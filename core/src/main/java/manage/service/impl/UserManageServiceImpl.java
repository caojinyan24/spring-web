package manage.service.impl;


import manage.dao.UserInfoMapper;
import manage.entity.UserInfo;
import manage.service.UserManageService;
import org.slf4j.ext.EventData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinyan.cao on 2016/4/18.
 */
@Service
public class UserManageServiceImpl implements UserManageService {
    @Resource
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> queryUserInfo(EventData data) {
        data.put("queryUserInfo","abc");
        return userInfoMapper.queryUserInfo();
    }

    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateUserInfo(userInfo);
    }


}
