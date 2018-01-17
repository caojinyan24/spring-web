package manage.dao;

import manage.cachProcessor.Cach;
import manage.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jinyancao on 9/18/16.
 */
@Repository
public interface UserInfoMapper {
    @Cach(expiredTime = 1000L)
    List<UserInfo> queryUserInfo();

    Integer updateUserInfo(UserInfo userInfo);
}
