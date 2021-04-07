package cz.fi.muni.pa165.yellow_yak.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author oreqizer
 */
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "competitor_id", nullable = false)
    private Competition competition;

    @NotNull
    private int index;

    @NotNull
    @CreatedDate
    private Date createdAt;

    public Score() {
    }

    public Long getId() {
        return id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score score = (Score) o;

        if (index != score.getIndex()) return false;
        if (!id.equals(score.getId())) return false;
        if (!competition.equals(score.getCompetition())) return false;
        return createdAt.equals(score.getCreatedAt());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCompetition().hashCode();
        result = 31 * result + getIndex();
        result = 31 * result + getCreatedAt().hashCode();
        return result;
    }
}
