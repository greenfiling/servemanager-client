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

  /**
   * Creates a ServeManager account for a law firm and marks it as an Exchange firm in the same call, so the account is immediately eligible for a
   * firm key and for connections. Returns the full account object.
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<Account> create(AccountBase record) throws Exception {
    FirmSubmit submitRecord = (record instanceof FirmSubmit) ? (FirmSubmit) record : new FirmSubmit((Account) record);
    return (Show<Account>) toShow(doCreateRequest(submitRecord));
  }

  /**
   * Reads a firm account.
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<Account> show(Integer id) throws Exception {
    return (Show<Account>) toShow(doShowRequest(id));
  }

  /**
   * Updates a firm's own details. Cannot change the account's Exchange status either way.
   */
  @Override
  @SuppressWarnings("unchecked")
  public Show<Account> update(Integer id, AccountBase record) throws Exception {
    FirmSubmit submitRecord = (record instanceof FirmSubmit) ? (FirmSubmit) record : new FirmSubmit((Account) record);
    return (Show<Account>) toShow(doUpdateRequest(id, submitRecord));
  }

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
    Show<FirmApiKeySubmit> showRecord = new Show<FirmApiKeySubmit>(new FirmApiKeySubmit());
    String url = makeFirmApiKeyUrl(firmId, null);
    String responseJson = getHandle().doPost(url, showRecord);
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Show<FirmApiKey>>() {
    }.getType());
  }

  /**
   * Reads a key by id, including its raw value, so you can recover a key you did not persist. The key must belong to the given firm. Not restricted
   * to firm_direct_exchange keys, any of the firm's keys can be read.
   * 
   * @param firmId
   * @param keyId
   * @return
   * @throws Exception
   */
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
