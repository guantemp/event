/*
 * Copyright 2019 www.hoprxi.com All Rights Reserved.
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
package event.hoprxi.domain.model;

/**
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @version 0.0.1 2019-04-19
 * @since JDK8.0
 */
public interface DomainEventSubscriber<T extends DomainEvent> {
    /**
     * Processing domain events
     *
     * @param domainEvent
     */
    void handleEvent(T domainEvent);

    /**
     * @return
     */
    Class<T> subscribedToEventType();
}
