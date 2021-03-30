package projectweek.team22.model.repository;


import projectweek.team22.model.entity.Wedstrijd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WedstrijdRepository extends JpaRepository<Wedstrijd,Long> {

    Wedstrijd findWedstrijdsByOmschrijving(String omschrijving);
    List<Wedstrijd> findWedstrijdsByLocatie(String locatie);

    int deleteById(long id);

    @Query("SELECT t FROM Wedstrijd t WHERE lower(t.omschrijving) LIKE %?1%")
    List<Wedstrijd> findWedstrijdsByOmschrijvingContaining(String omschrijving);

    @Query("SELECT t FROM Wedstrijd t WHERE t.omschrijving=?1 AND t.id <> ?2")
    Wedstrijd findWedstrijdByOmschrijvingNotSelf(String omschrijving, long id);

}
