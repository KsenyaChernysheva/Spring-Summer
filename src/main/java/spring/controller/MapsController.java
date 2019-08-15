package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import spring.dao.ReasonDao;
import spring.dao.UserDao;
import spring.dao.UserReasonDao;
import spring.model.Reason;
import spring.model.User;
import spring.model.UserReason;

import java.security.Principal;
import java.util.List;

@Controller
public class MapsController {

    @Autowired
    private ReasonDao reasonDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserReasonDao userReasonDao;

    @RequestMapping(value = "/reasons", method = RequestMethod.GET)
    public String reason(Model model, @RequestParam String country, @RequestParam long reasonId, Principal principal) {

        User user = userDao.findByUsername(principal.getName());
        UserReason filledByUser = userReasonDao.findByUserAndCountry(user, country);
        if (filledByUser == null) {
            filledByUser = new UserReason();
            filledByUser.setCountry(country);
        }

        List<Reason> reasons = reasonDao.findAll();
        model.addAttribute("reasonCount", userReasonDao.countByCountryAndReasonId(country, reasonId));
        model.addAttribute("reasonForm", filledByUser);
        model.addAttribute("reasonList", reasons);

        return "balloon";
    }

    @RequestMapping(value = "/reasons", method = RequestMethod.POST)
    public String registration(@ModelAttribute("reasonForm") UserReason reasonForm, Model model, Principal principal) {
        User user = userDao.findByUsername(principal.getName());
        UserReason filledByUser = userReasonDao.findByUserAndCountry(user, reasonForm.getCountry());
        if (filledByUser != null) {
            filledByUser.setReason(reasonForm.getReason());
            filledByUser.setComment(reasonForm.getComment());
        } else {
            filledByUser = reasonForm;
            filledByUser.setUser(user);
        }

        userReasonDao.save(filledByUser);

        List<Reason> reasons = reasonDao.findAll();
        model.addAttribute("reasonForm", filledByUser);
        model.addAttribute("reasonList", reasons);
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("UC#index").build();
    }
}
