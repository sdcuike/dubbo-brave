package com.doctor.dubbo.extend.brave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.doctor.dubbo.extend.brave.helper.ApplicationContextProvider;
import com.github.kristofa.brave.Brave;

/**
 * @author sdcuike
 *
 * @time 2016年2月1日 下午4:05:31
 */
@Activate(group = { Constants.PROVIDER, Constants.CONSUMER })
public class BraveFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(BraveFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("BraveFilter starting ....");
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();

        Brave brave = context.getBean(Brave.class);
        DubboSpanNameProvider spanNameProvider = context.getBean(DubboSpanNameProvider.class);
        DubboServiceNameProvider serviceNameProvider = context.getBean(DubboServiceNameProvider.class);
        RpcContext rpcContext = RpcContext.getContext();
        if (rpcContext.isProviderSide()) {
            brave.serverRequestInterceptor().handle(new DubboProviderRequestAdapter(rpcContext, spanNameProvider));
        } else if (rpcContext.isConsumerSide()) {
            brave.clientRequestInterceptor().handle(new DubboConsumerRequestAdapter(rpcContext, spanNameProvider, serviceNameProvider));
        }

        Result result = null;
        try {
            result = invoker.invoke(invocation);
            return result;
        } finally {
            if (rpcContext.isProviderSide()) {
                brave.serverResponseInterceptor().handle(new DubboProviderResponesAdapter(result));
            } else if (rpcContext.isConsumerSide()) {
                brave.clientResponseInterceptor().handle(new DubboConsumerResponseAdapter(result));
            }
        }
    }

}
