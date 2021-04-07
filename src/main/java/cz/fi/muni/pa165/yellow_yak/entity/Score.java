package cz.fi.muni.pa165.yellow_yak.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author oreqizer
 */
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "competitor_id", nullable = false)
    private Competitor competitor;

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

    public Competitor getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Competitor competitor) {
        this.competitor = competitor;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score that = (Score) o;

        return id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCompetitor().hashCode();
        result = 31 * result + getIndex();
        result = 31 * result + getCreatedAt().hashCode();
        return result;
    }
}
