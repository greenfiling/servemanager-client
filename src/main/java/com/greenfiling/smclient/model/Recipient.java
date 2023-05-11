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

package com.greenfiling.smclient.model;

public class Recipient {
  public static final String ETHNICITY_AFRICAN_AMERICAN = "African American";
  public static final String ETHNICITY_ASIAN_AMERICAN = "Asian American";
  public static final String ETHNICITY_CAUCASIAN = "Caucasian";
  public static final String ETHNICITY_HISPANIC = "Hispanic";
  public static final String ETHNICITY_LATINO = "Latino";
  public static final String ETHNICITY_MIDDLE_EASTERN = "Middle Eastern";
  public static final String ETHNICITY_NATIVE_AMERICAN = "American";
  public static final String ETHNICITY_NATIVE_HAWAIIAN = "Native Hawaiian";
  public static final String ETHNICITY_OTHER = "Other";
  public static final String ETHNICITY_BLANK = "";

  public static final String GENDER_MALE = "Male";
  public static final String GENDER_FEMALE = "Female";
  public static final String GENDER_OTHER = "Other";
  public static final String GENDER_BLANK = "";

  public static final String HEIGHT_3_FT = "3'";
  public static final String HEIGHT_4_FT = "4'";
  public static final String HEIGHT_5_FT = "5'";
  public static final String HEIGHT_6_FT = "6'";
  public static final String HEIGHT_7_FT = "7'";
  public static final String HEIGHT_8_FT = "8'";
  public static final String HEIGHT_9_FT = "9'";
  public static final String HEIGHT_1_IN = "1\"";
  public static final String HEIGHT_2_IN = "2\"";
  public static final String HEIGHT_3_IN = "3\"";
  public static final String HEIGHT_4_IN = "4\"";
  public static final String HEIGHT_5_IN = "5\"";
  public static final String HEIGHT_6_IN = "6\"";
  public static final String HEIGHT_7_IN = "7\"";
  public static final String HEIGHT_8_IN = "8\"";
  public static final String HEIGHT_9_IN = "9\"";
  public static final String HEIGHT_10_IN = "10\"";
  public static final String HEIGHT_11_IN = "11\"";
  public static final String HEIGHT_12_IN = "12\"";
  public static final String HEIGHT_BLANK = "";

  public static final String HAIR_BALD = "Bald";
  public static final String HAIR_BLACK = "Black";
  public static final String HAIR_BLOND = "Blond";
  public static final String HAIR_BROWN = "Brown";
  public static final String HAIR_GRAY = "Gray";
  public static final String HAIR_RED = "Red";
  public static final String HAIR_WHITE = "White";
  public static final String HAIR_OTHER = "Other";
  public static final String HAIR_BLANK = "";

  public static final String EYES_AMBER = "Amber";
  public static final String EYES_BLACK = "Black";
  public static final String EYES_BLUE = "Blue";
  public static final String EYES_BROWN = "Brown";
  public static final String EYES_GRAY = "Gray";
  public static final String EYES_GREEN = "Green";
  public static final String EYES_HAZEL = "Hazel";
  public static final String EYES_OTHER = "Other";
  public static final String EYES_BLANK = "";

  public static final String RELATIONSHIP_AUNT = "Aunt";
  public static final String RELATIONSHIP_BOYFRIEND = "Boyfriend";
  public static final String RELATIONSHIP_BROTHER = "Brother";
  public static final String RELATIONSHIP_COUSIN = "Cousin";
  public static final String RELATIONSHIP_DAUGHTER = "Daughter";
  public static final String RELATIONSHIP_FATHER = "Father";
  public static final String RELATIONSHIP_GIRLFRIEND = "Girlfriend";
  public static final String RELATIONSHIP_GRANDFATHER = "Grandfather";
  public static final String RELATIONSHIP_GRANDMOTHER = "Grandmother";
  public static final String RELATIONSHIP_HUSBAND = "Husband";
  public static final String RELATIONSHIP_MOTHER = "Mother";
  public static final String RELATIONSHIP_PARTNER = "Partner";
  public static final String RELATIONSHIP_NEPHEW = "Nephew";
  public static final String RELATIONSHIP_NIECE = "Niece";
  public static final String RELATIONSHIP_SISTER = "Sister";
  public static final String RELATIONSHIP_SON = "Son";
  public static final String RELATIONSHIP_UNCLE = "Uncle";
  public static final String RELATIONSHIP_WIFE = "Wife";
  public static final String RELATIONSHIP_OTHER = "Other";
  public static final String RELATIONSHIP_BLANK = "";

  public static final String RECIPIENT_TYPE_INDIVIDUAL = "individual";
  public static final String RECIPIENT_TYPE_ORGANIZATION = "organization";

  private String name;
  private String description;
  private String age;
  private String ethnicity;
  private String gender;
  private String weight;
  private String height1;
  private String height2;
  private String hair;
  private String relationship;
  private String eyes;
  private String email;
  private String phone;
  private String type;
  private String agentForService;

  public String getAge() {
    return this.age;
  }

  public String getAgentForService() {
    return this.agentForService;
  }

  public String getDescription() {
    return this.description;
  }

  public String getEmail() {
    return this.email;
  }

  public String getEthnicity() {
    return this.ethnicity;
  }

  public String getEyes() {
    return this.eyes;
  }

  public String getGender() {
    return this.gender;
  }

  public String getHair() {
    return this.hair;
  }

  public String getHeight1() {
    return this.height1;
  }

  public String getHeight2() {
    return this.height2;
  }

  public String getName() {
    return this.name;
  }

  public String getPhone() {
    return this.phone;
  }

  public String getRelationship() {
    return this.relationship;
  }

  public String getType() {
    return this.type;
  }

  public String getWeight() {
    return this.weight;
  }

  public void setAge(String age) {
    this.age = age;
  }

  /**
   * Sets the registered agent for this service
   * <P>
   * If the recipient <code>type</code> is RECIPIENT_TYPE_ORGANIZATION and the recipient of the service is a registered agent, the name of the
   * registered agent can be set via this interface.
   * 
   * @param agentForService
   *          agentForService
   */
  public void setAgentForService(String agentForService) {
    this.agentForService = agentForService;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Sets the ethnicity
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #ETHNICITY_AFRICAN_AMERICAN}</LI>
   * <LI>{@link #ETHNICITY_ASIAN_AMERICAN}</LI>
   * <LI>{@link #ETHNICITY_CAUCASIAN}</LI>
   * <LI>{@link #ETHNICITY_HISPANIC}</LI>
   * <LI>{@link #ETHNICITY_LATINO}</LI>
   * <LI>{@link #ETHNICITY_MIDDLE_EASTERN}</LI>
   * <LI>{@link #ETHNICITY_NATIVE_AMERICAN}</LI>
   * <LI>{@link #ETHNICITY_NATIVE_HAWAIIAN}</LI>
   * <LI>{@link #ETHNICITY_OTHER}</LI>
   * <LI>{@link #ETHNICITY_BLANK}</LI>
   * </UL>
   * 
   * @param ethnicity
   *          ethnicity
   */
  public void setEthnicity(String ethnicity) {
    this.ethnicity = ethnicity;
  }

  /**
   * Sets the eyes field
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #EYES_AMBER}</LI>
   * <LI>{@link #EYES_BLACK}</LI>
   * <LI>{@link #EYES_BLUE}</LI>
   * <LI>{@link #EYES_BROWN}</LI>
   * <LI>{@link #EYES_GRAY}</LI>
   * <LI>{@link #EYES_GREEN}</LI>
   * <LI>{@link #EYES_HAZEL}</LI>
   * <LI>{@link #EYES_OTHER}</LI>
   * <LI>{@link #EYES_BLANK}</LI>
   * </UL>
   * 
   * @param eyes
   *          eyes
   */
  public void setEyes(String eyes) {
    this.eyes = eyes;
  }

  /**
   * Sets the gender
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #GENDER_MALE}</LI>
   * <LI>{@link #GENDER_FEMALE}</LI>
   * <LI>{@link #GENDER_OTHER}</LI>
   * <LI>{@link #GENDER_BLANK}</LI>
   * </UL>
   * 
   * @param gender
   *          gender
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * Sets the hair field
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #HAIR_BALD}</LI>
   * <LI>{@link #HAIR_BLACK}</LI>
   * <LI>{@link #HAIR_BLOND}</LI>
   * <LI>{@link #HAIR_BROWN}</LI>
   * <LI>{@link #HAIR_GRAY}</LI>
   * <LI>{@link #HAIR_RED}</LI>
   * <LI>{@link #HAIR_WHITE}</LI>
   * <LI>{@link #HAIR_OTHER}</LI>
   * <LI>{@link #HAIR_BLANK}</LI>
   * </UL>
   * 
   * @param hair
   *          hair
   */
  public void setHair(String hair) {
    this.hair = hair;
  }

  /**
   * Sets the height1 field (the "foot" part of the height)
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #HEIGHT_3_FT}</LI>
   * <LI>{@link #HEIGHT_4_FT}</LI>
   * <LI>{@link #HEIGHT_5_FT}</LI>
   * <LI>{@link #HEIGHT_6_FT}</LI>
   * <LI>{@link #HEIGHT_7_FT}</LI>
   * <LI>{@link #HEIGHT_8_FT}</LI>
   * <LI>{@link #HEIGHT_9_FT}</LI>
   * <LI>{@link #HEIGHT_BLANK}</LI>
   * </UL>
   * 
   * @param height1
   *          "feet" part of height
   */
  public void setHeight1(String height1) {
    this.height1 = height1;
  }

  /**
   * Sets the height2 field (the "inch" part of the height)
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #HEIGHT_1_IN}</LI>
   * <LI>{@link #HEIGHT_2_IN}</LI>
   * <LI>{@link #HEIGHT_3_IN}</LI>
   * <LI>{@link #HEIGHT_4_IN}</LI>
   * <LI>{@link #HEIGHT_5_IN}</LI>
   * <LI>{@link #HEIGHT_6_IN}</LI>
   * <LI>{@link #HEIGHT_7_IN}</LI>
   * <LI>{@link #HEIGHT_8_IN}</LI>
   * <LI>{@link #HEIGHT_9_IN}</LI>
   * <LI>{@link #HEIGHT_10_IN}</LI>
   * <LI>{@link #HEIGHT_11_IN}</LI>
   * <LI>{@link #HEIGHT_12_IN}</LI>
   * <LI>{@link #HEIGHT_BLANK}</LI>
   * </UL>
   * 
   * @param height2
   *          inches part of height
   */
  public void setHeight2(String height2) {
    this.height2 = height2;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Sets the relationship field
   * <P>
   * This field typically uses pre-defined values, but can use a custom value if desired. The "known" values are
   * <UL>
   * <LI>{@link #RELATIONSHIP_AUNT}</LI>
   * <LI>{@link #RELATIONSHIP_BOYFRIEND}</LI>
   * <LI>{@link #RELATIONSHIP_BROTHER}</LI>
   * <LI>{@link #RELATIONSHIP_COUSIN}</LI>
   * <LI>{@link #RELATIONSHIP_DAUGHTER}</LI>
   * <LI>{@link #RELATIONSHIP_FATHER}</LI>
   * <LI>{@link #RELATIONSHIP_GIRLFRIEND}</LI>
   * <LI>{@link #RELATIONSHIP_GRANDFATHER}</LI>
   * <LI>{@link #RELATIONSHIP_GRANDMOTHER}</LI>
   * <LI>{@link #RELATIONSHIP_HUSBAND}</LI>
   * <LI>{@link #RELATIONSHIP_MOTHER}</LI>
   * <LI>{@link #RELATIONSHIP_PARTNER}</LI>
   * <LI>{@link #RELATIONSHIP_NEPHEW}</LI>
   * <LI>{@link #RELATIONSHIP_NIECE}</LI>
   * <LI>{@link #RELATIONSHIP_SISTER}</LI>
   * <LI>{@link #RELATIONSHIP_SON}</LI>
   * <LI>{@link #RELATIONSHIP_UNCLE}</LI>
   * <LI>{@link #RELATIONSHIP_WIFE}</LI>
   * <LI>{@link #RELATIONSHIP_OTHER}</LI>
   * <LI>{@link #RELATIONSHIP_BLANK}</LI>
   * </UL>
   * 
   * @param relationship
   *          relationship
   */
  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }

  /**
   * Sets the type of the recipient
   * <P>
   * This field uses pre-defined values. Allowed values are:
   * <UL>
   * <LI>{@link #RECIPIENT_TYPE_INDIVIDUAL}</LI>
   * <LI>{@link #RECIPIENT_TYPE_ORGANIZATION}</LI>
   * </UL>
   * 
   * @param type
   *          type
   */
  public void setType(String type) {
    this.type = type;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }
}
