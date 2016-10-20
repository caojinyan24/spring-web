package manage.controller;

import com.google.common.collect.Lists;
import manage.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


/**
 * Created by jinyancao on 2016/04/26/20/05
 */
@Controller
@RequestMapping(value = "manage")
public class UserManageController {
    private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);

    @RequestMapping("/home")
    public String home() {
        return "homePage";//返回vm名称
    }

    @RequestMapping("/login")
    public String login() {
        logger.info("login");
        return "login";
    }

    @ResponseBody
    @RequestMapping("/query")
    public List<UserInfo> query() {
        return Lists.newArrayList(new UserInfo("aa","bb"));
    }

    @ResponseBody
    @RequestMapping("/update")
    public void update(@RequestParam("userName")String userName) {
        logger.info("update");
        new RedirectView("/manage/query");

    }


}
