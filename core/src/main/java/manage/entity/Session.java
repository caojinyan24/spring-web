package manage.entity;

import java.io.Serializable;

/**
 * Created by jinyancao on 10/31/16.
 */
public class Session implements Serializable {
    private static final long serialVersionUID = -8623838161576515805L;
    String session;
    String userName;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
