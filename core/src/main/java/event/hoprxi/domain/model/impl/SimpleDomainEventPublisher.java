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
package event.hoprxi.domain.model.impl;

import event.hoprxi.domain.model.DomainEvent;
import event.hoprxi.domain.model.DomainEventPublisher;
import event.hoprxi.domain.model.DomainEventSubscriber;

import java.util.Collection;
import java.util.List;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 2019-10-05
 */
public final class SimpleDomainEventPublisher implements DomainEventPublisher {
    private static final ThreadLocal<DomainEventPublisher> instance = new ThreadLocal<DomainEventPublisher>() {
        protected DomainEventPublisher initialValue() {
            return new SimpleDomainEventPublisher();
        }
    };
    private boolean publishing;
    @SuppressWarnings("rawtypes")
    private List<DomainEventSubscriber> subscribers;

    public static DomainEventPublisher instance() {
        return instance.get();
    }

    private boolean hasSubscribers() {
        return this.subscribers() != null;
    }

    private boolean isPublishing() {
        return this.publishing;
    }

    private void setPublishing(boolean aFlag) {
        this.publishing = aFlag;
    }

    public <T extends DomainEvent> void publish(T event) {
        if (!this.isPublishing() && this.hasSubscribers()) {

            try {
                this.setPublishing(true);

                Class<?> eventType = event.getClass();

                @SuppressWarnings("unchecked")
                List<DomainEventSubscriber<T>> allSubscribers = this.subscribers();

                for (DomainEventSubscriber<T> subscriber : allSubscribers) {
                    Class<?> subscribedToType = subscriber.subscribedToEventType();

                    if (eventType == subscribedToType || subscribedToType == DomainEvent.class) {
                        subscriber.handleEvent(event);
                    }
                }

            } finally {
                this.setPublishing(false);
            }
        }
    }

    public <T> void publish(T event) {
        // TODO Auto-generated method stub

    }

    public void publishAll(Collection<DomainEvent> events) {
        // TODO Auto-generated method stub

    }

    public void reset() {
        // TODO Auto-generated method stub

    }

    public <T> void subscribe(T domainEventSubscriber) {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("rawtypes")
    private List subscribers() {
        return this.subscribers;
    }

    public <T> void unsubscribe(T domainEventSubscriber) {
        // TODO Auto-generated method stub

    }

}
