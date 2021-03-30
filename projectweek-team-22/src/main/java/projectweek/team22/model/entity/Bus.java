package projectweek.team22.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "bus.email.missing")
    @Email(message = "bus.email.invalid")
    private String email;

    @NotNull(message = "bus.seats.missing")
    @Min(value=1, message="bus.seats.min.invalid")
    @Max(value=100, message="bus.seats.max.invalid")
    private Integer seats;

    @NotBlank(message = "bus.departureLocation.missing")
    private String departureLocation;

    @NotBlank(message = "bus.name.missing")
    private String name;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getDepartureLocation() { return departureLocation; }
    public void setDepartureLocation(String departureLocation) { this.departureLocation = departureLocation; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
