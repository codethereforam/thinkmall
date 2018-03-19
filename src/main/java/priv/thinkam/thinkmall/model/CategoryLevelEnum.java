package priv.thinkam.thinkmall.model;

/**
 * 类别层次枚举
 *
 * @author thinkam
 * @date 2018/03/19
 */
public enum CategoryLevelEnum {
	/**
	 * 一级类别
	 */
	ONE(1),
	/**
	 * 二级类别
	 */
	TWO(2),
	/**
	 * 三级类别
	 */
	THREE(3);

	private final int numberOfCategoryLevel;

	CategoryLevelEnum(int numberOfCategoryLevel) {
		this.numberOfCategoryLevel = numberOfCategoryLevel;
	}

	public int getNumberOfCategoryLevel() {
		return numberOfCategoryLevel;
	}
}
