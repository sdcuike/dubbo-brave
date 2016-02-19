package com.doctor.dubbo.extend.brave;

import static com.github.kristofa.brave.internal.Util.checkNotNull;

import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kristofa.brave.SpanCollector;
import com.twitter.zipkin.gen.BinaryAnnotation;
import com.twitter.zipkin.gen.Span;

/**
 * @author sdcuike
 *
 * @time 2016年2月3日 下午1:55:59
 */
public class LoggingSpanCollector implements SpanCollector {
    private final Logger logger;
    private final Set<BinaryAnnotation> defaultAnnotations = new LinkedHashSet<>();

    public LoggingSpanCollector() {
        this(null);
    }

    public LoggingSpanCollector(String loggerName) {
        if (loggerName == null || loggerName.trim().length() == 0) {
            logger = LoggerFactory.getLogger(getClass());
        } else {
            logger = LoggerFactory.getLogger(loggerName);
        }

    }

    @Override
    public void collect(Span span) {
        checkNotNull(span, "Null span");
        if (!defaultAnnotations.isEmpty()) {
            for (final BinaryAnnotation ba : defaultAnnotations) {
                span.addToBinary_annotations(ba);
            }
        }

        if (getLogger().isInfoEnabled()) {
            getLogger().info(span.toString());
        }
    }

    @Override
    public void addDefaultAnnotation(String key, String value) {
        defaultAnnotations.add(new BinaryAnnotation(key, value));

    }

    Logger getLogger() {
        return logger;
    }
}
