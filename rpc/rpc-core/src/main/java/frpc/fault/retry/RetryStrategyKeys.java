package frpc.fault.retry;

public interface RetryStrategyKeys {

    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 重试策略
     */
    String FIXED_INTERVAL = "fixedInterval";
}
