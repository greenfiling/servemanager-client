package com.greenfiling.smclient;

import com.google.gson.reflect.TypeToken;
import com.greenfiling.smclient.internal.ApiClient;
import com.greenfiling.smclient.internal.JsonHandle;
import com.greenfiling.smclient.model.Contact;
import com.greenfiling.smclient.model.Invoice;
import com.greenfiling.smclient.model.Job;
import com.greenfiling.smclient.model.JobSubmit;
import com.greenfiling.smclient.model.ServerInvoiceSubmit;
import com.greenfiling.smclient.model.exchange.Index;
import com.greenfiling.smclient.model.exchange.Show;
import com.greenfiling.smclient.model.internal.InvoiceBase;
import com.greenfiling.smclient.model.internal.JobBase;

public class ExchangeJobClient extends ApiClient<JobBase, Job, JobSubmit> {
  public static final String ENDPOINT = "infotrack_exchange/jobs";

  public static final String TYPE_SERVER_INVOICE = "server_invoice";
  public static final String TYPE_SERVER_CLIENT_CONTACT = "server_client_contact";

  public ExchangeJobClient(ApiHandle handle) {
    super(handle);
    setEndpoint(ENDPOINT);

    // @formatter:off
    setShowType(new TypeToken<Show<Job>>() {}.getType());
    setIndexType(new TypeToken<Index<Job>>() {}.getType());
    // @formatter:on
  }

  // POST - /jobs/:job_id/server_invoices
  public Show<Invoice> createServerInvoice(Integer jobId, ServerInvoiceSubmit record) throws Exception {
    Show<ServerInvoiceSubmit> showRecord = new Show<ServerInvoiceSubmit>(record);
    String url = makeShowBaseUrl(jobId) + "/server_invoices";
    String responseJson = getHandle().doPost(url, showRecord);
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Show<Invoice>>() {
    }.getType());
  }

  // PUT - /jobs/:job_id/server_invoices/lock_invoice
  public Show<Invoice> lockServerInvoice(Integer jobId) throws Exception {
    InvoiceBase record = new InvoiceBase();
    // manually set endpoint required type
    record.setType(TYPE_SERVER_INVOICE);
    Show<InvoiceBase> showRecord = new Show<InvoiceBase>(record);
    String url = makeShowBaseUrl(jobId) + "/server_invoices/lock_invoice";
    String responseJson = getHandle().doPut(url, showRecord);
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Show<Invoice>>() {
    }.getType());
  }

  // POST - /jobs/:job_id/server_client_contact
  public Show<Contact> updateServerClientContact(Integer jobId, Contact record) throws Exception {
    // manually set endpoint required type
    record.setType(TYPE_SERVER_CLIENT_CONTACT);
    Show<Contact> showRecord = new Show<Contact>(record);
    String url = makeShowBaseUrl(jobId) + "/server_client_contact";
    String responseJson = getHandle().doPost(url, showRecord);
    return JsonHandle.get().getGson().fromJson(responseJson, new TypeToken<Show<Contact>>() {
    }.getType());
  }
}