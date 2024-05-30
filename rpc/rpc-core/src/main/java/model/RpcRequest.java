package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @NoArgsConstructor 自动生成无参的构造函数
 * @AllArgsConstructor 自动生成全参数的构造函数
 * @Data 包含get/set函数
 * @Builder 自动生成一个构建器类，用于创建带有多个属性的对象
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RpcRequest implements Serializable {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数类型列表
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数列表
     */
    private Object[] args;
}
