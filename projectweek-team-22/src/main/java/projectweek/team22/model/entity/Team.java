package projectweek.team22.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "team.name.missing")
    private String name;

    @NotNull(message = "team.minAge.missing")
    @Min(value=1, message="team.minAge.min.invalid")
    @Max(value=120, message="team.minAge.max.invalid")
    private Integer minAge;

    @NotNull(message = "team.maxAge.missing")
    @Min(value=1, message="team.maxAge.min.invalid")
    @Max(value=120, message="team.maxAge.max.invalid")
    private Integer maxAge;

    @NotNull(message = "team.numberOfPlayers.missing")
    @Min(value=1, message="team.numberOfPlayers.min.invalid")
    @Max(value=2000, message="team.numberOfPlayers.max.invalid")
    private Integer numberOfPlayers;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getMinAge() {
        return minAge;
    }
    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
