package com.doctor.dubbo.extend.brave;

import java.util.concurrent.atomic.AtomicInteger;

import com.github.kristofa.brave.SpanCollectorMetricsHandler;

/**
 * @author sdcuike
 *
 * @time 2016年2月22日 下午4:14:14
 */
public class TestMetricsHander implements SpanCollectorMetricsHandler {

    final AtomicInteger acceptedSpans = new AtomicInteger();
    final AtomicInteger droppedSpans = new AtomicInteger();

    @Override
    public void incrementAcceptedSpans(int quantity) {
        acceptedSpans.addAndGet(quantity);
    }

    @Override
    public void incrementDroppedSpans(int quantity) {
        droppedSpans.addAndGet(quantity);
    }

}
