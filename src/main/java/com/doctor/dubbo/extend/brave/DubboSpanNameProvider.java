package com.doctor.dubbo.extend.brave;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * @author sdcuike
 *
 * @time 2016年2月2日 上午10:11:04
 */
public interface DubboSpanNameProvider {
    String spanName(RpcContext rpcContext, String defaultValue);
}
