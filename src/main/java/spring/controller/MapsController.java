package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spring.dao.ReasonDao;
import spring.dao.UserReasonCountryDao;
import spring.model.Reason;
import spring.model.User;
import spring.model.UserReasonCountry;

import java.util.List;

@Controller
public class MapsController {

    @Autowired
    private ReasonDao reasonDao;

    @Autowired
    private UserReasonCountryDao userReasonCountryDao;

    @RequestMapping(value = "/reasons", method = RequestMethod.GET)
    public String reason(Model model, String reason, @RequestParam String country) {

        List<Reason> reasons = reasonDao.findAll();

        List<UserReasonCountry> userReasonCountries = userReasonCountryDao.findAll();

        model.addAttribute("reasonForm", new Reason());
        model.addAttribute("reasonList", reasons);
        model.addAttribute("country", country);

        return "balloon";
    }

    @RequestMapping(value = "/reasons", method = RequestMethod.POST)
    public String reasonSave(@ModelAttribute("userReasonCountry") UserReasonCountry userReasonCountry, Model model, @RequestParam String country) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userReasonDao.save(user.getId(), userReasonCountry.getId(), country);

        return "balloon";
    }
}

