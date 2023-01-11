package event.hoprxi.domain.model.disruptor;

import com.lmax.disruptor.EventFactory;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-11
 */
public class DomainEventWrapFactory implements EventFactory<DomainEventWrap> {
    @Override
    public DomainEventWrap newInstance() {
        return new DomainEventWrap();
    }
}
