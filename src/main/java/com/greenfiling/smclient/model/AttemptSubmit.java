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

import com.greenfiling.smclient.model.internal.AttemptBase;

public class AttemptSubmit extends AttemptBase {
  private String recipientName;
  private String recipientDescription;
  private String recipientAge;
  private String recipientEthnicity;
  private String recipientGender;
  private String recipientWeight;
  private String recipientHeight1;
  private String recipientHeight2;
  private String recipientHair;
  private String recipientRelationship;
  private String recipientEyes;
  private Integer processServerId;
  private Integer addressId;
  private Address addressAttributes;

  public AttemptSubmit() {
    super();
  }

  public AttemptSubmit(Attempt attempt) {
    super(attempt);

    // either address_attributes OR addressId can be populated, but not both
    if (attempt.getAddress() != null) {
      if (attempt.getAddress().getId() != null) {
        setAddressId(attempt.getAddress().getId());
      } else {
        setAddressAttributes(attempt.getAddress());
      }
    }

    // either process_server_id or server_name. Can't be both
    if (attempt.getProcessServer() != null) {
      if (attempt.getProcessServer().getId() != null) {
        setProcessServerId(attempt.getProcessServer().getId());
      } else {
        setServerName(attempt.getServerName());
      }
    }

    // For some reason the flattened the recipient information instead of using the existing Recipient object
    if (attempt.getRecipient() != null) {
      setRecipientName(attempt.getRecipient().getName());
      setRecipientDescription(attempt.getRecipient().getDescription());
      setRecipientAge(attempt.getRecipient().getAge());
      setRecipientEthnicity(attempt.getRecipient().getEthnicity());
      setRecipientGender(attempt.getRecipient().getGender());
      setRecipientWeight(attempt.getRecipient().getWeight());
      setRecipientHeight1(attempt.getRecipient().getHeight1());
      setRecipientHeight2(attempt.getRecipient().getHeight2());
      setRecipientHair(attempt.getRecipient().getHair());
      setRecipientRelationship(attempt.getRecipient().getRelationship());
      setRecipientEyes(attempt.getRecipient().getEyes());
    }
  }

  public Address getAddressAttributes() {
    return this.addressAttributes;
  }

  public Integer getAddressId() {
    return this.addressId;
  }

  public Integer getProcessServerId() {
    return this.processServerId;
  }

  public String getRecipientAge() {
    return this.recipientAge;
  }

  public String getRecipientDescription() {
    return this.recipientDescription;
  }

  public String getRecipientEthnicity() {
    return this.recipientEthnicity;
  }

  public String getRecipientEyes() {
    return this.recipientEyes;
  }

  public String getRecipientGender() {
    return this.recipientGender;
  }

  public String getRecipientHair() {
    return this.recipientHair;
  }

  public String getRecipientHeight1() {
    return this.recipientHeight1;
  }

  public String getRecipientHeight2() {
    return this.recipientHeight2;
  }

  public String getRecipientName() {
    return this.recipientName;
  }

  public String getRecipientRelationship() {
    return this.recipientRelationship;
  }

  public String getRecipientWeight() {
    return this.recipientWeight;
  }

  public void setAddressAttributes(Address addressAttributes) {
    this.addressAttributes = addressAttributes;
  }

  public void setAddressId(Integer addressId) {
    this.addressId = addressId;
  }

  public void setProcessServerId(Integer processServerId) {
    this.processServerId = processServerId;
  }

  public void setRecipientAge(String recipientAge) {
    this.recipientAge = recipientAge;
  }

  public void setRecipientDescription(String recipientDescription) {
    this.recipientDescription = recipientDescription;
  }

  /**
   * Sets the gender
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link Recipient#ETHNICITY_AFRICAN_AMERICAN}</LI>
   * <LI>{@link Recipient#ETHNICITY_ASIAN_AMERICAN}</LI>
   * <LI>{@link Recipient#ETHNICITY_CAUCASIAN}</LI>
   * <LI>{@link Recipient#ETHNICITY_HISPANIC}</LI>
   * <LI>{@link Recipient#ETHNICITY_LATINO}</LI>
   * <LI>{@link Recipient#ETHNICITY_MIDDLE_EASTERN}</LI>
   * <LI>{@link Recipient#ETHNICITY_NATIVE_AMERICAN}</LI>
   * <LI>{@link Recipient#ETHNICITY_NATIVE_HAWAIIAN}</LI>
   * <LI>{@link Recipient#ETHNICITY_OTHER}</LI>
   * <LI>{@link Recipient#ETHNICITY_BLANK}</LI>
   * </UL>
   * 
   * @param recipientEthnicity
   */
  public void setRecipientEthnicity(String recipientEthnicity) {
    this.recipientEthnicity = recipientEthnicity;
  }

  /**
   * Sets the eyes field
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link Recipient#EYES_AMBER}</LI>
   * <LI>{@link Recipient#EYES_BLACK}</LI>
   * <LI>{@link Recipient#EYES_BLUE}</LI>
   * <LI>{@link Recipient#EYES_BROWN}</LI>
   * <LI>{@link Recipient#EYES_GRAY}</LI>
   * <LI>{@link Recipient#EYES_GREEN}</LI>
   * <LI>{@link Recipient#EYES_HAZEL}</LI>
   * <LI>{@link Recipient#EYES_OTHER}</LI>
   * <LI>{@link Recipient#EYES_BLANK}</LI>
   * </UL>
   * 
   * @param recipientEyes
   */
  public void setRecipientEyes(String recipientEyes) {
    this.recipientEyes = recipientEyes;
  }

  /**
   * Sets the ethnicity
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link Recipient#GENDER_MALE}</LI>
   * <LI>{@link Recipient#GENDER_FEMALE}</LI>
   * <LI>{@link Recipient#GENDER_OTHER}</LI>
   * <LI>{@link Recipient#GENDER_BLANK}</LI>
   * </UL>
   * 
   * @param recipientGender
   */
  public void setRecipientGender(String recipientGender) {
    this.recipientGender = recipientGender;
  }

  /**
   * Sets the hair field
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link Recipient#HAIR_BALD}</LI>
   * <LI>{@link Recipient#HAIR_BLACK}</LI>
   * <LI>{@link Recipient#HAIR_BLOND}</LI>
   * <LI>{@link Recipient#HAIR_BROWN}</LI>
   * <LI>{@link Recipient#HAIR_GRAY}</LI>
   * <LI>{@link Recipient#HAIR_RED}</LI>
   * <LI>{@link Recipient#HAIR_WHITE}</LI>
   * <LI>{@link Recipient#HAIR_OTHER}</LI>
   * <LI>{@link Recipient#HAIR_BLANK}</LI>
   * </UL>
   * 
   * @param recipientHair
   */
  public void setRecipientHair(String recipientHair) {
    this.recipientHair = recipientHair;
  }

  /**
   * Sets the height1 field (the "foot" part of the height)
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link Recipient#HEIGHT_3_FT}</LI>
   * <LI>{@link Recipient#HEIGHT_4_FT}</LI>
   * <LI>{@link Recipient#HEIGHT_5_FT}</LI>
   * <LI>{@link Recipient#HEIGHT_6_FT}</LI>
   * <LI>{@link Recipient#HEIGHT_7_FT}</LI>
   * <LI>{@link Recipient#HEIGHT_8_FT}</LI>
   * <LI>{@link Recipient#HEIGHT_9_FT}</LI>
   * <LI>{@link Recipient#HEIGHT_BLANK}</LI>
   * </UL>
   * 
   * @param recipientHeight1
   */
  public void setRecipientHeight1(String recipientHeight1) {
    this.recipientHeight1 = recipientHeight1;
  }

  /**
   * Sets the height2 field (the "inch" part of the height)
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link Recipient#HEIGHT_1_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_2_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_3_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_4_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_5_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_6_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_7_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_8_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_9_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_10_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_11_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_12_IN}</LI>
   * <LI>{@link Recipient#HEIGHT_BLANK}</LI>
   * </UL>
   * 
   * @param recipientHeight2
   */
  public void setRecipientHeight2(String recipientHeight2) {
    this.recipientHeight2 = recipientHeight2;
  }

  public void setRecipientName(String recipientName) {
    this.recipientName = recipientName;
  }

  /**
   * Sets the relationship field
   * <P>
   * This field typically uses pre-defined values, but can use a custom value if desired. The "known" values are
   * <UL>
   * <LI>{@link Recipient#RELATIONSHIP_AUNT}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_BOYFRIEND}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_BROTHER}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_COUSIN}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_DAUGHTER}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_FATHER}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_GIRLFRIEND}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_GRANDFATHER}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_GRANDMOTHER}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_HUSBAND}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_MOTHER}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_PARTNER}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_NEPHEW}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_NIECE}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_SISTER}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_SON}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_UNCLE}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_WIFE}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_OTHER}</LI>
   * <LI>{@link Recipient#RELATIONSHIP_BLANK}</LI>
   * </UL>
   * 
   * @param recipientRelationship
   */
  public void setRecipientRelationship(String recipientRelationship) {
    this.recipientRelationship = recipientRelationship;
  }

  public void setRecipientWeight(String recipientWeight) {
    this.recipientWeight = recipientWeight;
  }

}
