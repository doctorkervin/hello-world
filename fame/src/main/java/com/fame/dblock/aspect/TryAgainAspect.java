package com.fame.dblock.aspect;

import com.fame.exception.TryAgainException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.StaleObjectStateException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;


/**
 * @program: hello-world
 * @desc: 重试业务
 * @author: kervin
 * @time: 2019-12-26 16:26
 */
@Slf4j
@Aspect
@Component
public class TryAgainAspect {
    public static final int MAX_RETRY_TIMES = 5;//max retry times

    @Pointcut("@annotation(com.fame.dblock.annotation.RetryOnFailure)") //self-defined pointcount for RetryOnFailure
    public void retryOnFailure(){}

    @Around("retryOnFailure()") //around can be execute before and after the point
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int attempts = 0;

        do {
            attempts++;
            try {
                pjp.proceed();
            } catch (Exception e) {
                //if (e instanceof ObjectOptimisticLockingFailureException ||e instanceof StaleObjectStateException) {
                if ( e instanceof TryAgainException) {
                    log.info("retrying....times:{}", attempts);
                    if (attempts > MAX_RETRY_TIMES) {
                        log.info("retry excceed the max times..");
                        throw e;
                    }
                }

            }
        } while (attempts < MAX_RETRY_TIMES);
        return null;
    }

}
