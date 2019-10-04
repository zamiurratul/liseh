package com.liseh.bll.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Controller
public class WebAppController extends BaseController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(WebRequest request, Model model) {
        model.addAttribute("datetime", new Date());
        model.addAttribute("websiteName", "LISEH");

        return "index";
    }
}
