package manage.service.impl;

import manage.dao.SessionMapper;
import manage.service.SessionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by jinyancao on 10/31/16.
 */
@Service
public class SessionServiceImpl implements SessionService {
    @Resource
    private SessionMapper sessionMapper;

    public void saveSession(String session, String userName) {
        if (StringUtils.isEmpty(sessionMapper.queryUserNameBySession(session))) {
            sessionMapper.saveSession(session, userName);
        }
    }

}
