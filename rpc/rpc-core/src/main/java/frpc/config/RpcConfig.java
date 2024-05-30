package frpc.config;

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
     * 服务器主机名/ IP
     */
    private Integer serverPort = 8080;

    /**
     * 模拟调用
     */
    private boolean mock = false;

    /**
     * 序列器
     */
    private String serializer = SerializerKeys.JDK;
}