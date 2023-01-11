package event.hoprxi;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import event.hoprxi.application.TestedDomainEvent;
import event.hoprxi.domain.model.DomainEvent;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        ;
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        DomainEvent event = new TestedDomainEvent(1241l, "暗无天日文爱图而我一个而后各位");
        System.out.println(mapper.writeValueAsString(event));
    }
}
