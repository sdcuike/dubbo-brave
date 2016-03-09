package com.doctor.dubbo.extend.brave;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.rpc.RpcContext;

/**
 * @author sdcuike
 *
 * @time 2016年2月2日 上午10:41:32
 */
public class DefaulDubboServiceNameProvider implements DubboServiceNameProvider {

    @Override
    public String serviceName(RpcContext rpcContext, String defaultValue) {
        if (rpcContext == null || rpcContext.getUrl() == null || rpcContext.getUrl().getParameter(Constants.APPLICATION_KEY) == null) {
            return defaultValue;
        }
        return rpcContext.getUrl().getParameter(Constants.APPLICATION_KEY);
    }
}
