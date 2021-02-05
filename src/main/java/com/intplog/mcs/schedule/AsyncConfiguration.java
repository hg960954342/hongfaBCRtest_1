package com.intplog.mcs.schedule;

import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 异步控制类
 */
@Component
public class AsyncConfiguration {
    private final Set<String> asyncSet = new ConcurrentSkipListSet<String>();

    public Set<String> getAsyncSet() {
        return asyncSet;
    }


}
