package manage.controller;

import manage.entity.UserInfo;
import manage.service.UserManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by jinyancao on 2016/04/26/20/05
 */
@Controller
@RequestMapping(value = "manage")
public class UserManageController {
    @Resource
    private UserManageService userManageService;

    @RequestMapping("/home")
    public String home() {
        return "homePage";//返回vm名称
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/query")
    public ModelAndView query() {
        ModelAndView modelAndView = new ModelAndView("query");
        List<UserInfo> userInfos = userManageService.queryUserInfo();
        modelAndView.addObject("userInfos", userInfos);
        return modelAndView;
    }

    @RequestMapping("/update")
    public ModelAndView update(@RequestParam("userName") String userName, @RequestParam("userId") Long userId) {
        ModelAndView modelAndView = new ModelAndView();
        userManageService.updateUserInfo(new UserInfo(userId, userName));
        View view = new RedirectView("/manage/query");
        modelAndView.setView(view);
        return modelAndView;

    }


}
