package priv.thinkam.thinkmall.model;

/**
 * 订单状态枚举
 *
 * @author thinkam
 * @date 2018/03/19
 */
public enum OrderStatusEnum {
	/**
	 * 已生成
	 */
	CREATED(1),
	/**
	 * 已发货
	 */
	CONSIGNED(2),
	/**
	 * 交易成功
	 */
	SUCCESS(3),
	/**
	 * 交易关闭
	 */
	CLOSED(4);

	private final int numberOfOrderStatus;

	OrderStatusEnum(int numberOfOrderStatus) {
		this.numberOfOrderStatus = numberOfOrderStatus;
	}

	public int getNumberOfOrderStatus() {
		return numberOfOrderStatus;
	}
}
