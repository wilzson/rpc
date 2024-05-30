import frpc.config.RpcConfig;
import frpc.model.User;
import frpc.proxy.ServiceProxyFactory;
import frpc.utils.ConfigUtils;
import service.UserService;

public class ConsumerExample {
    public static void main(String[] args) {
//        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
//        System.out.println(rpc);
        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("fzf");
        // 调用
        User newUser = userService.getUser(user);
        System.out.println(newUser);
//        long number = userService.getNumber();
//        System.out.println(number);

        User user1 = userService.mockUser();
        System.out.println(user1);
    }
}
