package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.dao.UserDao;
import spring.dao.UserReasonDao;
import spring.model.DeleteForm;
import spring.model.UserReason;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserReasonDao userReasonDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, Principal principal) {

        List<UserReason> reasons = userReasonDao.findByUserUsername(principal.getName());
        model.addAttribute("userReason", reasons);
        model.addAttribute("deleteForm", new DeleteForm());
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST, params = "delete")
    public String delete(@ModelAttribute("deleteForm") DeleteForm deleteForm, Model model, Principal principal) {
        userReasonDao.delete(deleteForm.getReasonId());

        List<UserReason> reasons = userReasonDao.findByUserUsername(principal.getName());
        model.addAttribute("userReason", reasons);
        model.addAttribute("deleteForm", new DeleteForm());
        return "profile";
    }
}
