package cz.fi.muni.pa165.yellow_yak.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Matej Knazik
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "team",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private LocalDate createdAt;

    @Override
    public int hashCode() {
       return Objects.hash(getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return Objects.equals(getName(), team.getName());
    }
}
