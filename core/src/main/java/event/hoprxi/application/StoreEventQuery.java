package event.hoprxi.application;

import event.hoprxi.domain.model.StoredEvent;

import java.util.Collection;
import java.util.Map;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-10
 */
public interface StoreEventQuery {
    /**
     * @return
     */
    long count();

    Map<String, Long> countType(String type);

    Collection<StoredEvent> storedEventsBetween(long low, long high);

    /**
     * @param eventId
     * @return
     */
    Collection<StoredEvent> storedEventsSince(long eventId);
}
