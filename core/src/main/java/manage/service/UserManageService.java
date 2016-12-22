package manage.service;


import manage.entity.UserInfo;
import org.slf4j.ext.EventData;

import java.util.List;

/**
 * Created by jinyan.cao on 2016/4/18.
 */
public interface UserManageService {
    List<UserInfo> queryUserInfo(EventData data);

    void updateUserInfo(UserInfo userInfo);


}
