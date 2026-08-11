package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Firm;
import com.greenfiling.smclient.model.FirmApiKey;
import com.greenfiling.smclient.model.FirmApiKeySubmit;
import com.greenfiling.smclient.model.FirmSubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.FirmBase;

public class FirmClient extends ApiClient<FirmBase, Firm, FirmSubmit> {
  public static final String ENDPOINT = "infotrack_exchange/firms";

  public FirmClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

  // @formatter:off
  setShowType(new TypeToken<Show<Firm>>() {}.getType());
  setIndexType(new TypeToken<Index<Firm>>() {}.getType());
  // @formatter:on
  }

  // POST /firms
  @Override
  @SuppressWarnings("unchecked")
  public Show<Firm> create(FirmBase record) throws Exception {
    FirmSubmit submitRecord = (record instanceof FirmSubmit) ? (FirmSubmit) record : new FirmSubmit((Firm) record);
    return (Show<Firm>) toShow(doCreateRequest(submitRecord));
  }

  // GET /firms/:id
  @Override
  @SuppressWarnings("unchecked")
  public Show<Firm> show(Integer id) throws Exception {
    return (Show<Firm>) toShow(doShowRequest(id));
  }

  // PUT /firms/:id
  @Override
  @SuppressWarnings("unchecked")
  public Show<Firm> update(Integer id, FirmBase record) throws Exception {
    FirmSubmit submitRecord = (record instanceof FirmSubmit) ? (FirmSubmit) record : new FirmSubmit((Firm) record);
    return (Show<Firm>) toShow(doUpdateRequest(id, submitRecord));
  }

  // POST /firms/:id/api_keys
  public Show<FirmApiKey> createFirmApiKey(FirmBase record) throws Exception {
    Integer firmId = null /* TODO: model must support getFirmId */;
    FirmApiKeySubmit showRecord = new FirmApiKeySubmit(record);
    String url = makeFirmApiKeyUrl(firmId, null);
    String responseJson = getHandle().doPost(url, showRecord);
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Show<FirmApiKey>>() {
    }.getType());
  }

  // GET /firms/:id/api_keys/:id
  public Show<FirmApiKey> showFirmApiKey(Integer firmId, Integer keyId) throws Exception {
    String url = makeFirmApiKeyUrl(firmId, keyId);
    String responseJson = getHandle().doGet(url);
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Show<FirmApiKey>>() {
    }.getType());
  }

  private String makeFirmApiKeyUrl(Integer firmId, Integer keyId) {
    String url = makeShowBaseUrl(firmId) + "/" + "api_keys";
    if (keyId == null) {
      return url;
    }
    return url + "/" + keyId.toString();
  }
}
