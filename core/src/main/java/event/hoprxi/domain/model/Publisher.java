package event.hoprxi.domain.model;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-08
 */
public interface Publisher {
    void publish(DomainEvent event);
}
