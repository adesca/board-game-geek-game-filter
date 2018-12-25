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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "GAME_MECHANICS_JOIN_TABLE",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "mechanics_id")
    )
    List<Mechanic> mechanics;


}
