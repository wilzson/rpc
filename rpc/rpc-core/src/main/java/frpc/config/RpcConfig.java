package frpc.config;

import frpc.fault.retry.RetryStrategy;
import frpc.fault.retry.RetryStrategyKeys;
import frpc.fault.tolerant.TolerantStrategy;
import frpc.fault.tolerant.TolerantStrategyKeys;
import frpc.loadbalancer.LoadBalancerKeys;
import frpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * 用于保存配置信息
 */
@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "rpc";

    /**
     *  版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口
     */
    private Integer serverPort = 8082;

    /**
     * 模拟调用
     */
    private boolean mock = false;

    /**
     * 序列器
     */
    private String serializer = SerializerKeys.JDK;

    /**
     * 注册中心配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();

    /**
     * 负载均衡
     */
    private String loadBalancer = LoadBalancerKeys.ROUND_ROBIN;

    /**
     * 重试机制
     */
    private String retryStrategy = RetryStrategyKeys.FIXED_INTERVAL;

    /**
     * 容错策略
     */
    private String tolerantStrategy = TolerantStrategyKeys.FAIL_FAST;
}
