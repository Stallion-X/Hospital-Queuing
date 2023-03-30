package serviceListener;

import java.util.Vector;
//一个浏览器只能有一个用户，最好是用两种浏览器
public class UserInfoList {
	private static UserInfoList user = new UserInfoList();
	private Vector<String> vector = null;

	/*
	 * 利用private调用构造函数， 防止被外界产生新的instance对象
	 */
	public UserInfoList() {
		this.vector = new Vector<>();
	}

	/* 外界使用的instance对象 */
	public static UserInfoList getInstance() {
		return user;
	}

	/* 增加用户 */
	public boolean addUserInfo(String user) {
		if (user != null) {
			this.vector.add(user);
			return true;
		} else {
			return false;
		}
	}

	/* 获取用户列表 */
	public Vector<String> getList() {
		return vector;
	}

	/* 移除用户 */
	public void removeUserInfo(String user) {
		if (user != null) {
			vector.removeElement(user);
		}
	}
}
