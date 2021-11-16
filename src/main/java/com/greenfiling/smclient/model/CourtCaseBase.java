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

package com.greenfiling.smclient.model;

import java.time.LocalDate;

public abstract class CourtCaseBase {
  public static final String TYPE = "court_case";

  private String type;
  private String plaintiff;
  private String defendant;
  private LocalDate filedDate;
  private LocalDate courtDate;
  private String number;

  public CourtCaseBase() {
    super();
    setType(TYPE);
  }

  public CourtCaseBase(CourtCaseBase courtCase) {
    super();
    setType(TYPE);

    setPlaintiff(courtCase.getPlaintiff());
    setDefendant(courtCase.getDefendant());
    setFiledDate(courtCase.getFiledDate());
    setCourtDate(courtCase.getCourtDate());
    setNumber(courtCase.getNumber());
  }

  public LocalDate getCourtDate() {
    return this.courtDate;
  }

  public String getDefendant() {
    return this.defendant;
  }

  public LocalDate getFiledDate() {
    return this.filedDate;
  }

  public String getNumber() {
    return this.number;
  }

  public String getPlaintiff() {
    return this.plaintiff;
  }

  public String getType() {
    return this.type;
  }

  public void setCourtDate(LocalDate courtDate) {
    this.courtDate = courtDate;
  }

  public void setDefendant(String defendant) {
    this.defendant = defendant;
  }

  public void setFiledDate(LocalDate filedDate) {
    this.filedDate = filedDate;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public void setPlaintiff(String plaintiff) {
    this.plaintiff = plaintiff;
  }

  public void setType(String type) {
    this.type = type;
  }

}
