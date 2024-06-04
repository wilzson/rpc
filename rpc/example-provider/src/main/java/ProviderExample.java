import frpc.RpcApplication;
import frpc.ServiceMetaInfo;
import frpc.config.RegistryConfig;
import frpc.config.RpcConfig;
import frpc.proxy.RegistryFactory;
import frpc.registry.LocalRegistry;
import frpc.registry.Registry;
import frpc.server.HttpServer;
import frpc.server.VertxHttpServer;
import frpc.server.tcp.VertxTcpServer;
import frpc.service.UserService;

public class ProviderExample {

    public static void main(String[] args) {
        //RPC 框架初始化
        RpcApplication.init();

        // 注册服务到注册器中
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();

        // 注册服务到注册中心
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 提供服务
//        HttpServer httpServer = new VertxHttpServer();
//        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());

        VertxTcpServer tcpServer = new VertxTcpServer();
        tcpServer.doStart(8082);
    }
}
