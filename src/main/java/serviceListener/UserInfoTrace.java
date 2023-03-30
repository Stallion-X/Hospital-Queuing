package serviceListener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

/**
 * Application Lifecycle Listener implementation class UserInfoTrace
 *
 */
@WebListener
public class UserInfoTrace implements HttpSessionBindingListener {
	private String user;
	private UserInfoList container = UserInfoList.getInstance();

    /**
     * Default constructor. 
     */
    public UserInfoTrace() {
        // TODO Auto-generated constructor stub
    	user = "";
    }
    //设置在线监听人员
   	public void setUser(String user) {
   		this.user = user;
   	}

   	// 获取在线监听
   	public String getUser() {
   		return this.user;
   	}
   	//当Session有对象加入时自动被调用的方法
   	public void valueBound(HttpSessionBindingEvent arg0) {
   		System.out.println("[ " + this.user + " ]上线");
   	}
   	//当Session有对象移除时自动被调用的方法
   	public void valueUnbound(HttpSessionBindingEvent arg0) {
   		System.out.println("[ " + this.user + " ]下线");
   		if (user != "") {
   			container.removeUserInfo(user);
   		}
   	}
}
