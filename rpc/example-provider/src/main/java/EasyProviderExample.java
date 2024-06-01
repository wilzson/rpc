import frpc.registry.LocalRegistry;
import frpc.server.HttpServer;
import frpc.server.VertxHttpServer;
import frpc.service.UserService;

public class EasyProviderExample {
    public static void main(String[] args) {

        // 注册服务到注册器中
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
