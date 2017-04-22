package manage.service.impl;


import manage.anotation.ProxyCglib;
import manage.anotation.ProxyJdk;
import manage.dao.UserInfoMapper;
import manage.entity.UserInfo;
import manage.processor.CachEntity;
import manage.processor.Processor;
import manage.service.UserInfoService;
import manage.service.UserManageService;
import org.springframework.beans.factory.BeanFactory;
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
    @Resource
    private BeanFactory beanFactory;

    //todo:获取key的方式不太优雅，考虑怎么优化
    public List<UserInfo> queryUserInfoA() {
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

    //通过cglib动态代理实现缓存的注解处理器
    public List<UserInfo> queryUserInfoC() {
        UserInfoService userInfoService = (UserInfoService) new ProxyCglib().createInstance(UserInfoServiceImpl.class);
        return userInfoService.queryUserInfo();
    }


    //通过jdk动态代理实现缓存的注解处理器
    public List<UserInfo> queryUserInfoJ() {
        ProxyJdk proxyJdk = new ProxyJdk();
        UserInfoService userInfoService = (UserInfoService) proxyJdk.createInstance(beanFactory.getBean(UserInfoService.class));
        //beanFactory.getBean(UserInfoServiceImpl.class)会报找不到bean
        return userInfoService.queryUserInfo();
    }

    public List<UserInfo> queryUserInfo() {
        return queryUserInfoJ();
    }

    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateUserInfo(userInfo);
    }


}
