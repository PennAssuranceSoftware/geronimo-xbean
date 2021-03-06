/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.xbean.kernel;

import java.util.Collections;
import java.util.Set;

/**
 * A basic service factory that always creates the supplied object.
 *
 * @author Dain Sundstrom
 * @version $Id$
 * @since 2.0
 */
public class StaticServiceFactory extends AbstractServiceFactory {
    private final Object service;
    private final ClassLoader classLoader;

    /**
     * Creates a non-restartable service factory which will simply returns the specified service from the createService
     * method.
     *
     * @param service the static to which this factory "creates"
     * @throws NullPointerException if service is null
     */
    public StaticServiceFactory(Object service) throws NullPointerException {
        if (service == null) throw new NullPointerException("service is null");
        this.service = service;
        ClassLoader cl = service.getClass().getClassLoader();
        if (cl == null) {
            cl = ClassLoader.getSystemClassLoader();
        }
        this.classLoader = cl;
    }

    /**
     * Creates a non-restartable service factory which will simply returns the specified service from the createService
     * method.
     *
     * @param service the static to which this factory "creates"
     * @param classLoader the classLoader used to load the service class
     * @throws NullPointerException if service or classLoader is null
     */
    public StaticServiceFactory(Object service, ClassLoader classLoader) throws NullPointerException {
        if (service == null) throw new NullPointerException("service is null");
        if (classLoader == null) throw new NullPointerException("classLoader is null");
        this.service = service;
        this.classLoader = classLoader;
    }

    public Class[] getTypes() {
        return new Class[]{service.getClass()};
    }

    /**
     * {@inheritDoc}
     */
    public boolean isRestartable() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public Set getOwnedServices() {
        return Collections.EMPTY_SET;
    }

    /**
     * Returns the static service instance.
     *
     * @param serviceContext ignored
     * @return the static service instance
     */
    public Object createService(ServiceContext serviceContext) {
        return service;
    }

    /**
     * This method is a noop.
     *
     * @param serviceContext ignored
     */
    public void destroyService(ServiceContext serviceContext) {
    }

    /**
     * {@inheritDoc}
     */
    public ClassLoader getClassLoader() {
        return classLoader;
    }
    
}
