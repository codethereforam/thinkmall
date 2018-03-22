package priv.thinkam.thinkmall.enums;

/**
 * 商品状态枚举
 *
 * @author thinkam
 * @date 2018/03/19
 */
public enum ProductStatusEnum implements BaseEnum {
    /**
     * 正常
     */
    NORMAL(1, "正常"),
    /**
     * 下架
     */
    SOLD_OUT(2, "下架"),
    /**
     * 删除
     */
    DELETED(3, "删除");

    private int code;
    private String name;

    ProductStatusEnum(int code, String name) {
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
