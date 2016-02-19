package com.doctor.dubbo.extend.brave;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.alibaba.dubbo.rpc.Result;
import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.ServerResponseAdapter;

/**
 * @author sdcuike
 *
 * @time 2016年2月2日 上午10:19:51
 */
public class DubboProviderResponesAdapter implements ServerResponseAdapter {
    private final Result result;

    public DubboProviderResponesAdapter(Result result) {
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
