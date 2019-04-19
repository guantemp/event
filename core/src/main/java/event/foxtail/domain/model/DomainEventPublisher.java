/*
 * Copyright 2019 www.foxtail.cc All Rights Reserved.
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
package event.foxtail.domain.model;

import java.util.Collection;

/**
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @version 0.0.1 2019-04-19
 * @since JDK8.0
 */
public interface DomainEventPublisher {

    /**
     * @param event
     */
    <T> void publish(T event);

    /**
     * @param events
     */
    void publishAll(Collection<DomainEvent> events);

    /**
     *
     */
    void reset();

    /**
     * @param domainEventSubscriber
     */
    <T> void subscribe(T domainEventSubscriber);

    /**
     * @param domainEventSubscriber
     */
    <T> void unsubscribe(T domainEventSubscriber);
}
