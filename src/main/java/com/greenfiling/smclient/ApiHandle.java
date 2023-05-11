/**
 * Copyright 2021-2023 Green Filing, LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.greenfiling.smclient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.Exceptions.AccessForbiddenException;
import com.greenfiling.smclient.Exceptions.ConflictException;
import com.greenfiling.smclient.Exceptions.ContentTypeException;
import com.greenfiling.smclient.Exceptions.InvalidCredentialsException;
import com.greenfiling.smclient.Exceptions.InvalidEndpointException;
import com.greenfiling.smclient.Exceptions.InvalidRequestException;
import com.greenfiling.smclient.Exceptions.RecordNotFoundException;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.internal.DnsSelector;
import com.greenfiling.smclient.internal.DnsSelector.IpMode;
import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.internal.RequestEnclosure;
import com.greenfiling.smclient.internal.Transaction;
import com.greenfiling.smclient.internal.UserAgentHandle;
import com.greenfiling.smclient.internal.UserAgentInterceptor;
import com.greenfiling.smclient.model.Upload;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * The API handle needed by all client interfaces
 * <P>
 * The API handle contains non-endpoint-specific information about the API, like the API key, the API base URL, and timeouts. An ApiHandle is needed
 * to instantiate endpoint client classes. The idea is that a single ApiHandle can be generated per application and passed to each endpoint client.
 * The class is instantiated via it's {@link ApiHandle.Builder} interface.
 * <P>
 * <B>Example:</B><BR>
 * <code>
 * ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).build();
 * </code>
 * 
 * @author jetmore
 * @since 1.0.0
 */
public class ApiHandle {
  /**
   * The builder interface for creating {@link ApiHandle} objects
   * <P>
   * While Builder objects can be passed around, the simplest method is to call in a chained manner, with {@link #build()} generating the
   * {@link ApiHandle} object.
   * <P>
   * <B>Example:</B><BR>
   * <code>
   * ApiHandle apiHandle = new ApiHandle.Builder().apiKey(VALID_API_KEY).apiEndpoint(ApiHandle.DEFAULT_ENDPOINT_BASE).build();
   * </code>
   * 
   * @author jetmore
   * @since 1.0.0
   */
  public static class Builder {
    private static final Logger logger = LoggerFactory.getLogger(Builder.class);
    private String apiEndpointBase;
    private String apiKey;
    private Long writeTimeout;
    private Long readTimeout;
    private Long connectTimeout;
    private Integer keepTransactions;
    private okhttp3.OkHttpClient client;
    private String basicAuth;
    private IpMode ipMode;
    private okhttp3.OkHttpClient.Builder builder;

    /**
     * Set the API endpoint base
     * <P>
     * Defaults to {@link ApiHandle#DEFAULT_ENDPOINT_BASE}
     * 
     * @param apiEndpoint
     *          api endpoint to use
     * @return A valid @{link Builder} object so calls can be chained
     * @since 1.0.0
     */
    public Builder apiEndpoint(String apiEndpoint) {
      this.apiEndpointBase = apiEndpoint;
      return this;
    }

    /**
     * Set the API key to be used
     * <P>
     * This is customer-specific, required, and has no default value. Contact Serve Manager to get access to their API.
     * 
     * @param apiKey
     *          A valid access key to the Serve Manager api.
     * @return A valid @{link Builder} object so calls can be chained
     * @since 1.0.0
     */
    public Builder apiKey(String apiKey) {
      this.apiKey = apiKey;
      return this;
    }

    /**
     * Uses the parameters set on the build to instantiate and return an {@link ApiHandle} object
     * 
     * @return An instantiated {@link ApiHandle} if no configuration errors are found
     * @throws IllegalStateException
     *           If any {@link Builder} settings are invalid
     * @since 1.0.0
     */
    public ApiHandle build() {
      basicAuth = Base64.encodeBase64String((apiKey + ":").getBytes());

      if (apiEndpointBase == null || "".equals(apiEndpointBase)) {
        apiEndpointBase = DEFAULT_ENDPOINT_BASE;
      }

      boolean externalBuilder = true;
      if (builder == null) {
        externalBuilder = false;
        builder = new OkHttpClient.Builder();
      }

      if (ipMode != null) {
        builder.dns(new DnsSelector(ipMode));
      }

      if (keepTransactions == null) {
        keepTransactions = DEFAULT_KEEP_TRANSACTIONS;
      }

      // Only set the defaults if we're not using an external builder object
      if (!externalBuilder) {
        if (writeTimeout == null) {
          writeTimeout = DEFAULT_WRITE_TIMEOUT;
        }
        if (readTimeout == null) {
          readTimeout = DEFAULT_READ_TIMEOUT;
        }
        if (connectTimeout == null) {
          connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        }
      }

      if (writeTimeout != null) {
        builder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
      }
      if (readTimeout != null) {
        builder.readTimeout(readTimeout, TimeUnit.SECONDS);
      }
      if (connectTimeout != null) {
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
      }

      builder.addInterceptor(new UserAgentInterceptor(UserAgentHandle.get().getUserAgent()));

      logger.trace(
          "build - building and returning client, endpoint = {}, writeTimeout = {}, readTimeout = {}, connectTimeout = {}, keepTransactions = {}, ipMode = {}, auth = {}",
          apiEndpointBase, writeTimeout, readTimeout, connectTimeout, keepTransactions, ipMode, basicAuth);
      this.client = builder.build();

      ApiHandle client = new ApiHandle(this);
      validate(client);
      client.keepTransactions = keepTransactions;
      return client;
    }

    /**
     * Sets an externally configured {@link okhttp3.OkHttpClient.Builder} object
     * <P>
     * By default, {@link ApiHandle} instantiates its own {@link okhttp3.OkHttpClient.Builder} object and exposes a few interfaces for configuring it
     * ({@link #connectTimeout(int)}, etc). ServeManager-client is not interested in wrapping all okhttp3 functionality, so allows the user to pass in
     * an instantiated builder object which they have already configured.
     * 
     * @param builder
     *          An instantiated {@link okhttp3.OkHttpClient.Builder} object
     * @return A valid @{link Builder} object so calls can be chained
     * @since 1.0.1
     */
    public Builder builder(okhttp3.OkHttpClient.Builder builder) {
      this.builder = builder;
      return this;
    }

    /**
     * Sets the connection timeout for this handle
     * <P>
     * If this is not set, the builder will default to {@link ApiHandle#DEFAULT_CONNECT_TIMEOUT}
     *
     * @param connectTimeout
     *          The timeout, in seconds, for connection attempts
     * @return A valid @{link Builder} object so calls can be chained
     * @since 1.0.0
     */
    public Builder connectTimeout(int connectTimeout) {
      if (connectTimeout >= 0) {
        this.connectTimeout = Long.valueOf(connectTimeout);
      }
      return this;
    }

    /**
     * Sets the {@link IpMode} for the http connection
     * <P>
     * EXAMPLE: only attempt to connect to IPv4 addresses
     * <P>
     * 
     * <code>
     * ApiHandle apiHandle = new ApiHandle.Builder()<br>
     *                           .apiKey(VALID_API_KEY)<br>
     *                           .ipMode(IpMode.IPV4_ONLY)<br>
     *                           .build();<br>
     * </code>
     * 
     * @param ipMode
     *          an {@link IpMode} object to be used for connection attempts
     * @return A valid @{link Builder} object so calls can be chained
     * @since 1.0.1
     */
    public Builder ipMode(IpMode ipMode) {
      this.ipMode = ipMode;
      return this;
    }

    /**
     * Sets the number of {@link Transaction}s that will be kept in memory for this ApiHandle
     * <P>
     * If this is not set, the builder will default to {@link ApiHandle#DEFAULT_KEEP_TRANSACTIONS}
     * 
     * @param keepTransactions
     *          number of transactions to keep in memory
     * @return A valid @{link Builder} object so calls can be chained
     * @since 1.0.4
     */
    public Builder keepTransactions(int keepTransactions) {
      if (keepTransactions >= 0) {
        this.keepTransactions = keepTransactions;
      }
      return this;
    }

    /**
     * Sets the read timeout for this handle
     * <P>
     * If this is not set, the builder will default to {@link ApiHandle#DEFAULT_READ_TIMEOUT}
     *
     * @param readTimeout
     *          The timeout, in seconds, for read attempts
     * @return A valid @{link Builder} object so calls can be chained
     * @since 1.0.0
     */
    public Builder readTimeout(int readTimeout) {
      if (readTimeout >= 0) {
        this.readTimeout = Long.valueOf(readTimeout);
      }
      return this;
    }

    /**
     * Sets the write timeout for this handle
     * <P>
     * If this is not set, the builder will default to {@link ApiHandle#DEFAULT_WRITE_TIMEOUT}
     *
     * @param writeTimeout
     *          The timeout, in seconds, for write attempts
     * @return A valid @{link Builder} object so calls can be chained
     * @since 1.0.0
     */
    public Builder writeTimeout(Integer writeTimeout) {
      if (writeTimeout >= 0) {
        this.writeTimeout = Long.valueOf(writeTimeout);
      }
      return this;
    }

    /**
     * Validates an instantiated {@link ApiHandle} client to make sure we don't pass a misconfigured client to the user
     * 
     * @param client
     *          an instantiated {@link ApiHandle} to validate
     * @throws IllegalStateException
     */
    private void validate(ApiHandle client) throws IllegalStateException {
      ArrayList<String> errors = new ArrayList<String>();
      if (this.apiKey == null) {
        errors.add("ApiKey cannot be null");
      }
      if (this.client == null) {
        errors.add("HTTP Client did not instantiate");
      }
      if (basicAuth == null || "".equals(basicAuth)) {
        errors.add("Authentication string not built properly");
      }

      if (errors.size() > 0) {
        logger.error("validate - failed validation.  Errors: {}", errors.toString());
        throw new IllegalStateException(errors.toString());
      }
    }
  }

  private static final Logger logger = LoggerFactory.getLogger(ApiHandle.class);

  /**
   * The default endpoint base URL for the Serve Manager API
   */
  public static final String DEFAULT_ENDPOINT_BASE = "https://www.servemanager.com/api";
  /**
   * The default write timeout, in seconds, when communicating with the Serve Manager API
   */
  public static final long DEFAULT_WRITE_TIMEOUT = 30;
  /**
   * The default read timeout, in seconds, when communicating with the Serve Manager API
   */
  public static final long DEFAULT_READ_TIMEOUT = 60;
  /**
   * The default connect timeout, in seconds, when communicating with the Serve Manager API
   */
  public static final long DEFAULT_CONNECT_TIMEOUT = 180;
  /**
   * How many transactions are saved in the transaction history
   */
  public static final Integer DEFAULT_KEEP_TRANSACTIONS = 3;

  private String apiEndpointBase;
  private String basicAuth;
  private okhttp3.OkHttpClient client;
  private MediaType jsonMediaType;
  private Integer keepTransactions;
  private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

  /**
   * Instantiates an ApiHandler object based off a Builder object.
   * <P>
   * Never directly callable, should only ever be called by {@link Builder#build()}
   */
  private ApiHandle(Builder builder) {
    this.apiEndpointBase = builder.apiEndpointBase;
    this.client = builder.client;
    this.basicAuth = builder.basicAuth;
    this.jsonMediaType = MediaType.parse("application/json; charset=utf-8");
  }

  /**
   * Perform a GET request against the API.
   * <P>
   * Should not be called directly
   *
   * @param url
   *          the URL to GET
   * @return The response from the request
   * @throws Exception
   *           see {@link ApiClient#show(Integer)} for explanation of possible exceptions
   * @since 1.0.0
   */
  public String doGet(String url) throws Exception {
    logger.trace("doGet - url = {}", url);
    Request.Builder builder = new Request.Builder().url(url);
    return doApiRequest(new RequestEnclosure(builder, null));

  }

  /**
   * Download any arbitrary file and save to disk.
   * <P>
   * Does not use API authentication, useful for grabbing short term, non-api-protected files from {@link Upload} objects.
   *
   * @param url
   *          URL to download file from
   * @param filePath
   *          File on disk to save contents of download to
   * @throws Exception
   *           thrown if file cannot be downloaded for any reason
   */
  public void doGetFile(String url, String filePath) throws Exception {
    Request request = new Request.Builder().url(url).build();
    Response response = client.newCall(request).execute();

    if (!response.isSuccessful()) {
      throw new IOException("Couldn't download file: " + response);
    }

    FileOutputStream stream = new FileOutputStream(filePath);
    stream.write(response.body().bytes());
    stream.close();
  }

  /**
   * Download any arbitrary file that requires API authentication and save to disk.
   * <P>
   * Uses API authentication, useful for grabbing short term, api-protected files, e.g. from {@link com.greenfiling.smclient.model.Document Document}
   * objects.
   * 
   * @param url
   *          URL to download file from (assumed to be protected by ServeManager API authentication)
   * @param filePath
   *          File on disk to save contents of download to
   * @throws Exception
   *           thrown if file cannot be downloaded for any reason
   */
  public void doGetFileApi(String url, String filePath) throws Exception {
    Request.Builder builder = new Request.Builder().url(url);
    addApiHeaderAuthorization(builder);
    Request request = builder.build();
    Response response = client.newCall(request).execute();

    if (!response.isSuccessful()) {
      throw new IOException("Couldn't download file: " + response);
    }

    FileOutputStream stream = new FileOutputStream(filePath);
    stream.write(response.body().bytes());
    stream.close();
  }

  /**
   * Perform a POST request against the API.
   * <P>
   * Should not be called directly
   *
   * @param url
   *          the URL to POST
   * @param request
   *          the object to serialize as JSON and send to the API
   * @return The response from the request
   * @throws Exception
   *           see {@link ApiClient#show(Integer)} for explanation of possible exceptions
   * @since 1.0.0
   */
  public String doPost(String url, Object request) throws Exception {
    String jsonString = JsonHandle.get().toJson(request);
    logger.trace("doPost - url = {}, request = {}", url, jsonString);

    RequestBody requestBody = RequestBody.create(jsonString, this.jsonMediaType);
    Request.Builder builder = new Request.Builder().url(url).post(requestBody);
    return doApiRequest(new RequestEnclosure(builder, jsonString));
  }

  /**
   * Perform a PUT request against the API.
   * <P>
   * Should not be called directly
   *
   * @param url
   *          the URL to PUT
   * @param request
   *          the object to serialize as JSON and send to the API
   * @return The response from the request
   * @throws Exception
   *           see {@link ApiClient#show(Integer)} for explanation of possible exceptions
   * @since 1.0.0
   */
  public String doPut(String url, Object request) throws Exception {
    String jsonString = JsonHandle.get().toJson(request);
    logger.trace("doPut - url = {}, request = {}", url, jsonString);

    RequestBody requestBody = RequestBody.create(jsonString, this.jsonMediaType);
    Request.Builder builder = new Request.Builder().url(url).put(requestBody);
    return doApiRequest(new RequestEnclosure(builder, jsonString));
  }

  /**
   * Create a remote file using the PUT command
   * <P>
   * Should not be called directly
   *
   * @param url
   *          the URL to GET
   * @param requestBody
   *          the request body prepared for submission
   * @return The response from the request
   * @throws Exception
   *           see {@link ApiClient#show(Integer)} for explanation of possible exceptions
   * @since 1.0.0
   */
  public String doPutFile(String url, RequestBody requestBody) throws Exception {
    String dataString = "<" + requestBody.contentLength() + " bytes of file data>";
    logger.trace("doPutFile - url = {}, request = {}", url, dataString);

    return doRequest(new RequestEnclosure(new Request.Builder().url(url).put(requestBody), dataString));
  }

  /**
   * Get the API endpoint URL
   *
   * @return The currently-in-use API endpint URL
   * @since 1.0.0
   */
  public String getApiEndpointBase() {
    return apiEndpointBase;
  }

  /**
   * Returns a list of saved {@link Transaction} objects.
   * 
   * @return list of {@link Transaction} objects. Newest transaction is always index 0
   * @since 1.0.4
   */
  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  private void addApiHeaderAccept(Request.Builder builder) {
    builder.addHeader("accept", "application/json");
  }

  private void addApiHeaderAuthorization(Request.Builder builder) {
    builder.addHeader("Authorization", "Basic " + this.basicAuth);
  }

  /**
   * Adds headers to a Request.Builder object needed to communicate with the API
   */
  private RequestEnclosure addApiHeaders(RequestEnclosure enclosure) {
    addApiHeaderAccept(enclosure.getBuilder());
    addApiHeaderAuthorization(enclosure.getBuilder());
    return enclosure;
  }

  /**
   * Perform a request against the API
   */
  private String doApiRequest(RequestEnclosure enclosure) throws Exception {
    return doRequest(addApiHeaders(enclosure));
  }

  /**
   * Perform the actual http request.
   * <P>
   * This is a raw request (for S3, etc). If you need to communicate with the API use {@link #doApiRequest(okhttp3.Request.Builder)} instead
   */
  private String doRequest(RequestEnclosure enclosure) throws Exception {
    okhttp3.Request.Builder builder = enclosure.getBuilder();
    Transaction txn = getNewTransaction();
    txn.setRequestUrl(builder.getUrl$okhttp().toString());
    txn.setRequestBody(enclosure.getRequestBody());
    txn.setRequestType(builder.getMethod$okhttp());

    String responseBody;
    int responseCode;
    try (Response response = client.newCall(builder.build()).execute()) {
      try (ResponseBody body = response.body()) {
        responseBody = body.string().trim();
      }
      responseCode = response.code();
      txn.setResponseCode(responseCode);
      txn.setResponseLine(response.message());
    }

    if (responseBody == null) {
      responseBody = "";
    }
    txn.setResponseBody(responseBody);

    logger.trace("doRequest - response = {}", responseBody);

    if (responseCode == 200 || responseCode == 201) {
      return responseBody;
    }

    ///////////////////////////////////////
    // Anything past here is error handling

    // TODO - parse the error body as JSON and return a more meaningful response. For now just return the raw json
    String error = responseBody;

    if (responseCode == 404) {
      if (error.startsWith("{\"errors\":")) {
        logger.info("doRequest - 404, record not found, error = {}", error);
        throw new RecordNotFoundException(error);
      } else {
        logger.info("doRequest - 404, invalid endpoint");
        throw new InvalidEndpointException();
      }
    }

    if (responseCode == 401) {
      logger.info("doRequest - 401, invalid credentials");
      throw new InvalidCredentialsException();
    }

    if (responseCode == 403) {
      logger.info("doRequest - 403, access forbidden, error - {}", error);
      throw new AccessForbiddenException(error);
    }

    if (responseCode == 406) {
      logger.info("doRequest - 406, Content-Type exception");
      throw new ContentTypeException();
    }

    if (responseCode == 422) {
      logger.info("doRequest - 422, invalid request, error - {}", error);
      throw new InvalidRequestException(error);
    }

    if (responseCode == 409) {
      logger.info("doRequest - 409, conflict, error - {}", error);
      throw new ConflictException(error);
    }

    logger.info("doRequest - An unknown exception occurred. Server response code = {}, error = {}", responseCode, error);
    throw new Exception("An unknown exception occurred. Server response code = " + responseCode + ", error = " + error);
  }

  private Transaction getNewTransaction() {
    Transaction txn = new Transaction();
    getTransactions().add(0, txn);

    // Make sure we don't retain more than we're supposed to
    while (getTransactions().size() > keepTransactions) {
      getTransactions().remove(getTransactions().size() - 1);
    }

    return txn;
  }

}
