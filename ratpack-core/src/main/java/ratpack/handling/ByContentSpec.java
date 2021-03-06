/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ratpack.handling;

/**
 * A specification of how to respond to a request, based on the requested content type (i.e. the request's Accept header).
 *
 * @see Context#byContent(ratpack.func.Action)
 */
public interface ByContentSpec {

  /**
   * Specifies that the given handler should be used if the client wants content of the given mime type.
   *
   * @param mimeType The mime type to register for
   * @param handler The handler to invoke if the content type matches
   * @return this
   */
  ByContentSpec type(String mimeType, Handler handler);

  /**
   * Specifies that the given handler should be used if the client wants content of type "text/plain".
   *
   * @param handler The handler to invoke if the content type matches
   * @return this
   */
  ByContentSpec plainText(Handler handler);

  /**
   * Specifies that the given handler should be used if the client wants content of type "text/html".
   *
   * @param handler The handler to invoke if the content type matches
   * @return this
   */
  ByContentSpec html(Handler handler);

  /**
   * Specifies that the given handler should be used if the client wants content of type "application/json".
   *
   * @param handler The handler to invoke if the content type matches
   * @return this
   */
  ByContentSpec json(Handler handler);

  /**
   * Specifies that the given handler should be used if the client wants content of type "application/xml".
   *
   * @param handler The handler to invoke if the content type matches
   * @return this
   */
  ByContentSpec xml(Handler handler);

}
