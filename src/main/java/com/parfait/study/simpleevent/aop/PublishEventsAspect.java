package com.parfait.study.simpleevent.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Slf4j
@Profile("distributed-aop-async-event")
@Component
@Aspect
public class PublishEventsAspect {

    @Autowired
    private PublishEventAspect delegator;

    @Pointcut("@annotation(publishEvents)")
    public void pointcut(PublishEvents publishEvents) {
    }

    /**
     * compile time에 체크하지 않음 주의!!!!!!!!
     *
     * @param publishEvents
     * @param retVal
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @AfterReturning(pointcut = "pointcut(publishEvents)", returning = "retVal", argNames = "publishEvents,retVal")
    public void afterReturning(PublishEvents publishEvents, Object retVal) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        for (PublishEvent publishEvent : publishEvents.value()) {
            delegator.afterReturning(publishEvent, retVal);
        }
    }
}
