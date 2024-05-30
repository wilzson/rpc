import registry.LocalRegistry;
import server.HttpServer;
import server.VertxHttpServer;
import service.UserService;

public class ProviderExample {

    public static void main(String[] args) {
        //RPC 框架初始化
        RpcApplication.init();

        // 注册服务到注册器中
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
