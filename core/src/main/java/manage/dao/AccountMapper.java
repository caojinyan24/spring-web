package manage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jinyancao on 10/31/16.
 */
@Repository
public interface AccountMapper {
    public Integer queryAccountCount(@Param("userName") String userName,@Param("password")String password);
}
