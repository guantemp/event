package event.hoprxi.domain.model.impl;

import event.hoprxi.domain.model.DomainEvent;
import event.hoprxi.domain.model.Handler;
import event.hoprxi.domain.model.StoredEventRepository;
import event.hoprxi.domain.model.disruptor.DomainEventWrap;
import event.hoprxi.infrastruture.persistence.PsqlStoredEventRepository;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-08
 */
public class StoredEventHandler implements Handler {
    private StoredEventRepository repository = new PsqlStoredEventRepository("event");

    @Override
    public void onEvent(DomainEventWrap domainEventWrap, long l, boolean b) throws Exception {
        DomainEvent event = domainEventWrap.getEvent();
        repository.save(event);
        System.out.println(l);
        System.out.println(b);
    }

    @Override
    public void onEvent(DomainEventWrap domainEventWrap) throws Exception {

    }
}
