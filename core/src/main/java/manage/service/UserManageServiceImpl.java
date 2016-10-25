package manage.service;


import manage.dao.UserInfoMapper;
import manage.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jinyan.cao on 2016/4/18.
 */
@Service
public class UserManageServiceImpl implements UserManageService {
    private static final Logger logger = LoggerFactory.getLogger(UserManageServiceImpl.class);
    @Resource
    private UserInfoMapper userInfoMapper;
    String userName;

    public List<UserInfo> queryUserInfo() {
        return userInfoMapper.queryUserInfo();
    }

    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateUserInfo(userInfo);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
