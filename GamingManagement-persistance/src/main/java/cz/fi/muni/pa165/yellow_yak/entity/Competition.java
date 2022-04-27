package cz.fi.muni.pa165.yellow_yak.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Competition entity
 *
 * @author Lukas Mikula
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name="competition",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @NotNull
    private String name;

    private String prices;

    private @NotNull LocalDate startedAt;

    private LocalDate finishedAt;

    @CreatedDate
    private LocalDate createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Competition)) return false;
        Competition competition = (Competition) o;
        return Objects.equals(getName(), competition.getName()) &&
                Objects.equals(getStartedAt(), competition.getStartedAt()) &&
                Objects.equals(getFinishedAt(), competition.getFinishedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStartedAt(), getFinishedAt());
    }
}
