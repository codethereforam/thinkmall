package priv.thinkam.thinkmall.model;

import java.util.Date;

/**
 * @author thinkam
 * @date 2018/03/18
 */
public class User {
	/**
	 * 用户编号
	 */
	private Long userId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码 = md5(真实密码 + 盐)
	 */
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 电话号码
	 */
	private String phoneNumber;

	/**
	 * 性别(0:女，1:男，2:不愿透露)
	 */
	private Byte sex;

	/**
	 * 是否锁定(1表示是,0表示否)
	 */
	private Boolean locked;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date modifiedTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", userId=").append(userId);
		sb.append(", username=").append(username);
		sb.append(", password=").append(password);
		sb.append(", salt=").append(salt);
		sb.append(", email=").append(email);
		sb.append(", phoneNumber=").append(phoneNumber);
		sb.append(", sex=").append(sex);
		sb.append(", locked=").append(locked);
		sb.append(", createTime=").append(createTime);
		sb.append(", modifiedTime=").append(modifiedTime);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		User other = (User) that;
		return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
				&& (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
				&& (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
				&& (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
				&& (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
				&& (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
				&& (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
				&& (this.getLocked() == null ? other.getLocked() == null : this.getLocked().equals(other.getLocked()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getModifiedTime() == null ? other.getModifiedTime() == null : this.getModifiedTime().equals(other.getModifiedTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
		result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
		result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
		result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
		result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
		result = prime * result + ((getLocked() == null) ? 0 : getLocked().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getModifiedTime() == null) ? 0 : getModifiedTime().hashCode());
		return result;
	}
}