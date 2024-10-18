package test;

import com.huaxin.beans.bean.UserService;
import com.huaxin.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserService bean = applicationContext.getBean("userService", UserService.class);
        bean.query();
        System.out.println(bean.getBeanFactory());
        System.out.println(bean.getApplicationContext());
    }
}
