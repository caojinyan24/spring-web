package manage.service.impl;


import manage.dao.UserInfoMapper;
import manage.entity.UserInfo;
import manage.processor.Processor;
import manage.service.UserManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jinyan.cao on 2016/4/18.
 */
@Service
public class UserManageServiceImpl implements UserManageService {
    @Resource
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> queryUserInfo() {
        if (null != Processor.getCachedMap().get("queryUserInfo1")) {
            return (List<UserInfo>) Processor.getCachedMap().get("queryUserInfo1");
        }
        return userInfoMapper.queryUserInfo();
    }

    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateUserInfo(userInfo);
    }


}
