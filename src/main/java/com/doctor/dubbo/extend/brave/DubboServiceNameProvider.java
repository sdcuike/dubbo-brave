package com.doctor.dubbo.extend.brave;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * @author sdcuike
 *
 * @time 2016年2月2日 上午10:40:26
 */
public interface DubboServiceNameProvider {
    String serviceName(RpcContext rpcContext, String defaultValue);
}
