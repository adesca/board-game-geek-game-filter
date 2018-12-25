package app.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Mechanic {

    @Id
    @GeneratedValue
    Long Id;

    @NonNull
    String value;

    @Override
    public boolean equals(Object other) {
        if (other instanceof Mechanic) {
            return this.value.equals(((Mechanic) other).value);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
