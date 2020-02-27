package learn.lhb.security.distributed.security.uaa;


import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 梁鸿斌
 * @date 2020/2/27.
 * @time 16:37
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
public class Test {

    @org.junit.Test
    public void secretTest() {
        System.out.println(new BCryptPasswordEncoder().encode("secret"));
    }
}
