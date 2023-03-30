package filters;

import jakarta.servlet.http.HttpFilter;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class IPFilter
 */
@WebFilter(value="/RandomAdminKey.jsp",initParams={@WebInitParam(name="AllowIP",value="(127[.]0[.]0[.]1)|(localhost)|(0:0:0:0:0:0:0:1)|(10[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3})|(172[.]((1[6-9])|(2\\d)|(3[01]))[.]\\d{1,3}[.]\\d{1,3})|(192[.]168[.]\\d{1,3}[.]\\d{1,3})|(169[.]254[.]\\d{1,3}[.]\\d{1,3})")})
public class IPFilter extends HttpFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected FilterConfig filterConfig;
	protected String AllowIP;
    /**
     * @see HttpFilter#HttpFilter()
     */
    public IPFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		this.filterConfig = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");//设置编码格式
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");//定义错误转向页面
		//读出本地Ip
		String remoteIP = request.getRemoteAddr();
		//String remoteIP = InetAddress.getLocalHost().getHostAddress();
		//String remoteIP = getAllIpAddress();
		//System.out.println(remoteIP);
		//将其与不过滤的Ip比较，如果不相同，就转到错误处理界面
		if (!Pattern.matches(AllowIP, remoteIP)) {
			dispatcher.forward(request, response);
		}else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = fConfig;//过滤器初始化
		AllowIP = fConfig.getInitParameter("NoFilteredIP");//获取被过滤的Ip
		if (AllowIP==null) {
			AllowIP="(127[.]0[.]0[.]1)|(localhost)|(0:0:0:0:0:0:0:1)|(10[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3})|(172[.]((1[6-9])|(2\\d)|(3[01]))[.]\\d{1,3}[.]\\d{1,3})|(192[.]168[.]\\d{1,3}[.]\\d{1,3})|(169[.]254[.]\\d{1,3}[.]\\d{1,3})";
		}

	}
	
	public static String getAllIpAddress() {
		InetAddress ipAddress = null;
		try {
            //get all network interface
            Enumeration<NetworkInterface> allNetworkInterfaces = 
                    NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface = null;
            
            //check if there are more than one network interface
            while (allNetworkInterfaces.hasMoreElements()) {
                //get next network interface
                networkInterface = allNetworkInterfaces.nextElement();
                //output interface's name
                //System.out.println("network interface: " + networkInterface.getDisplayName());
                
                                //get all ip address that bound to this network interface
                Enumeration<InetAddress> allInetAddress = 
                        networkInterface.getInetAddresses();
                
                
                //check if there are more than one ip addresses
                //band to one network interface
                //while (allInetAddress.hasMoreElements()) {
                    //get next ip address
                    ipAddress = allInetAddress.nextElement();
                    if (ipAddress != null && ipAddress instanceof Inet4Address) {
                        //System.out.println("ip address: " + ipAddress.getHostAddress());
                        break;
                    }
                //}
            }
            
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipAddress.toString();
    }

}
