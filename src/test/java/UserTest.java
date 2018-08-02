import com.sunmall.controller.portal.UserController;
import org.junit.Test;

/**
 * @className UserTest
 * @Author SunYanwu
 * @Description：
 * @Date 2018/7/24 14:16
 */
public class UserTest {
    @Test
    public void loginTest(){
        UserController userController=new UserController();
        userController.login("see","dfefff",null);
    }
}
