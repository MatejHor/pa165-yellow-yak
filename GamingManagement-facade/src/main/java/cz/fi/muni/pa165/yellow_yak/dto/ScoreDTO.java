package cz.fi.muni.pa165.yellow_yak.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author matho
 */
@Getter
@Setter
@NoArgsConstructor
public class ScoreDTO {
    private Long id;
    @NotNull
    private PlayerDTO player;
    @NotNull
    private CompetitionDTO competition;
    private int placement;
    private String result;
    private String stats;
    @CreatedDate
    private LocalDateTime createdAt;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreDTO)) return false;
        ScoreDTO score = (ScoreDTO) o;
        return Objects.equals(getPlacement(), score.getPlacement()) &&
                Objects.equals(getResult(), score.getResult()) &&
                Objects.equals(getStats(), score.getStats());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlacement(), getResult(), getStats());
    }

}
