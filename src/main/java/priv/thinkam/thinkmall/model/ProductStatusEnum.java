package priv.thinkam.thinkmall.model;

/**
 * 商品状态枚举
 *
 * @author thinkam
 * @date 2018/03/19
 */
public enum ProductStatusEnum {
	/**
	 * 正常
	 */
	NORMAL(1),
	/**
	 * 下架
	 */
	SOLD_OUT(2),
	/**
	 * 删除
	 */
	DELETED(3);

	private final int numberOfProductStatus;

	ProductStatusEnum(int numberOfProductStatus) {
		this.numberOfProductStatus = numberOfProductStatus;
	}

	public int getNumberOfProductStatus() {
		return numberOfProductStatus;
	}
}
