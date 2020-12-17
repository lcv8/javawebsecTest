import com.hui.dao.BaseDao;
import com.hui.dao.UserDao;
import com.hui.dao.impl.BooksDaoImpl;
import com.hui.dao.impl.UsersDaoImpl;
import com.hui.entity.Users;
import org.junit.Test;

import java.util.List;

public class TestConn {
    @Test
    public void test1(){
        UserDao usersDao = new UsersDaoImpl();
        Users users = new Users();
        users.setGender(1);
        users.setEmail("542323153@qq.com");
        users.setPassword("xiayu520");
        users.setUserCode("mimi的一生");
        usersDao.insertUser(users);
        List<Users> list = usersDao.queryUser(new Users());
        System.out.println(list);
    }
}
