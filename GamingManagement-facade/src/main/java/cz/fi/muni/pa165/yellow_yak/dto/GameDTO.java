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
public class GameDTO {
    private Long id;
    @NotNull
    private String name;
    private LocalDate createdAt;

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof GameDTO)) return false;
        GameDTO game = (GameDTO) obj;
        return Objects.equals(getName(), game.getName());
    }
}
