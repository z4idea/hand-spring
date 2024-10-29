package com.huaxin.test;

import com.huaxin.aop.advisedsupport.AdvisedSupport;
import com.huaxin.aop.advisedsupport.TargetSource;
import com.huaxin.aop.interceptor.UserServiceInterceptor;
import com.huaxin.aop.pointcutexpression.AspectJExpressionPointcut;
import com.huaxin.aop.proxy.Cglib2AopProxy;
import com.huaxin.aop.proxy.JdkDynamicAopProxy;
import com.huaxin.beans.bean.IUserService;
import com.huaxin.beans.bean.UserService;
import org.junit.Test;

import java.lang.reflect.Method;

public class ApiTest {
    @Test
    public void test4PointcutExpression() throws NoSuchMethodException {
        // 匹配 String 的 valueOf 方法
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* java.lang.String.*(..))");
        System.out.println(aspectJExpressionPointcut.matches(String.class));
        Method method = String.class.getDeclaredMethod("valueOf", int.class);
        System.out.println(aspectJExpressionPointcut.matches(method, String.class));
    }

    @Test
    public void testDynamic(){
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.huaxin.beans.bean.IUserService.*(..))"));
        // 代理对象
        IUserService proxyByJDK = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        IUserService proxyByCglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试
        System.out.println(proxyByJDK.queryUserInfo());
        System.out.println();
        System.out.println(proxyByCglib.register("zhjj"));
    }
}
