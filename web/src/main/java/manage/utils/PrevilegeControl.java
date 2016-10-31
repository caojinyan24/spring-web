package manage.utils;

import com.google.common.base.Splitter;
import manage.dao.SessionMapper;
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
 * 权限控制
 * Created by jinyancao on 10/25/16.
 */
@Component
public class PrevilegeControl extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(PrevilegeControl.class);
    @Resource
    private SessionMapper sessionMapper;

    @Value("${query}")
    String canQuery;
    @Value("${update}")
    String canUpdate;

    private List<String> canQueryList;
    private List<String> canUpdateList;

    @PostConstruct
    public void setUp() {
        canQueryList = Splitter.on(",").trimResults().splitToList(canQuery);
        canUpdateList = Splitter.on(",").trimResults().splitToList(canUpdate);
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("preHandle begin:");
        if (request.getRequestURI().endsWith("login")||request.getRequestURI().endsWith("home")) {
            return true;
        }
        String sessionId="";
        Cookie[]cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("sessionId")){
                sessionId=cookie.getValue();
            }
        }
        if(StringUtils.isEmpty(sessionId)){
            response.sendRedirect("/manage/login");
        }
        String userName = sessionMapper.queryUserNameBySession(sessionId);
        logger.info("name={}", userName);
        if (StringUtils.isEmpty(userName)) {
            return false;
        }
        return canQueryList.contains(String.valueOf(userName));
    }
}