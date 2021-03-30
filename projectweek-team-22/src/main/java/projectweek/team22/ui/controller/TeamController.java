package projectweek.team22.ui.controller;

import projectweek.team22.model.service.BasketballService;
import projectweek.team22.util.ServiceException;
import projectweek.team22.model.entity.Team;
import projectweek.team22.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TeamController {

    @Autowired
    private BasketballService service;

    @GetMapping("/team_overview")
    public String showTeamOverview(Model model) {
        model.addAttribute("overviewTitle", "team.overview");
        model.addAttribute("teams", service.getAllTeams());
        return "overview-team";
    }
    @GetMapping("/team_overview/namesort")
    public String showTeamOveviewNameSort(Model model) {
        model.addAttribute("overviewTitle", "team.sorted.name");
        model.addAttribute("teams", service.getAllTeamsSortedByName());
        return "overview-team";
    }
    @GetMapping("/team_overview/playersort")
    public String showTeamOverviewPlayerSort(Model model) {
        model.addAttribute("overviewTitle", "team.sorted.players");
        model.addAttribute("teams", service.getAllTeamsSortedByPlayers());
        return "overview-team";
    }
    @GetMapping("/team_overview/confirmupdate")
    public String showTeamOverviewConfirmationUpdate(Model model){
        model.addAttribute("confirmationMessage", "confirmation.update.team");
        model.addAttribute("overviewTitle", "team.overview");
        model.addAttribute("teams", service.getAllTeams());
        return "overview-team";
    }
    @GetMapping("/team_overview/confirmdelete")
    public String showTeamOverviewConfirmationDelete(Model model){
        model.addAttribute("confirmationMessage", "confirmation.delete.team");
        model.addAttribute("overviewTitle", "team.overview");
        model.addAttribute("teams", service.getAllTeams());
        return "overview-team";
    }

    @GetMapping("/team_add")
    public String addForm(Team team){
        return "add-team";
    }

    @PostMapping("/team_add")
    public String addTeam(@Valid Team team, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-team";
        }
        List<String> errorList = Validator.validateMinMaxAgeInput(team.getMinAge(), team.getMaxAge());
        if(errorList.isEmpty()){
            try {
                service.addTeam(team);
            } catch(ServiceException e){
                model.addAttribute("teamError", e.getMessage());
                return "add-team";
            }
        }else {
            model.addAttribute("minmaxError", errorList);
            return "add-team";
        }
        return "redirect:/team_overview";
    }

    @GetMapping("/team_update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        try{
            Team team = service.findTeamById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
            model.addAttribute("team", team);
        } catch(IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "update-team";
    }

    @PostMapping("/team_update/{id}")
    public String updatePatient(@PathVariable("id") long id, @Valid Team team, BindingResult result, Model model) {
        if (result.hasErrors()) {
            team.setId(id);
            return "update-team";
        }

        List<String> errorList = Validator.validateMinMaxAgeInput(team.getMinAge(), team.getMaxAge());
        if(errorList.isEmpty()){
            try {
                service.updateTeam(team);
            } catch(ServiceException e){
                model.addAttribute("teamError", e.getMessage());
                return "update-team";
            }
        }else {
            model.addAttribute("minmaxError", errorList);
            return "update-team";
        }

        return "redirect:/team_overview/confirmupdate";
    }

    @GetMapping("/team_delete/{id}")
    public String deleteButton(@PathVariable("id") long id, Model model){
        try{
            Team team = service.findTeamById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid id: "+ id));
            service.deleteTeam(id);
        } catch(IllegalArgumentException e){
            model.addAttribute("teamError",e.getMessage());
            return "overview-team";
        }
        return "redirect:/team_overview/confirmdelete";
    }

    @GetMapping("/team_search")
    public String searchBuses(){
        return "search-team";
    }

    //TODO int mag niet negatief zijn
    @PostMapping("/showTeamsWithMaximumAge")
     public String showTeamsWithMaximumAge(@RequestParam("maxAge") String maxAge, Model model ) {
        try {
            List<Team> teams = service.getTeamsWithMaxAge(maxAge);
            if(teams.isEmpty()){ model.addAttribute("emptyAlert", "searchteam.empty"); }
            else{ model.addAttribute("teamsWithMaximumAge", teams); }

        } catch(IllegalArgumentException e) {
            model.addAttribute("maxAgeError", e.getMessage());
        }
        return "search-team";
    }

    @PostMapping("/showTeamsWithNameThatContainsString")
    public String showTeamsWithNameThatContainsString(@RequestParam("word") String word, Model model) {
        try{
            List<Team> teams = service.getTeamsWithNameThatContainsString(word);
            if(teams.isEmpty()){ model.addAttribute("emptyAlert", "searchteam.empty"); }
            else{ model.addAttribute("teamsWithNameThatContainsString", teams); }

        } catch(IllegalArgumentException e){
            model.addAttribute("nameContainsError", e.getMessage());
        }
        return "search-team";
    }

    //TODO int mag niet negatief zijn
    @PostMapping("/showTeamsWithAgeAbove")
    public String showTeamsWithAgeAbove(@RequestParam("age") String age, Model model){
        try {
            List<Team> teams = service.getTeamsWithAgeAbove(age);
            if(teams.isEmpty()){ model.addAttribute("emptyAlert", "searchteam.empty"); }
            else{ model.addAttribute("teamsWithAgeAbove", teams); }

        } catch(IllegalArgumentException e){
            model.addAttribute("ageAboveError", e.getMessage());
        }
        return "search-team";
    }

}
