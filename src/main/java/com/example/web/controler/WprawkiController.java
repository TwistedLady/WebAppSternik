package com.example.web.controler;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entities.Cd;
import com.example.entities.Status;
import com.example.repositories.CdAlreadyExistExceprion;
import com.example.repositories.CdRepository;
import com.example.repositories.NoSuchCdException;
@Controller
public class WprawkiController {
    @Autowired
    @Qualifier("tablica")
    CdRepository baza;

    @RequestMapping("/wprawki")
    public String index(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        try {
            model.addAttribute("cd", baza.readById(0L));
        } catch (NoSuchCdException e) {
            e.printStackTrace();
        }
        return "wprawki";
    }

    @RequestMapping({ "/wprawki/{cos}" })
    public String indexInny(@PathVariable String cos, ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        try {
            model.addAttribute("moneta", baza.readById(0L));
        } catch (NoSuchCdException e) {
            e.printStackTrace();
        }

        model.put("id", cos);

        if ("5".equals(cos)) {
            model.clear();
            return "redirect:/index";
        }
        return "wprawki";
    }

    @RequestMapping(value = "/wprawki/cds/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Cd> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
    	Cd cd;
        try {
        	cd = baza.readById(id);
            return new ResponseEntity<Cd>(cd, HttpStatus.OK);
        } catch (NoSuchCdException e) {
            e.printStackTrace();
            cd = new Cd(5l,"Among the living","Anthrax",new BigDecimal(25),Status.NEW,"blabla",new Date());
            
            
            try {
                baza.create(cd);
            } catch (CdAlreadyExistExceprion e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<Cd>(cd, HttpStatus.CREATED);
        }
    }
}
