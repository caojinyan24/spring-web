package manage.service;


import manage.entity.UserInfo;

import java.util.List;

/**
 * Created by jinyan.cao on 2016/4/18.
 */
public interface UserManageService {
    List<UserInfo> queryUserInfo();

    public void updateUserInfo(UserInfo stuentInfo);

    public String getUserName();

    public void setUserName(String userName);


}
