package manage.controller;

import manage.entity.UserInfo;
import manage.service.AccountService;
import manage.service.RedisService;
import manage.service.UserManageService;
import org.slf4j.MDC;
import org.slf4j.ext.EventData;
import org.slf4j.ext.EventLogger;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.ProfilerRegistry;
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
import java.util.UUID;


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
        Profiler profiler = new Profiler("controller begin");
        profiler.start("verifyAccount");
        if (accountService.vefifyAccount(userName, password)) {
            String sessionId = request.getRequestedSessionId();
            if (sessionId == null) {
                sessionId = UUID.randomUUID().toString();
            }
            MDC.put("login",sessionId);
            MDC.put("userName",userName);
            MDC.put("password",password);
            profiler.start("save");
            Cookie cookie = new Cookie("sessionId", sessionId);
            response.addCookie(cookie);
            redisService.save(sessionId, userName);
            return "login";
        }
        profiler.stop().print();
        MDC.clear();
        return "homePage";
    }

    @RequestMapping(value = "/query")
    public ModelAndView query(HttpServletRequest request) {
        Profiler profiler = new Profiler("query");
        EventData data=new EventData();
        data.setEventDateTime(new Date());
        data.setEventType("query");
        data.put("request",request.getRequestURL());
        ProfilerRegistry profilerRegistry = ProfilerRegistry.getThreadContextInstance();
        profiler.registerWith(profilerRegistry);
        profiler.start("query");
        ModelAndView modelAndView = new ModelAndView("query");
        List<UserInfo> userInfos = userManageService.queryUserInfo(data);
        modelAndView.addObject("userInfos", userInfos);
        EventLogger.logEvent(data);
        profiler.stop().print();
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
