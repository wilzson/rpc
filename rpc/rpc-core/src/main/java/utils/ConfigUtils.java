package utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 */
public class ConfigUtils {
    /**
     * 加载配置对象
     * @param tClass
     * @param prefix
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置对象，支持区分环境
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("applicaiton");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass, prefix);

        // TODO 支持yaml和yml等更多类型的文件配置读取
//        String[] suffix = {".properties", ".yaml", "yml"};
//        for(int i = 0; i < suffix.length; i++) {
//            configFileBuilder.append(suffix[i]);
//            Props props = new Props(configFileBuilder.toString());
//            if (props != null) {
//                return props.toBean(tClass, prefix);
//            }
//        }
    }
}
