package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Contact;
import com.greenfiling.smclient.model.Invoice;
import com.greenfiling.smclient.model.ServerInvoiceSubmit;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.InvoiceBase;

public class SopExchangeJobManagementClient {
  public static final String ENDPOINT = "infotrack_exchange/jobs";

  public static final String TYPE_SERVER_INVOICE = "server_invoice";
  public static final String TYPE_SERVER_CLIENT_CONTACT = "server_client_contact";

  private ApiHandle apiHandle;

  public SopExchangeJobManagementClient(ApiHandle handle) {
    setHandle(handle);
  }

  /**
   * Creates the agency's invoice for a shared job.
   * 
   * @param jobId
   * @param record
   * @return
   * @throws Exception
   */
  public Show<Invoice> createServerInvoice(Integer jobId, ServerInvoiceSubmit record) throws Exception {
    Show<ServerInvoiceSubmit> showRecord = new Show<ServerInvoiceSubmit>(record);
    String url = makeShowBaseUrl(jobId) + "/server_invoices";
    String responseJson = getHandle().doPost(url, showRecord);
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Show<Invoice>>() {
    }.getType());
  }

  /**
   * Locks the invoice against further edits by the agency.
   * 
   * @param jobId
   * @return
   * @throws Exception
   */
  public Show<Invoice> lockServerInvoice(Integer jobId) throws Exception {
    InvoiceBase record = new InvoiceBase();
    // manually set to type required by endpoint
    record.setType(TYPE_SERVER_INVOICE);
    Show<InvoiceBase> showRecord = new Show<InvoiceBase>(record);
    String url = makeShowBaseUrl(jobId) + "/server_invoices/lock_invoice";
    String responseJson = getHandle().doPut(url, showRecord);
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Show<Invoice>>() {
    }.getType());
  }

  /**
   * Creates or updates the firm's contact person on the agency's copy of the job, so the agency knows who to reach at the firm.
   * 
   * @param jobId
   * @param record
   * @return
   * @throws Exception
   */
  public Show<Contact> updateServerClientContact(Integer jobId, Contact record) throws Exception {
    // manually set to type required by endpoint
    record.setType(TYPE_SERVER_CLIENT_CONTACT);
    Show<Contact> showRecord = new Show<Contact>(record);
    String url = makeShowBaseUrl(jobId) + "/server_client_contact";
    String responseJson = getHandle().doPost(url, showRecord);
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Show<Contact>>() {
    }.getType());
  }

  private ApiHandle getHandle() {
    return apiHandle;
  }

  private String makeShowBaseUrl(Integer id) {
    String baseUrl = apiHandle.getApiEndpointBase() + "/" + ENDPOINT;

    if (id == null) {
      return baseUrl;
    }
    return baseUrl + "/" + id.toString();

  }

  private void setHandle(ApiHandle handle) {
    this.apiHandle = handle;
  }
}