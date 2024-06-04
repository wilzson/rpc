package frpc.proxy;

import cn.hutool.core.collection.CollUtil;
import frpc.RpcApplication;
import frpc.config.RpcConfig;
import frpc.constant.RpcConstant;
import frpc.RpcRequest;
import frpc.RpcResponse;
import frpc.ServiceMetaInfo;
import frpc.loadbalancer.LoadBalanceFactory;
import frpc.loadbalancer.LoadBalancer;
import frpc.registry.Registry;
import frpc.serializer.Serializer;
import frpc.serializer.SerializerFactory;
import frpc.server.tcp.VertxTcpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态代理 需要实现InvocationHandler接口的invoke方法
 * 静态代理——相当于给每一个方法写一个特定的方法，然后去远程访问并返回对应结果
 */
public class ServiceProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        String serviceName = method.getDeclaringClass().getName();

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 发送请求
            // 从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry()); // 注册中心
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }
//            // 暂时取第一个
//            ServiceMetaInfo selectedService = serviceMetaInfoList.get(0);
            LoadBalancer loadBalancer = LoadBalanceFactory.getInstance(rpcConfig.getLoadBalancer());
            // 将调用方法名作为负载均衡参数
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());
            ServiceMetaInfo selectedService = loadBalancer.select(requestParams, serviceMetaInfoList);

            // 发送TCP请求
            RpcResponse response = VertxTcpClient.doRequest(rpcRequest, selectedService);
            return response.getData();
        } catch (Exception e) {
            throw new RuntimeException("调用失败");
        }
    }
}
