package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.dao.UserReasonDao;
import spring.model.UserReason;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class RestController {

    @Autowired
    private UserReasonDao userReasonDao;

    @ResponseBody
    @RequestMapping(value = "/reasons", method = RequestMethod.GET)
    public List<UserReason> reason() {
        return userReasonDao.findAll();
    }
}
