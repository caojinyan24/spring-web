package manage.service.impl;

import com.google.common.collect.Lists;
import manage.dao.UserInfoMapper;
import manage.entity.UserInfo;
import manage.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jinyan on 4/22/17.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    UserInfoMapper userInfoMapper;

    public List<UserInfo> queryUserInfo() {
        return userInfoMapper.queryUserInfo();
    }


}
