package frpc.proxy;

import com.github.javafaker.Faker;
//import frpc.model.User;
import frpc.model.User;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@Slf4j
public class MockServiceProxy implements InvocationHandler {
    /**
     * Mock服务代理
     * @param proxy the frpc.proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the frpc.proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * frpc.proxy interface that the frpc.proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the frpc.proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据方法的返回值类型，生成特定的默认值对象
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        return getDefaultObject(methodReturnType);
    }

    private Object getDefaultObject(Class<?> type) {
        // isPrimitive() 判断是否为基本类型
        if (type.isPrimitive()) {
            if (type == boolean.class)
                return false;
            else if (type == short.class) {
                return (short) 0;
            } else if (type == int.class) {
                return 0;
            } else if (type == long.class) {
                return 0L;
            }
        }
        // 对象类型
        Faker faker = new Faker();
        if (type == User.class) {
            User user = new User();
            String name = faker.name().fullName();
            System.out.println(name);
            user.setName(name);
            return user;
        }
        return null;
    }
}
