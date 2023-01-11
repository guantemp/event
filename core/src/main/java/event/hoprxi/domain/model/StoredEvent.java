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

import java.time.LocalDateTime;
import java.util.StringJoiner;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 2019-10-05
 */
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class StoredEvent {
    private DomainEvent eventBody;
    //private int version;
    private long id;
    private LocalDateTime occurredOn;
    private String eventType;

    public StoredEvent(long id, String eventType, DomainEvent eventBody) {
        this.setId(id);
        this.setEventType(eventType);
        this.setEventBody(eventBody);
        this.occurredOn = LocalDateTime.now();
        ;
    }

    public DomainEvent eventBody() {
        return eventBody;
    }

    public long id() {
        return id;
    }

    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    private void setEventBody(DomainEvent eventBody) {
        this.eventBody = eventBody;
    }

    private void setId(long id) {
        this.id = id;
    }

    private void setEventType(String eventType) {
        if (eventType == null)
            throw new IllegalArgumentException("The event type name is required.");
        if (eventType.length() < 1 || eventType.length() > 94)
            throw new IllegalArgumentException("The event type name must be 94 characters or less.");
        this.eventType = eventType;
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass = null;
        T domainEvent = null;
        /*
        try {
            domainEventClass = (Class<T>) Class.forName(typeName);
           ObjectMapper mapper = new ObjectMapper();
            domainEvent = mapper.readValue(eventBody, domainEventClass);
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("Class load error, because: " + e.getMessage());
        }
        */
        return domainEvent;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StoredEvent.class.getSimpleName() + "[", "]")
                .add("eventBody='" + eventBody + "'")
                .add("id=" + id)
                .add("occurredOn=" + occurredOn)
                .add("typeName='" + eventType + "'")
                .toString();
    }

    public String eventType() {
        return eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoredEvent)) return false;

        StoredEvent that = (StoredEvent) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
