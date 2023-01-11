package event.hoprxi.infrastruture.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import event.hoprxi.domain.model.DomainEvent;
import event.hoprxi.domain.model.StoredEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import salt.hoprxi.id.LongId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Objects;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2023-01-08
 */
public class PsqlStoredEventRepository implements StoredEventRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PsqlStoredEventRepository.class);
    private final String databaseName;
    private final ObjectMapper mapper;
    private final String prefix = "insert into ";
    private final String suffix = "(id,occurredOn,event_type,version,value) values(?,?,?,?,?::jsonb) ";

    public PsqlStoredEventRepository(String databaseName) {
        this.databaseName = Objects.requireNonNull(databaseName, "The databaseName parameter is required");
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .addModule(new Jdk8Module())
                .build();
    }

    @Override
    public long nextIdentity() {
        return LongId.generate();
    }

    @Override
    public void save(DomainEvent domainEvent) {
        try (Connection connection = PsqlUtil.getConnection(databaseName)) {
            final String EVENT_SQL = "insert into catalog(id,occurredOn,event_type,version,value) values(?,?,?,?,?::jsonb)";
            PreparedStatement ps = connection.prepareStatement(EVENT_SQL);
            ps.setLong(1, LongId.generate());
            ps.setTimestamp(2, Timestamp.from(domainEvent.occurredOn().atZone(ZoneId.systemDefault()).toInstant()));
            ps.setString(3, domainEvent.getClass().getSimpleName());
            ps.setInt(4, domainEvent.version());
            ps.setString(5, mapper.writeValueAsString(domainEvent));
            ps.executeUpdate();
        } catch (JsonProcessingException | SQLException e) {
            LOGGER.error("Can't save domainEvent {}", domainEvent, e);
            throw new RuntimeException(e);
        }
    }
}
