package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.dao.ReasonDao;
import spring.model.Reason;

import java.util.List;

@Controller
public class MapsController {

    @Autowired
    private ReasonDao reasonDao;

    @RequestMapping(value = "/reasons", method = RequestMethod.GET)
    public String reason(Model model, String reason) {

        List<Reason> reasons = reasonDao.findAll();

        model.addAttribute("reasonForm", new Reason());
        model.addAttribute("reasonList", reasons);

        return "balloon";
    }

    @RequestMapping(value = "/reasons", method = RequestMethod.POST)
    public String registration(@ModelAttribute("reasonForm") Reason reasonForm, Model model) {


        return "balloon";
    }
}
