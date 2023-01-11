package event.hoprxi.domain.model;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import event.hoprxi.domain.model.disruptor.DomainEventWrap;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-08
 */
public interface Handler extends EventHandler<DomainEventWrap>, WorkHandler<DomainEventWrap> {
}
