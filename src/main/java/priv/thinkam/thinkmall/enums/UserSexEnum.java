package priv.thinkam.thinkmall.enums;

/**
 * 用户性别枚举
 *
 * @author thinkam
 * @date 2018/03/19
 */
public enum UserSexEnum implements BaseEnum {
    /**
     * 女
     */
    FEMALE(0, "女"),
    /**
     * 男
     */
    MALE(1, "男"),
    /**
     * 不愿透露
     */
    SECRET(2, "不愿透露");

    private int code;
    private String name;

    UserSexEnum(int code, String name) {
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
