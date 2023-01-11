package event.hoprxi;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lmax.disruptor.TimeoutException;
import event.hoprxi.application.Bootstrap;
import event.hoprxi.application.TestedDomainEvent;
import event.hoprxi.domain.model.DomainEvent;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test(invocationCount = 1)
    public void shouldAnswerWithTrue() throws JsonProcessingException, NoSuchAlgorithmException {
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        SecureRandom r = SecureRandom.getInstance("SHA1PRNG");
        DomainEvent event = new TestedDomainEvent(r.nextLong(), "暗无天日文爱图而我一个而后各位");
        System.out.println(mapper.writeValueAsString(event));
    }

    @Test(invocationCount = 1)
    public void tojson() throws NoSuchAlgorithmException {
        SecureRandom r = SecureRandom.getInstance("SHA1PRNG");
        DomainEvent event = new TestedDomainEvent(r.nextLong(), "暗无天日文爱图而我一个而后各位");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JsonFactory jasonFactory = JsonFactory.builder().build();
        try (JsonGenerator generator = jasonFactory.createGenerator(output, JsonEncoding.UTF8)) {
            generator.writeStartObject();
            Field[] allFields = event.getClass().getDeclaredFields();
            for (Field field : allFields) {
                field.setAccessible(true);
                if (field.getType().equals(long.class))
                    generator.writeNumberField(field.getName(), field.getLong(event));
                else if (field.getType().equals(String.class))
                    generator.writeStringField(field.getName(), (String) field.get(event));
                else if (field.getType().equals(LocalDateTime.class))
                    generator.writeStringField(field.getName(), ((LocalDateTime) field.get(event)).format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss.SSSSSS")));
            }
            generator.writeEndObject();
            generator.flush();
        } catch (IOException e) {

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println(output);
    }

    @Test(invocationCount = 10, priority = 2, threadPoolSize = 4)
    public void disruptor() throws NoSuchAlgorithmException, InterruptedException, TimeoutException {
        final Bootstrap strap = new Bootstrap();
        strap.start();
    }
}
