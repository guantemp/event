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

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK7.0
 * @version 0.0.1 2015年11月3日
 */
public interface StoredEventRepository {
    /**
     * @return
     */
    long countStoredEvent();

    /**
     * @return
     */
    long nextIdentity();

    /**
     * @param domainEvent
     */
    void save(DomainEvent domainEvent);

    /**
     * @param low
     * @param high
     * @return
     */
    Collection<StoredEvent> storedEventsBetween(long low, long high);

    /**
     * @param eventId
     * @return
     */
    Collection<StoredEvent> storedEventsSince(long eventId);
}
