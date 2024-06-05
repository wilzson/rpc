package frpc.fault.tolerant;

import frpc.model.RpcResponse;

import java.util.Map;

public interface TolerantStrategy {

    /**
     * 容错
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e);
}
