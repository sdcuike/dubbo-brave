package com.doctor.dubbo.extend.brave;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * @author sdcuike
 *
 * @time 2016年2月2日 上午10:02:06
 */
public class DefaultDubboSpanNameProvider implements DubboSpanNameProvider {

    @Override
    public String spanName(RpcContext rpcContext) {
        return rpcContext.getMethodName();
    }

}
