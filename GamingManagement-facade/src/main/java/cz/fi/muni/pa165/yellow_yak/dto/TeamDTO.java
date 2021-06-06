package cz.fi.muni.pa165.yellow_yak.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO layer for Team
 *
 * @author oreqizer
 */
@Getter
@Setter
@NoArgsConstructor
public class TeamDTO {
    private Long id;
    @NotNull
    private String name;

    private LocalDate createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamDTO)) return false;

        TeamDTO teamDTO = (TeamDTO) o;

        if (!name.equals(teamDTO.name)) return false;
        return Objects.equals(createdAt, teamDTO.createdAt);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
