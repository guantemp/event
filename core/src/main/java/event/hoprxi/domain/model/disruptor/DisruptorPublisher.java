package event.hoprxi.domain.model.disruptor;

import com.lmax.disruptor.RingBuffer;
import event.hoprxi.domain.model.DomainEvent;
import event.hoprxi.domain.model.Publisher;

import java.util.Objects;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-08
 */
public class DisruptorPublisher implements Publisher {
    private final RingBuffer<DomainEventWrap> ringBuffer;

    public DisruptorPublisher(RingBuffer<DomainEventWrap> ringBuffer) {
        this.ringBuffer = Objects.requireNonNull(ringBuffer, "ringBuffer is required");
    }

    public void publish(DomainEvent event) {
        long sequence = ringBuffer.next();
        try {
            DomainEventWrap wrap = ringBuffer.get(sequence);
            wrap.setEvent(event);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
