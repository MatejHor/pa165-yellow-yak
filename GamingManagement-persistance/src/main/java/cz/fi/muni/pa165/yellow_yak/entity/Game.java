package cz.fi.muni.pa165.yellow_yak.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Matej Horniak
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name="game",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private LocalDateTime createdAt;

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Game)) return false;
        Game game = (Game) obj;
        return Objects.equals(getName(), game.getName());
    }
}

