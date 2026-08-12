package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Account;
import com.greenfiling.smclient.model.FirmApiKey;
import com.greenfiling.smclient.model.FirmApiKeySubmit;
import com.greenfiling.smclient.model.FirmSubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.AccountBase;

public class FirmClient extends ApiClient<AccountBase, Account, FirmSubmit> {
  public static final String ENDPOINT = "infotrack_exchange/firms";

  public FirmClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

  // @formatter:off
  setShowType(new TypeToken<Show<Account>>() {}.getType());
  setIndexType(new TypeToken<Index<Account>>() {}.getType());
  // @formatter:on
  }

  // POST /firms
  @Override
  @SuppressWarnings("unchecked")
  public Show<Account> create(AccountBase record) throws Exception {
    FirmSubmit submitRecord = (record instanceof FirmSubmit) ? (FirmSubmit) record : new FirmSubmit((Account) record);
    return (Show<Account>) toShow(doCreateRequest(submitRecord));
  }

  // GET /firms/:id
  @Override
  @SuppressWarnings("unchecked")
  public Show<Account> show(Integer id) throws Exception {
    return (Show<Account>) toShow(doShowRequest(id));
  }

  // PUT /firms/:id
  @Override
  @SuppressWarnings("unchecked")
  public Show<Account> update(Integer id, AccountBase record) throws Exception {
    FirmSubmit submitRecord = (record instanceof FirmSubmit) ? (FirmSubmit) record : new FirmSubmit((Account) record);
    return (Show<Account>) toShow(doUpdateRequest(id, submitRecord));
  }

  // POST /firms/:id/api_keys
  /**
   * Mints the firm's firm_direct_exchange key and returns the raw key value. This is the key you authenticate as the firm with for connections and
   * job creation. The target account must be an Exchange firm, which POST /firms guarantees. <br>
   * If the firm already has an Exchange key, this returns that existing key rather than creating a second one, so the call is safe to repeat.
   * 
   * @param firmId
   * @return
   * @throws Exception
   */
  public Show<FirmApiKey> createFirmApiKey(Integer firmId) throws Exception {
    FirmApiKeySubmit showRecord = new FirmApiKeySubmit();
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
