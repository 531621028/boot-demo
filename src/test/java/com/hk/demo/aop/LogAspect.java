package com.hk.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hukangkang 2018/8/22
 */
@Aspect
@Configuration
public class LogAspect {
    //execution([可见性] 返回类型 [声明类型].方法名(参数) [异常])
    @Pointcut("execution(* com.hk.demo.aop..*(..))")
    public void pointcutName() {}

    // args(id,..)中的参数按照顺序进行匹配
    @Before("pointcutName() && args(id,..)")
    public void performance(long id) {
        System.out.println("Before....参数值为:" + id);
    }

    //环绕通知(@Around) -- 需要切面方法携带ProceedingJoinPoion参数，类似于一个完整的动态代理，环绕通知必须要有一个返回值，是目标方法的返回值.
    @Around("pointcutName() && args(id,..)")
    public Object around(ProceedingJoinPoint joinPoint, long id) throws Throwable {
        System.out.println("Around前....参数值为:" + id);
        Object result = joinPoint.proceed();
        System.out.println("Around后....参数值为:" + id);
        return result;
    }

    @After("pointcutName() && args(id,..)")
    public void after(long id) {
        System.out.println("After....参数值为:" + id);
    }

    //@AfterReturning能正常切入，@Around需要把返回target的执行结果返回

    @AfterReturning(value = "pointcutName() && args(id,..)", returning = "result")
    public void afterReturning(long result, long id) {
        System.out.println("AfterReturning....参数值为:" + result);
    }

}
