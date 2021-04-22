package cz.fi.muni.pa165.yellow_yak.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @NotNull
    private String name;

    private String prices;

    @NotNull
    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    @CreatedDate
    private LocalDateTime createdAt;

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
