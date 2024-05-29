package registry;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 本地注册中心
 */
public class LocalRegistry {
    /**
     * 在泛型中，有两种主要形式：类型参数T和通配符？
     * T用于定义类型参数，而？通配符主要用于泛型方法的参数和泛型类的字段
     * T表示list里面只有某一类具体的对象，？表示它自己也不知道集合里会存放多少种类型的数据，因此list中我们可以放n种类型。
     */
    // 使用线程安全的ConcurrentHashMap存储服务注册信息
    private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    /**
     * 注册服务
     */
    public static void register(String serviceName, Class<?> implClass) {
        map.put(serviceName, implClass);
    }

    /**
     * 获取服务
     */
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     * 删除服务
     */
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
