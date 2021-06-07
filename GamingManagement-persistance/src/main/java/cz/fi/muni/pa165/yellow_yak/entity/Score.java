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
 * Score entity
 *
 * @author oreqizer
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "score"
)
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Player player;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private int placement;

    private int result;

    @CreatedDate
    private LocalDate createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score = (Score) o;
        return Objects.equals(getPlacement(), score.getPlacement()) &&
                Objects.equals(getResult(), score.getResult()) &&
                Objects.equals(getCreatedAt(), score.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlacement(), getResult(), getCreatedAt());
    }
}
