package app.simulwatch.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import sun.util.resources.cldr.en.TimeZoneNames_en_ZA;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import static java.time.format.DateTimeFormatter.*;

@Data
@Builder
@AllArgsConstructor
public class SocketMessage {
    private String name;
    private String room;


    private String timestamp;

    public SocketMessage() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        format.setTimeZone(tz);
        timestamp = format.format(new Date());

    }

}
