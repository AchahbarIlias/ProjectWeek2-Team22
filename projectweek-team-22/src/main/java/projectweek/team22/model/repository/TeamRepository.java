package projectweek.team22.model.repository;

import projectweek.team22.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findTeamByName(String name);

    int deleteTeamById(long id);

    List<Team> findTeamsByMaxAge(int maxAge);

    List<Team> findTeamsByMinAgeAfter(int minAge);

    @Query("SELECT t FROM Team t WHERE lower(t.name) LIKE %?1%")
    List<Team> findTeamsByNameContaining(String string);

    @Query("SELECT t FROM Team t WHERE t.name=?1 AND t.id <> ?2")
    Team findTeamByNameNotSelf(String name, long id);

}
