package manage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jinyancao on 10/31/16.
 */
@Repository
public interface SessionMapper {
    String queryUserNameBySession(String session);
    void saveSession(@Param("session")String session,@Param("userName")String userName);
}
