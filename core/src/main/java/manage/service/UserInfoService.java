package manage.service;

import manage.entity.UserInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jinyan on 4/22/17.
 */
public interface UserInfoService {

     List<UserInfo> queryUserInfo();

}
