package  com.nti.hub.config;



import com.nti.hub.dao.JdbcUserDao;
import com.nti.hub.dao.UserDao;

public class Storage {
    private static final UserDao USER_DAO= new JdbcUserDao();

    private Storage() {
    }

    public static UserDao  getUserDao(){
        return  USER_DAO;
    }
}
