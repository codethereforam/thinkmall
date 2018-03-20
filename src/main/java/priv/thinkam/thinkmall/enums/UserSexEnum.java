package priv.thinkam.thinkmall.enums;

/**
 * 用户性别枚举
 * @author thinkam
 * @date 2018/03/19
 */
public enum UserSexEnum {
	/**
	 * 女
	 */
	FEMALE(0),
	/**
	 * 男
	 */
	MALE(1),
	/**
	 * 不愿透露
	 */
	SECRET(2);

	private final int numberOfSex;

	UserSexEnum(int numberOfSex) {
		this.numberOfSex = numberOfSex;
	}

	public int getNumberOfSex() {
		return numberOfSex;
	}
}
