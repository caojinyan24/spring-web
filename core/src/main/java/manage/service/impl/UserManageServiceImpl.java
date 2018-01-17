package manage.service.impl;


import manage.cachProcessor.ProxyCglib;
import manage.cachProcessor.ProxyJdk;
import manage.dao.UserInfoMapper;
import manage.entity.UserInfo;
import manage.processor.CachEntity;
import manage.processor.Processor;
import manage.service.UserInfoService;
import manage.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UserManageService.class);
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private BeanFactory beanFactory;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 手工做缓存处理
     *
     * @return
     */
    public List<UserInfo> queryUserInfoWithCach() {//对mapper的查询结果做缓存
        CachEntity cachEntity = Processor.getCachedMap().get("queryUserInfo");
        if (null != cachEntity) {
            if ((new Date().getTime() - cachEntity.getTimestamp()) < cachEntity.getExpiredTime()) {
                return (List<UserInfo>) Processor.getCachedMap().get("queryUserInfo");
            } else {
                Processor.getCachedMap().remove("queryUserInfo");
            }
        }
        return userInfoMapper.queryUserInfo();
    }

    //通过cglib动态代理实现缓存的注解处理器
    public List<UserInfo> queryUserInfoC() {
        UserInfoService userInfoServiceProxy = (UserInfoService) new ProxyCglib().createInstance(userInfoService, userInfoService.getClass());//由于userInfoService被Spring代理,所以userInfoService的class类型并不是UserInfoService;动态代理的时候会出现反射失败
        return userInfoServiceProxy.queryUserInfo();
    }


    //通过jdk动态代理实现缓存的注解处理器
    public List<UserInfo> queryUserInfoJ() {
        ProxyJdk proxyJdk = new ProxyJdk();//todo:可以新加一个service层，屏蔽ProxyJdk的调用。
        //spring生成的bean是代理对象（若设置了相关注解，也会把注解对应的处理器代码织入进去）
        //spring实现缓存注解的原理：在生成bean实例时，根据缓存注解，对方法进行代理，生成的bean是代理过的对象实例
        UserInfoService userInfoService = (UserInfoService) proxyJdk.createInstance(beanFactory.getBean(UserInfoService.class));
        //beanFactory.getBean(UserInfoServiceImpl.class)会报找不到bean
        return userInfoService.queryUserInfo();
    }

    public List<UserInfo> queryUserInfo() {
        return userInfoService.queryUserInfo();
    }

    //查询操作使用了缓存,更新完成之后需要更新缓存
    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateUserInfo(userInfo);
        Processor.getCachedMap().clear();
    }


}
