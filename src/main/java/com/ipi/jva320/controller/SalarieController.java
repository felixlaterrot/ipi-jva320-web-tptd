package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.repository.SalarieAideADomicileRepository;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class SalarieController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @Autowired
    public SalarieAideADomicileService salarieService;

    @RequestMapping(value = "/salaries/{id}")
    public String getDetailSalarie(final ModelMap m, @PathVariable("id") long id){
        m.put("id", id);
        m.put("nom", salarieService.getSalarie(id).getNom());
        m.put("salarie", salarieService.getSalarie(id));
        return "detail_Salarie.html";
    }

    @RequestMapping(value = "/salaries")
    public String getListSalarie(final ModelMap m){
        m.addAttribute("salarie", salarieService.getSalaries());
        return "list.html";
    }

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

    @GetMapping(value = "/salaries/{id}/delete")
    public String deletePatient(SalarieAideADomicile salarie) throws SalarieException {
        salarieAideADomicileService.deleteSalarieAideADomicile(1l);
        return "redirect:/salaries/" + salarie.getId();
    }

    @GetMapping(value = "/salaries/aide/new")
    public String ajoutPatient(SalarieAideADomicile salarie) {
        salarieAideADomicileService.ajoutSalarie(salarie);
        return "redirect:/salaries/" + salarie.getId();
    }
}
