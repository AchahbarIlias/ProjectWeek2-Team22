package projectweek.team22.model.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "player.firstName.missing")
    private String firstName;

    @NotBlank(message = "player.lastName.missing")
    private String lastName;


    @NotNull(message = "player.number.missing")
    private Integer playerNumber;


    @NotNull(message = "player.age.missing")
    @Min(value=1, message="player.age.min.invalid")
    private Integer age;



    @Value("#{target.firstname + ' ' + target.lastname}")//dit moet nog getest worden
    private String fullName;

}
