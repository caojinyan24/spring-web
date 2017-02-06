package manage.utils;

import com.google.common.base.Splitter;
import manage.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 权限控制，通过redis记录登陆用户状态
 * Created by jinyancao on 10/25/16.
 */
@Component
public class PrevilegeControl extends HandlerInterceptorAdapter {
    @Value("${query}")
    String canQuery;
    @Value("${update}")
    String canUpdate;
    @Resource
    private RedisService redisService;
    private List<String> canQueryList;

    @PostConstruct
    public void setUp() {
        canQueryList = Splitter.on(",").trimResults().splitToList(canQuery);
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getRequestURI().endsWith("login") || request.getRequestURI().endsWith("home")) {
            return true;
        }
        String sessionId = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sessionId")) {
                sessionId = cookie.getValue();
            }
        }
        if (StringUtils.isEmpty(sessionId)) {
            response.sendRedirect("/manage/login");
        }
        String userName = redisService.get(sessionId);
        return !StringUtils.isEmpty(userName) && canQueryList.contains(String.valueOf(userName));
    }
}
