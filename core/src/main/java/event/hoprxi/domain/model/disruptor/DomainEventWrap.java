package event.hoprxi.domain.model.disruptor;

import event.hoprxi.domain.model.DomainEvent;

import java.util.Objects;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-08
 */
public class DomainEventWrap {
    private DomainEvent event;

    public DomainEvent getEvent() {
        return event;
    }

    public void setEvent(DomainEvent event) {
        this.event = Objects.requireNonNull(event, "event is requires");
    }
}
