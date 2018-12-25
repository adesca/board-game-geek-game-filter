package app.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Game {

    @Id
    @GeneratedValue
    Long id;

    Long rank;
    String bggUrl;
    String name;

    @ElementCollection
    @CollectionTable(
            name="mechanics"
    )
    List<String> mechanics;


}
