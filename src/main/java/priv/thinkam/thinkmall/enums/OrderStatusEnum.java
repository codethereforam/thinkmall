package priv.thinkam.thinkmall.enums;

/**
 * 订单状态枚举
 *
 * @author thinkam
 * @date 2018/03/19
 */
public enum OrderStatusEnum implements BaseEnum {
    /**
     * 已生成
     */
    CREATED(1, "已生成"),
    /**
     * 已发货
     */
    CONSIGNED(2, "已发货"),
    /**
     * 交易成功
     */
    SUCCESS(3, "交易成功"),
    /**
     * 交易关闭
     */
    CLOSED(4, "交易关闭");

    private int code;
    private String name;

    OrderStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
