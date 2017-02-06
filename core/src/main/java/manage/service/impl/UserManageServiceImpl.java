package manage.service.impl;


import manage.dao.UserInfoMapper;
import manage.entity.UserInfo;
import manage.processor.CachEntity;
import manage.processor.Processor;
import manage.service.UserManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by jinyan.cao on 2016/4/18.
 */
@Service
public class UserManageServiceImpl implements UserManageService {
    @Resource
    private UserInfoMapper userInfoMapper;

    //todo:获取key的方式不太优雅，考虑怎么优化
    public List<UserInfo> queryUserInfo() {
        CachEntity cachEntity = Processor.getCachedMap().get("queryUserInfo1");
        if (null != cachEntity) {
            if ((new Date().getTime() - cachEntity.getTimestamp()) < cachEntity.getExpiredTime()) {
                return (List<UserInfo>) Processor.getCachedMap().get("queryUserInfo1");
            } else {
                Processor.getCachedMap().remove("queryUserInfo1");
            }

        }
        return userInfoMapper.queryUserInfo();
    }

    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateUserInfo(userInfo);
    }


}
