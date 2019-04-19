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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

/***
 * @author <a href="mailto:myis1000@gmail.com">guan xiangHuan</a>
 * @since JDK7.0
 * @version 0.0.1 2015年11月3日
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class StoredEvent {
    private String eventBody;
    //private int version;
    private long eventId;
    private Date occurredOn;
    private String typeName;

    public StoredEvent(long eventId, String typeName, String eventBody) {
        this.occurredOn = new Date();
        this.setEventId(eventId);
        this.setTypeName(typeName);
        this.setEventBody(eventBody);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StoredEvent other = (StoredEvent) obj;
        if (eventId != other.eventId)
            return false;
        return true;
    }

    public String eventBody() {
        return eventBody;
    }

    public long eventId() {
        return eventId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (eventId ^ (eventId >>> 32));
        return result;
    }

    public Date occurredOn() {
        return occurredOn;
    }

    protected void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    protected void setEventId(long eventId) {
        this.eventId = eventId;
    }

    protected void setTypeName(String typeName) {
        if (typeName == null)
            throw new IllegalArgumentException("The event type name is required.");
        if (typeName.length() < 1 || typeName.length() > 100)
            throw new IllegalArgumentException("The event type name must be 100 characters or less.");
        this.typeName = typeName;
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass = null;
        T domainEvent = null;
        try {
            domainEventClass = (Class<T>) Class.forName(typeName);
            ObjectMapper mapper = new ObjectMapper();
            domainEvent = mapper.readValue(eventBody, domainEventClass);
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("Class load error, because: " + e.getMessage());
        }
        return domainEvent;
    }

    @Override
    public String toString() {
        return "StoredEvent [eventBody=" + eventBody + ", eventId=" + eventId + ", occurredOn=" + occurredOn
                + ", typeName=" + typeName + "]";
    }

    public String typeName() {
        return typeName;
    }
}
