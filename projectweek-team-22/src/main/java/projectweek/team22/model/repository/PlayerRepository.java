package projectweek.team22.model.repository;

import projectweek.team22.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository  extends JpaRepository<Player, Long> {


    Player findPlayerByFullName(String fullName);

    @Query("SELECT p FROM Player p WHERE p.fullName=?1 AND p.id <> ?2")
    Player findPlayerByFullNameNotSelf(String fullName, long id);

    List<Player> findPlayersByAge(Integer age);
}
