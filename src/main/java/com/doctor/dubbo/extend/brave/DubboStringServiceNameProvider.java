package com.doctor.dubbo.extend.brave;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * @author sdcuike
 *
 * @time 2016年2月2日 上午10:41:32
 */
public class DubboStringServiceNameProvider implements DubboServiceNameProvider {

    private final String serviceName;

    public DubboStringServiceNameProvider(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String serviceName(RpcContext rpcContext) {
        return serviceName;
    }

}
