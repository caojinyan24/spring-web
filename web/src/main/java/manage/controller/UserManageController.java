package manage.controller;

import manage.entity.UserInfo;
import manage.service.AccountService;
import manage.service.RedisService;
import manage.service.UserManageService;
import org.slf4j.MDC;
import org.slf4j.ext.EventData;
import org.slf4j.ext.EventLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


/**
 * Created by jinyancao on 2016/04/26/20/05
 */
@Controller
@RequestMapping(value = "manage")
public class UserManageController {
    @Resource
    private UserManageService userManageService;
    @Resource
    private AccountService accountService;
    @Resource
    private RedisService redisService;

    @RequestMapping("/home")
    public String home() {
        return "homePage";//返回vm名称
    }

    @RequestMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        if (accountService.vefifyAccount(userName, password)) {
//            String sessionId = request.getRequestedSessionId();
            String sessionId = request.getSession(true).getId();
//            if (sessionId == null) {
//                sessionId = UUID.randomUUID().toString();
//            }
            MDC.put("login", sessionId);
            MDC.put("userName", userName);
            MDC.put("password", password);
            Cookie cookie = new Cookie("sessionId", sessionId);
            response.addCookie(cookie);
            redisService.save(sessionId, userName);
            return "login";
        }
        MDC.clear();
        return "homePage";
    }

    @RequestMapping(value = "/query")
    public ModelAndView query(HttpServletRequest request) {
        EventData data = new EventData();
        data.setEventDateTime(new Date());
        data.put("request", request.getRequestURL());
        ModelAndView modelAndView = new ModelAndView("query");
        List<UserInfo> userInfos = userManageService.queryUserInfo();
        modelAndView.addObject("userInfos", userInfos);
        EventLogger.logEvent(data);
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
