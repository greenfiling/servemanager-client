package com.greenfiling.smclient.model;

import java.util.ArrayList;
import java.util.List;

import com.greenfiling.smclient.model.internal.InvoiceBase;

public class ServerInvoiceSubmit extends InvoiceBase {
  public static final String TYPE = "server_invoice";

  private String terms;
  private ArrayList<LineItem> lineItemsAttributes;

  public ServerInvoiceSubmit() {
    super();
    setType(TYPE);
  }

  public List<LineItem> getLineItemsAttributes() {
    return this.lineItemsAttributes;
  }

  public String getTerms() {
    return this.terms;
  }

  public void setLineItemsAttributes(ArrayList<LineItem> lineItemsAttributes) {
    this.lineItemsAttributes = lineItemsAttributes;
  }

  public void setTerms(String terms) {
    this.terms = terms;
  }
}