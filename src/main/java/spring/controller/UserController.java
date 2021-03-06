package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import spring.dao.ReasonDao;
import spring.model.Reason;
import spring.model.User;
import spring.service.SecurityService;
import spring.service.UserService;
import spring.validator.UserValidator;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private ReasonDao reasonDao;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("UC#index").build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        List<Reason> reasons = reasonDao.findAll();

        model.addAttribute("reasonForm", new Reason());
        model.addAttribute("reasonList", reasons);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String balloon(@ModelAttribute("reasonForm") Reason reasonForm, Model model) {

        List<Reason> reasons = reasonDao.findAll();

        model.addAttribute("reasonForm", new Reason());
        model.addAttribute("reasonList", reasons);
        return "balloon";
    }
}
