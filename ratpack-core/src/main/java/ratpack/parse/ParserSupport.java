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

package ratpack.parse;

import com.google.common.reflect.TypeToken;

/**
 * A convenience superclass for {@link Parser} implementations.
 * <p>
 * Specializations only need to implement the {@link Parser#parse(ratpack.handling.Context, ratpack.http.TypedData, Parse)} method.
 * <pre class="java">
 * import ratpack.handling.Handler;
 * import ratpack.handling.Context;
 * import ratpack.http.TypedData;
 * import ratpack.parse.Parse;
 * import ratpack.parse.ParserSupport;
 * import ratpack.parse.ParseException;
 * import ratpack.util.Types;
 * import ratpack.func.Action;
 * import ratpack.registry.RegistrySpec;
 *
 * import java.io.UnsupportedEncodingException;
 *
 * import ratpack.test.UnitTest;
 * import ratpack.test.handling.HandlingResult;
 * import ratpack.test.handling.RequestFixture;
 *
 * public class Example {
 *
 *   // The parse options
 *   public static class StringParseOpts {
 *     private int maxLength;
 *
 *     public StringParseOpts(int maxLength) {
 *       this.maxLength = maxLength;
 *     }
 *
 *     public int getMaxLength() {
 *       return maxLength;
 *     }
 *   }
 *
 *   // A parser for this type
 *   public static class MaxLengthStringParser extends ParserSupport&lt;StringParseOpts&gt; {
 *     public MaxLengthStringParser() {
 *       super("text/plain");
 *     }
 *
 *     public &lt;T&gt; T parse(Context context, TypedData requestBody, Parse&lt;T, StringParseOpts&gt; parse) throws UnsupportedEncodingException {
 *       if (!parse.getType().getRawType().equals(String.class)) {
 *         return null;
 *       }
 *
 *       String rawString = requestBody.getText();
 *       if (rawString.length() &lt; parse.getOpts().getMaxLength()) {
 *         return Types.cast(rawString);
 *       } else {
 *         return Types.cast(rawString.substring(0, parse.getOpts().getMaxLength()));
 *       }
 *     }
 *   }
 *
 *   public static class ToUpperCaseHandler implements Handler {
 *     public void handle(Context context) throws ParseException {
 *       String string = context.parse(String.class, new StringParseOpts(5));
 *       context.render(string);
 *     }
 *   }
 *
 *   // unit test
 *   public static void main(String[] args) {
 *     HandlingResult result = UnitTest.handle(new ToUpperCaseHandler(), new Action&lt;RequestFixture&gt;() {
 *       public void execute(RequestFixture fixture) throws Exception {
 *         fixture
 *           .body("123456", "text/plain")
 *           .registry(new Action&lt;RegistrySpec&gt;() {
 *             public void execute(RegistrySpec registry) {
 *               registry.add(new MaxLengthStringParser());
 *             }
 *           });
 *       }
 *     });
 *
 *     assert result.rendered(String.class).equals("12345");
 *   }
 * }
 * </pre>
 *
 * @see NoOptParserSupport
 * @param <O> the type of option object this parser accepts
 */
abstract public class ParserSupport<O> implements Parser<O> {

  private final Class<O> optsType;
  private final String contentType;

  /**
   * Constructor.
   *
   * @param contentType the type of request this parser can handle
   */
  protected ParserSupport(String contentType) {
    this.contentType = contentType;

    TypeToken<O> typeToken = new TypeToken<O>(getClass()) {
    };

    if (typeToken.getType() instanceof Class) {
      @SuppressWarnings("unchecked") Class<O> rawType = (Class<O>) typeToken.getRawType();
      this.optsType = rawType;
    } else {
      throw new IllegalArgumentException("Type parameter O of ParserSupport must be a Class");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getContentType() {
    return contentType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Class<O> getOptsType() {
    return optsType;
  }

}
