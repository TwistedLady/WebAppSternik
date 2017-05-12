package com.example.web.controler;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entities.Cd;
import com.example.entities.Status;
import com.example.services.KlasterService;
import com.example.services.NotificationService;

@Controller
public class CdController {

    @Autowired
    @Qualifier("spring")
    private KlasterService klaserService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @RequestMapping(value = "/cds/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Cd> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
        	Cd cd = result.get();
            model.addAttribute("cd", cd);
            return "cd";
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return "redirect:/cds";
        }
    }

    @RequestMapping(value = "/cds/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Cd> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Cd> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
        	Cd cd = result.get();
            return new ResponseEntity<Cd>(cd, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return new ResponseEntity<Cd>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cds", params = { "save" }, method = RequestMethod.POST)
    public String saveMoneta(@Valid Cd cd, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "cd";
        }
        Optional<Cd> result = klaserService.edit(cd);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();
        return "redirect:/cds";
    }

    @RequestMapping(value = "/cds", params = { "create" }, method = RequestMethod.POST)
    public String createMoneta(@Valid Cd cd, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "cd";
        }
        klaserService.create(cd);
        model.clear();
        notifyService.addInfoMessage("Zapis nowej udany");
        return "redirect:/cds";
    }

    @RequestMapping(value = "/cds", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Cd cd, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = klaserService.deleteById(rowId.longValue());
        return "redirect:/cds";
    }

    @RequestMapping(value = "/cds/create", method = RequestMethod.GET)
    public String showMainPages(final Cd cd) {
    	cd.setData(Calendar.getInstance().getTime());
        return "cd";
    }
}
