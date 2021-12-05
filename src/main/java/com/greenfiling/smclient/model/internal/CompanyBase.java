/**
 * Copyright 2021 Green Filing, LLC
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

package com.greenfiling.smclient.model.internal;

public abstract class CompanyBase {
  public static final String TYPE = "company";

  // legal values for the companyType field. Not an enum because it will also accept custom values
  public static final String COMPANY_TYPE_ATTORNEY = "Attorney";
  public static final String COMPANY_TYPE_CONTRACTOR = "Contractor";
  public static final String COMPANY_TYPE_GOVERNMENT = "Government";
  public static final String COMPANY_TYPE_INDIVIDUAL = "Individual";
  public static final String COMPANY_TYPE_LAW_FIRM = "Law Firm";
  public static final String COMPANY_TYPE_PARALEGAL = "Paralegal";
  public static final String COMPANY_TYPE_PROCESS_SERVER = "Process Server";
  public static final String COMPANY_TYPE_BLANK = "";

  private String type;
  private String name;
  private String website;
  private String companyType;
  private String privateNote;

  public CompanyBase() {
    super();
    setType(TYPE);
  }

  public CompanyBase(CompanyBase company) {
    super();
    setType(TYPE);

    setName(company.getName());
    setWebsite(company.getWebsite());
    setCompanyType(company.getCompanyType());
    setPrivateNote(company.getPrivateNote());
  }

  public String getCompanyType() {
    return this.companyType;
  }

  public String getName() {
    return this.name;
  }

  public String getPrivateNote() {
    return this.privateNote;
  }

  public String getType() {
    return this.type;
  }

  public String getWebsite() {
    return this.website;
  }

  /**
   * Sets the company type record
   * <P>
   * This field typically uses pre-defined values, but can use a custom value if desired. The "known" values are
   * <UL>
   * <LI>{@link #COMPANY_TYPE_ATTORNEY}</LI>
   * <LI>{@link #COMPANY_TYPE_CONTRACTOR}</LI>
   * <LI>{@link #COMPANY_TYPE_GOVERNMENT}</LI>
   * <LI>{@link #COMPANY_TYPE_INDIVIDUAL}</LI>
   * <LI>{@link #COMPANY_TYPE_LAW_FIRM}</LI>
   * <LI>{@link #COMPANY_TYPE_PARALEGAL}</LI>
   * <LI>{@link #COMPANY_TYPE_PROCESS_SERVER}</LI>
   * <LI>{@link #COMPANY_TYPE_BLANK}</LI>
   * </UL>
   * 
   * @param companyType
   */
  public void setCompanyType(String companyType) {
    this.companyType = companyType;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrivateNote(String privateNote) {
    this.privateNote = privateNote;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

}
