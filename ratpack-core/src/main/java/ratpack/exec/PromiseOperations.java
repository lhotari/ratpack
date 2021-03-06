/*
 * Copyright 2014 the original author or authors.
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

package ratpack.exec;

import ratpack.func.Action;
import ratpack.func.Function;
import ratpack.func.NoArgAction;
import ratpack.func.Predicate;

public interface PromiseOperations<T> {

  <O> Promise<O> map(Function<? super T, ? extends O> function);

  <O> Promise<O> flatMap(Function<? super T, ? extends Promise<O>> function);

  Promise<T> route(Predicate<? super T> predicate, Action<? super T> action);

  Promise<T> onNull(NoArgAction action);

}
