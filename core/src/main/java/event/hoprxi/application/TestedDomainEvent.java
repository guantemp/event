package event.hoprxi.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import event.hoprxi.domain.model.DomainEvent;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-11
 */
public class TestedDomainEvent implements DomainEvent {

    private final String name;

    private final LocalDateTime occurredOn;
    private final int version = 1;

    private final long id;

    public TestedDomainEvent(long id, String name) {
        this.id = id;
        this.name = name;
        this.occurredOn = LocalDateTime.now();
    }

    @JsonProperty
    public String name() {
        return name;
    }

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "GMT+8")
    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    @Override
    public int version() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestedDomainEvent)) return false;

        TestedDomainEvent that = (TestedDomainEvent) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TestedDomainEvent.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("occurredOn=" + occurredOn)
                .add("version=" + version)
                .toString();
    }

    @JsonProperty
    public long id() {
        return id;
    }
}
