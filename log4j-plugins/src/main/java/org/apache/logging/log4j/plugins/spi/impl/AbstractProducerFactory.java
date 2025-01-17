/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */

package org.apache.logging.log4j.plugins.spi.impl;

import org.apache.logging.log4j.plugins.spi.Bean;
import org.apache.logging.log4j.plugins.spi.InjectionPoint;
import org.apache.logging.log4j.plugins.spi.ProducerFactory;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Objects;

abstract class AbstractProducerFactory implements ProducerFactory {
    final Bean<?> declaringBean;
    final Member producerMember;
    final Method disposerMethod;
    final Collection<InjectionPoint> disposerInjectionPoints;

    AbstractProducerFactory(final Bean<?> declaringBean, final Member producerMember, final Method disposerMethod,
                            final Collection<InjectionPoint> disposerInjectionPoints) {
        this.declaringBean = declaringBean;
        this.producerMember = Objects.requireNonNull(producerMember);
        this.disposerMethod = disposerMethod;
        this.disposerInjectionPoints = Objects.requireNonNull(disposerInjectionPoints);
    }
}
