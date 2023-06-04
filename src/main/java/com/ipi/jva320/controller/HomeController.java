package com.ipi.jva320.controller;

import com.ipi.jva320.service.SalarieAideADomicileService;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @Autowired
    public SalarieAideADomicileService salarieService;

    @RequestMapping(value = "/")
    public String getHome(final ModelMap m){
        m.put("salarieCount", salarieService.countSalaries());
        return "home";
    }
}
