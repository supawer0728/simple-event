package com.parfait.study.simpleevent.aop;

import com.parfait.study.simpleevent.model.event.EventHoldingValue;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PublishEvent {

    Class<? extends EventHoldingValue> eventType();

    String params() default "";
}
