package com.doctor.dubbo.extend.brave;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.alibaba.dubbo.rpc.Result;
import com.github.kristofa.brave.ClientResponseAdapter;
import com.github.kristofa.brave.KeyValueAnnotation;

/**
 * @author sdcuike
 *
 * @time 2016年2月2日 上午10:51:04
 */
public class DubboConsumerResponseAdapter implements ClientResponseAdapter {

    private final Result result;

    public DubboConsumerResponseAdapter(Result result) {
        this.result = result;
    }

    @Override
    public Collection<KeyValueAnnotation> responseAnnotations() {
        if (result != null) {
            Throwable exception = result.getException();
            if (exception != null) {
                KeyValueAnnotation statusAnnotation = KeyValueAnnotation.create("dubbo.exception", exception.getMessage());
                return Arrays.asList(statusAnnotation);
            }
        }
        return Collections.emptyList();
    }
}
