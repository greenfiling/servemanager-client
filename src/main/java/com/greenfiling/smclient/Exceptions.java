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

package com.greenfiling.smclient;

/**
 * Project-specific exceptions
 *
 * @since 1.0.0
 * @author jetmore
 */
public class Exceptions {

  /**
   * Indicates that the API key was recognized but some other permission is preventing the request
   * <P>
   * The response from the server should include more detail and is included in the exception's message
   *
   * @since 1.0.0
   */
  public static class AccessForbiddenException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    public AccessForbiddenException(String message) {
      super(message);
    }
  }

  /**
   * Indicates a conflict
   * <P>
   * The response from the server should include more detail and is included in the exception's message
   *
   * @since 1.0.0
   */
  public static class ConflictException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    public ConflictException(String message) {
      super(message);
    }
  }

  /**
   * Indicates that the content type of the request was incorrect
   * <P>
   * Ensure that the Content-Type header is set to <code>application/json</code>.
   *
   * @since 1.0.0
   */
  public static class ContentTypeException extends Exception {
    private static final long serialVersionUID = 1L;

  }

  /**
   * Indicates an unrecognized or invalid API key
   * 
   * @since 1.0.0
   */
  public static class InvalidCredentialsException extends Exception {
    private static final long serialVersionUID = 1L;

  }

  /**
   * Indicates an invalid API endpoint
   * 
   * @since 1.0.0
   */
  public static class InvalidEndpointException extends Exception {
    private static final long serialVersionUID = 1L;

  }

  /**
   * Indicates an invalid request
   * <P>
   * The response from the server should include more detail and is included in the exception's message
   *
   * @since 1.0.0
   */
  public static class InvalidRequestException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    public InvalidRequestException(String message) {
      super(message);
    }
  }

  /**
   * Indicates that the requested record does not exist
   * <P>
   * The response from the server should include more detail and is included in the exception's message
   *
   * @since 1.0.0
   */
  public static class RecordNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    public RecordNotFoundException(String message) {
      super(message);
    }
  }

}
