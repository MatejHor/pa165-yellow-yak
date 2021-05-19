package cz.fi.muni.pa165.yellow_yak.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Lukas Mikula
 */
@Getter
@Setter
@NoArgsConstructor
public class CompetitionDTO {
    private Long id;
    @NotNull
    private GameDTO game;
    @NotNull
    private String name;
    private String prices;

    private @NotNull LocalDate startedAt;
    private LocalDate finishedAt;
    private LocalDate createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof CompetitionDTO)) return false;
        CompetitionDTO competition = (CompetitionDTO) o;
        return Objects.equals(getName(), competition.getName()) &&
                Objects.equals(getStartedAt(), competition.getStartedAt()) &&
                Objects.equals(getFinishedAt(), competition.getFinishedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStartedAt(), getFinishedAt());
    }

}
