package projectweek.team22.model.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Wedstrijd {

    @NotBlank(message = "omschrijving.is.missing")
    @Size(max = 50,message = "omschrijving.is.to.long")
    //TODO try @UniqueElements(message = "moet.unique")
    private String omschrijving;

    @NotBlank(message = "location.is.missing")
    private String locatie;

    @NotNull(message = "aantal.is.missing")
    @Positive(message = "aantal.moet.positief")
    private Integer aantalToeschouwers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setAantalToeschouwers(int aantalToeschouwers) {
        this.aantalToeschouwers = aantalToeschouwers;
    }

    public int getAantalToeschouwers() {
        return aantalToeschouwers;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
