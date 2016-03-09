package com.doctor.dubbo.extend.brave;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.rpc.RpcContext;

/**
 * @author sdcuike
 *
 * @time 2016年2月2日 上午10:02:06
 */
public class DefaultDubboSpanNameProvider implements DubboSpanNameProvider {

    @Override
    public String spanName(RpcContext rpcContext, String defaultValue) {
        if (rpcContext == null || rpcContext.getUrl() == null || rpcContext.getUrl().getParameter(Constants.APPLICATION_KEY) == null) {
            return defaultValue;
        }
        return rpcContext.getUrl().getParameter(Constants.APPLICATION_KEY);
    }
}
