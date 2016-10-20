package manage.entity;

import java.io.Serializable;

/**
 * Created by jinyan.cao on 2016/4/18.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 4091651295331185057L;
    String userId;
    String userName;

    public UserInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
