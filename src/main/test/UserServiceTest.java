import com.baobaotao.domain.User;
import com.baobaotao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试类
 * author Mr.WangWei
 * Create 2017 01 04 19:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:spring-mybatis.xml"})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void getUser(){
        User user = userService.findUserByUserName("admin");
        System.out.println(user);
    }

    @Test
    public void getTime(){
        long t=1482831305019L;
        Date date = new Date(t);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dateFormat.format(1482831305019L));
    }
}
