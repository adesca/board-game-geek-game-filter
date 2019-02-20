package app.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
