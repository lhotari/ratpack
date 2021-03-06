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

package ratpack.registry

import spock.lang.Specification

import static ratpack.registry.Registries.join
import static ratpack.registry.Registries.just

class RegistryBuilderSpec extends Specification {

  def "can retrieve successfully"() {
    given:
    def c = just(String, "foo")
    def p = just(Integer, 2)
    def n = join(p, c)

    expect:
    n.get(String) == "foo"
    n.get(Number) == 2 // delegating to parent
    n.getAll(Object).toList() == ["foo", 2]
  }

}
