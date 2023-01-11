package event.hoprxi.application;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.TimeoutException;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import event.hoprxi.domain.model.disruptor.DisruptorPublisher;
import event.hoprxi.domain.model.disruptor.DomainEventWrap;
import event.hoprxi.domain.model.disruptor.DomainEventWrapFactory;
import event.hoprxi.domain.model.impl.StoredEventHandler;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.Executors;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-10
 */
public class Bootstrap {
    private static final EventFactory<DomainEventWrap> domainEventWrapFactory = new DomainEventWrapFactory();

    public void start() throws TimeoutException, NoSuchAlgorithmException {
        final Disruptor<DomainEventWrap> disruptor = new Disruptor<>(
                domainEventWrapFactory,
                16,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );
        disruptor.handleEventsWith(new StoredEventHandler());
        disruptor.start();
        RingBuffer<DomainEventWrap> ringBuffer = disruptor.getRingBuffer();
        DisruptorPublisher publisher = new DisruptorPublisher(ringBuffer);
        SecureRandom r = SecureRandom.getInstance("SHA1PRNG");
        for (int i = 0; i < 128; i++) {
            publisher.publish(new TestedDomainEvent(r.nextLong(), "我失策失策沙发上"));
        }
        //publisher.publish(new TestedDomainEvent(r.nextLong(), "使得该发生第三方"));
        disruptor.shutdown();
    }
}
