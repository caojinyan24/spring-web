package manage.dao;

import manage.anotation.Cach;
import manage.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jinyancao on 9/18/16.
 */
@Repository
public interface UserInfoMapper {
    @Cach
    List<UserInfo> queryUserInfo();

    Integer updateUserInfo(UserInfo userInfo);
}
