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

package ratpack.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import ratpack.launch.LaunchConfig;
import ratpack.registry.Registry;
import ratpack.spring.internal.SpringBackedRegistry;

public abstract class Spring {
  public static Registry registry(ApplicationContext applicationContext) {
    return new SpringBackedRegistry(applicationContext);
  }

  public static Registry run(Class<?> springApplicationClass) {
    SpringApplication springApplication =  new SpringApplication(springApplicationClass);
    springApplication.setMainApplicationClass(springApplicationClass);
    return registry(springApplication.run(new String[0]));
  }
}
