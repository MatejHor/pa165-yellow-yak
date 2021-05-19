package cz.fi.muni.pa165.yellow_yak.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name="member"
)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotNull LocalDate createdAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Player player;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Member)) return false;
        Member member = (Member) obj;
        return Objects.equals(getCreatedAt(), member.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedAt());
    }
}
