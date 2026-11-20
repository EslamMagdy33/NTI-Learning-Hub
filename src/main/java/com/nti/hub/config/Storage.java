package  com.nti.hub.config;



import com.nti.hub.dao.JdbcStudentDao;
import com.nti.hub.dao.JdbcUserDao;
import com.nti.hub.dao.StudentDao;
import com.nti.hub.dao.UserDao;

public class Storage {
    private static final UserDao USER_DAO= new JdbcUserDao();
    private static final StudentDao STUDENT_DAO = new JdbcStudentDao();

    private Storage() {
    }

    public static UserDao  getUserDao(){
        return  USER_DAO;
    }
    public static StudentDao getStudentDao() { return STUDENT_DAO; }
}
