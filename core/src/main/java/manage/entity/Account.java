package manage.entity;

import java.io.Serializable;

/**
 * Created by jinyancao on 10/31/16.
 */
public class Account implements Serializable{
    private static final long serialVersionUID = 9179862291063072965L;
    Long userId;
    String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
