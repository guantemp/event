package event.hoprxi.infrastruture.persistence;

import event.hoprxi.application.TestedDomainEvent;
import event.hoprxi.domain.model.DomainEvent;
import event.hoprxi.domain.model.StoredEventRepository;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-11
 */
public class PsqlStoredEventRepositoryTest {
    private final StoredEventRepository repository = new PsqlStoredEventRepository("event");

    @Test
    public void testNextIdentity() {
        System.out.println(repository.nextIdentity());
    }

    @Test
    public void testSave() throws NoSuchAlgorithmException {
        SecureRandom r = SecureRandom.getInstance("SHA1PRNG");
        DomainEvent event1 = new TestedDomainEvent(r.nextLong(), "二恶烷");
        repository.save(event1);
        event1 = new TestedDomainEvent(r.nextLong(), "知道GF啥的啥的干啥 耳温枪桶很委屈在西安发大水");
        repository.save(event1);
    }
}