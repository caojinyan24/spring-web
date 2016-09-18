package manage.controller;

import manage.entity.StudentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jinyancao on 2016/04/26/20/05
 */
@Controller
@RequestMapping(value = "manage")
public class StudentManageController {
    private static final Logger logger = LoggerFactory.getLogger(StudentManageController.class);

    @RequestMapping("/home")
    public String home() {
        return "homePage";//返回vm名称


    }

    @RequestMapping("/login")
    public void login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        logger.info("login");
    }

    @RequestMapping("/query")
    public StudentInfo query() {
        return new StudentInfo();
    }
}
