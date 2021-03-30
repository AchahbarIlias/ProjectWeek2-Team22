package projectweek.team22.ui.controller;

import projectweek.team22.model.service.BasketballService;
import projectweek.team22.util.Validator;
import projectweek.team22.model.entity.Bus;
import projectweek.team22.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Valid
public class BusController {

    @Autowired
    private BasketballService service;

    @GetMapping("/bus_overview")
    public String showBusOverview(Model model) {
        model.addAttribute("overviewTitle", "bus.overview");
        model.addAttribute("buses", service.getAllBuses());
        return "overview-bus";
    }
    @GetMapping("/bus_overview/namesort")
    public String showBusOverviewSortName(Model model) {
        model.addAttribute("overviewTitle", "bus.sorted.name");
        model.addAttribute("buses", service.getAllBusesSortedByName());
        return "overview-bus";
    }
    @GetMapping("/bus_overview/emailsort")
    public String showBusOverviewSortEmail(Model model){
        model.addAttribute("overviewTitle", "bus.sorted.email");
        model.addAttribute("buses", service.getAllBusesSortedByEmail());
        return "overview-bus";
    }
    @GetMapping("/bus_overview/seatsort")
    public String showBusOverviewSortSeat(Model model){
        model.addAttribute("overviewTitle", "bus.sorted.seats");
        model.addAttribute("buses", service.getAllBusesSortedBySeats());
        return "overview-bus";
    }
    @GetMapping("/bus_overview/confirmupdate")
    public String showBusOverviewConfirmationUpdate(Model model){
        model.addAttribute("confirmationMessage", "confirmation.update.bus");
        model.addAttribute("overviewTitle", "bus.overview");
        model.addAttribute("buses", service.getAllBuses());
        return "overview-bus";
    }
    @GetMapping("/bus_overview/confirmdelete")
    public String showBusOverviewConfirmationDelete(Model model){
        model.addAttribute("confirmationMessage", "confirmation.delete.bus");
        model.addAttribute("overviewTitle", "bus.overview");
        model.addAttribute("buses", service.getAllBuses());
        return "overview-bus";
    }


    @GetMapping("/bus_add")
    public String addForm(Bus bus){
        return "add-bus";
    }

    @PostMapping("/bus_add")
    public String addBus(@Valid Bus bus, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-bus";
        }
        try {
            service.addBus(bus);
        } catch(ServiceException e) {
            model.addAttribute("busError", e.getMessage());
            return "add-bus";
        }
        return "redirect:/bus_overview";
    }

    @GetMapping("/bus_update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        try{
            Bus bus = service.findBusById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
            model.addAttribute("bus", bus);
        }
        catch (IllegalArgumentException exc) {
            model.addAttribute("busError", exc.getMessage());
        }
        return "update-bus";
    }

    @PostMapping("/bus_update/{id}")
    public String updateBus(@PathVariable("id") long id, @Valid Bus bus, BindingResult result, Model model) {
        if (result.hasErrors()) {
            bus.setId(id);
            return "update-bus";
        }
        try {
            service.updateBus(bus);
        }catch (ServiceException exc) {
            model.addAttribute("busError", exc.getMessage());
            return "update-bus";
        }
        return "redirect:/bus_overview/confirmupdate";
    }



    @GetMapping("/bus_delete/{id}")
    public String deleteButton(@PathVariable("id") long id, Model model){
        try{
            Bus bus = service.findBusById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid id: "+ id));

            service.deleteBus(id);
        }catch(IllegalArgumentException e){
            model.addAttribute("busError", e.getMessage());
            return "overview-bus";
        }
        return "redirect:/bus_overview/confirmdelete";
    }

    @GetMapping("/bus_search")
    public String searchBuses(){
        return "search-bus";
    }

    @PostMapping("/showBusesWithDepartureLocation")
    public String showBusOverviewWithDepartureLocation(@RequestParam("departureLocation") String departureLocation,  Model model ) {
        try {
            List<Bus> buses = service.getBusesWithDepartureLocation(departureLocation);
            if(buses.isEmpty()){ model.addAttribute("emptyAlert", "searchbus.empty"); }
            else{ model.addAttribute("busesWithDepartureLocation", buses); }

        } catch (ServiceException | IllegalArgumentException e) {
            model.addAttribute("departureError", e.getMessage());
        }
        return "search-bus";
    }

    @PostMapping("/showBusesWithNumberOfSeatsBetween")
    public String showBusOverviewWithNumbersBetweenMinMax(@RequestParam("minSeats") String minSeats,@RequestParam("maxSeats") String maxSeats, Model model) {
       List<String> errorList = Validator.validateBusSeatsInput(minSeats,maxSeats);

       if(errorList.isEmpty()){
           List<Bus> buses = service.getBusesWithNumberOfSeatsBetween(minSeats, maxSeats);
           if(buses.isEmpty()){ model.addAttribute("emptyAlert", "searchbus.empty"); }
           else{ model.addAttribute("busesWithNumberOfSeatsBetween", buses); }

       } else {
           model.addAttribute("seatsError", errorList );
       }
       return "search-bus";
    }

    @PostMapping("/showBusesWithSeatsAbove")
    public String showBusesWithSeatsAbove(@RequestParam("seatsAbove") String seatsAbove, Model model){
        try {
            List<Bus> buses = service.getBusesWithNumberOfSeatsAbove(seatsAbove);
            if(buses.isEmpty()){ model.addAttribute("emptyAlert", "searchbus.empty"); }
            else{ model.addAttribute("busesWithSeatsAbove", buses); }

        } catch (IllegalArgumentException e){
            model.addAttribute("seatsAboveError", e.getMessage());
        }
        return "search-bus";
    }
}
