package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.repository.SalarieAideADomicileRepository;
import com.ipi.jva320.service.SalarieAideADomicileService;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class SalarieController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @Autowired
    public SalarieAideADomicileService salarieService;

    @RequestMapping(value = "/salaries/aide/new")
    public String getAjoutSalarie(final ModelMap m){
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        m.put("nom", salarie.getNom());
        m.put("salarie", salarie);
        return "new_Salarie.html";
    }

    @PostMapping(value = "/salaries/{id}")
    public String savePatient(SalarieAideADomicile salarie) throws SalarieException {
        salarieAideADomicileService.updateSalarieAideADomicile(salarie);
        return "redirect:/salaries/" + salarie.getId();
    }

    @GetMapping(value = "/salaries/aide/new")
    public String ajoutPatient(SalarieAideADomicile salarie) {
        salarieAideADomicileService.ajoutSalarie(salarie);
        return "redirect:/salaries/" + salarie.getId();
    }

    @GetMapping(value = "/salaries/{id}/delete")
    public String deletePatient(SalarieAideADomicile salarie) throws SalarieException {
        salarieAideADomicileService.deleteSalarieAideADomicile(salarie.getId());
        return "redirect:/salaries/";
    }

    @RequestMapping(value = "/salaries/{id}")
    public String getDetailSalarie(final ModelMap m, @PathVariable("id") long id){
        m.put("id", id);
        m.put("nom", salarieService.getSalarie(id).getNom());
        m.put("salarie", salarieService.getSalarie(id));
        return "detail_Salarie.html";
    }

    //@RequestMapping(value = "/salaries")
    //public String getSalaries(ModelMap m) {
    //    m.addAttribute("salarie", salarieService.getSalaries());
    //    return "list.html";
    //}

    @GetMapping(value = "/salaries")
    public String getListSalarie(ModelMap m, SalarieAideADomicile salarie, @RequestParam(value = "nom") String name) {
        System.out.println("Hello  " + name);

        if(Objects.equals(name, "")){
            m.addAttribute("salarie", salarieService.getSalaries());
        } else{
            try {
                m.addAttribute("salarie", salarieService.getSalaries(name));
            } catch (EntityNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, name + " : pas trouvé");
            }
        }
        return "list.html";
    }
}
