import frpc.model.User;
import frpc.proxy.ServiceProxyFactory;
import frpc.service.UserService;

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
        System.out.println(newUser.getName());
//        long number = userService.getNumber();
//        System.out.println(number);
        User newUser1 = userService.getUser(user);
        System.out.println(newUser1.getName());
        User user1 = userService.getUser(user);
        System.out.println(user1.getName());
    }
}
