/** 
* @author PC
*/
package servlets;

import beans.BackendManage_UserBean;
import service.BackendManage_UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class Main
 */
@WebServlet("/BackendManage_Main")
public class BackendManage_Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request. setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("Initialization".equals(action)) {
			//System.out.println("ok ");
			Initialization(request, response);
		}
		if ("add".equals(action)) {
			add(request, response);
		}
		if ("delete".equals(action)) {
			delete(request, response);
		}
//		if ("export".equals(action)) {
//			exportExcel(request, response);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request. setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

	public void Initialization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		BackendManage_UserService userService = new BackendManage_UserService();
		
		List<BackendManage_UserBean> list1 = userService.browse1();
		List<BackendManage_UserBean> list2 = userService.browse2();
		List<BackendManage_UserBean> list3 = userService.browse3();
		List<BackendManage_UserBean> list4 = userService.browse4();
		List<BackendManage_UserBean> list5 = userService.browse5();
		List<BackendManage_UserBean> list6 = userService.browse6();
		List<BackendManage_UserBean> list7 = userService.browse7();
		List <String> list0 = userService.analyze(list3, list5, list7);
		
		request.setAttribute("allUsers0", list0);
		request.setAttribute("allUsers1", list1);
		request.setAttribute("allUsers2", list2);
		request.setAttribute("allUsers3", list3);
		request.setAttribute("allUsers4", list4);
		request.setAttribute("allUsers5", list5);
		request.setAttribute("allUsers6", list6);
		request.setAttribute("allUsers7", list7);
		String path = "/BackendManage_Administrator.jsp?ok=0";
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher = request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);
	
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String no = request.getParameter("no");
		String table = request.getParameter("table");
		String num = request.getParameter("num");
		BackendManage_UserService userService = new BackendManage_UserService();
		String path;
		if(userService.delete(no, table, num) == 1) {
			path = "/BackendManage_Administrator.jsp?delete=1";
		}else {
			path = "/BackendManage_Administrator.jsp?delete=2";
		}
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher = request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);
		
	}
	
	
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BackendManage_UserService userService = new BackendManage_UserService();
		BackendManage_UserBean user = new BackendManage_UserBean();
		String path = null;
		String table_no = request.getParameter("table_no");
		if(table_no.equals("1")) {
			String dnum = request.getParameter("dnum");
			String dname = request.getParameter("dname");
			String dsex = request.getParameter("dsex");
			String dage = request.getParameter("dage");
			String dtel = request.getParameter("dtel");
			String dtitle = request.getParameter("dtitle");
			String dpost = request.getParameter("dpost");
			String dedu = request.getParameter("dedu");
			String ddepartment = request.getParameter("ddepartment");
			String dcon = request.getParameter("dcon");
			int dageInt = Integer.parseInt(dage);
			
			user.setDnum(dnum);
			user.setDname(dname);
			user.setDsex(dsex);
			user.setDage(dageInt);
			user.setDtel(dtel);
			user.setDtitle(dtitle);
			user.setDpost(dpost);
			user.setDedu(dedu);
			user.setDdepartment(ddepartment);
			user.setDcon(dcon);
			if(userService.addUser(user, 1)) {
				path = "/BackendManage_Administrator.jsp?add=1";
			}else {
				path = "/BackendManage_Administrator.jsp?add=2";
			}
		}else if(table_no.equals("2")) {
			String pnum = request.getParameter("pnum");
			String pname = request.getParameter("pname");
			String psex = request.getParameter("psex");
			String page = request.getParameter("page");
			String pid = request.getParameter("pid");
			String ptel = request.getParameter("ptel");
			String ppsd = request.getParameter("ppsd");
			String pinfor = request.getParameter("pinfor");
			String pmdad = request.getParameter("pmdad");
			
			
			int pageInt = Integer.parseInt(page);
			
			user.setPnum(pnum);
			user.setPname(pname);
			user.setPsex(psex);
			user.setPage(pageInt);
			user.setPid(pid);
			user.setPtel(ptel);
			user.setPpsd(ppsd);
			user.setPinfor(pinfor);
			user.setPmdad(pmdad);
			
			if(userService.addUser(user, 2)) {
				path = "/BackendManage_Administrator.jsp?add=1";
			}else {
				path = "/BackendManage_Administrator.jsp?add=2";
			}
		}else if(table_no.equals("3")) {
			String anum = request.getParameter("anum");
			String pnum = request.getParameter("pnum");
			String ptel = request.getParameter("ptel");
			String dnum = request.getParameter("dnum");
			String yytime = request.getParameter("yytime");
			String jztime = request.getParameter("jztime");
			String dcon = request.getParameter("dcon");
			String astate = request.getParameter("astate");
			String atype = request.getParameter("atype");
			
			int anumInt = Integer.parseInt(anum);
			int atypeInt = Integer.parseInt(atype);
			int astateInt = Integer.parseInt(astate);
			
			user.setAnum(anumInt);
			user.setPnum(pnum);
			user.setPtel(ptel);
			user.setDnum(dnum);
			user.setYytime(yytime);
			user.setJztime(jztime);
			user.setDcon(dcon);
			user.setAstate(astateInt);
			user.setAtype(atypeInt);
			if(userService.addUser(user, 3)) {
				path = "/BackendManage_Administrator.jsp?add=1";
			}else {
				path = "/BackendManage_Administrator.jsp?add=2";
			}
		}else if(table_no.equals("4")) {
			String drnum = request.getParameter("drnum");
			String drname = request.getParameter("drname");
			String drprice = request.getParameter("drprice");
			String drtype = request.getParameter("drtype");
	
	
			float drpriceFloat = Float.parseFloat(drprice);
			
			user.setDrnum(drnum);
			user.setDrname(drname);
			user.setDrprice(drpriceFloat);
			user.setDrtype(drtype);
	
			if(userService.addUser(user, 4
					)) {
				path = "/BackendManage_Administrator.jsp?add=1";
			}else {
				path = "/BackendManage_Administrator.jsp?add=2";
			}
		}else if(table_no.equals("5")) {
			String tmnum = request.getParameter("tmnum");
			String pnum = request.getParameter("pnum");
			String drnum = request.getParameter("drnum");
			String drquantity = request.getParameter("drquantity");
			String tmstate = request.getParameter("tmstate");
			String ptel = request.getParameter("ptel");
			
			int drquantityInt = Integer.parseInt(drquantity);
			int tmstateInt = Integer.parseInt(tmstate);
			int tmnumInt = Integer.parseInt(tmnum);
			
			user.setTmnum(tmnumInt);
			user.setPnum(pnum);
			user.setDrnum(drnum);
			user.setDrquantity(drquantityInt);
			user.setTmstate(tmstateInt);
			user.setPtel(ptel);
			
			if(userService.addUser(user, 5)) {
				path = "/BackendManage_Administrator.jsp?add=1";
			}else {
				path = "/BackendManage_Administrator.jsp?add=2";
			}
		}else if(table_no.equals("6")) {
			String mtnum = request.getParameter("mtnum");
			String mtname = request.getParameter("mtname");
			String mtprice = request.getParameter("mtprice");
			String mtplace = request.getParameter("mtplace");

			float mtpriceFloat = Float.parseFloat(mtprice);
			
			user.setMtnum(mtnum);
			user.setMtname(mtname);
			user.setMtprice(mtpriceFloat);
			user.setMtplace(mtplace);
			
			if(userService.addUser(user, 6)) {
				path = "/BackendManage_Administrator.jsp?add=1";
			}else {
				path = "/BackendManage_Administrator.jsp?add=2";
			}
		}else if(table_no.equals("7")) {
			String mtenum= request.getParameter("mtenum");
			String pnum = request.getParameter("pnum");
			String ptel = request.getParameter("ptel");
			String mtnum = request.getParameter("mtnum");
			String mtetime = request.getParameter("mtetime");
			String mtstate= request.getParameter("mtstate");
			
			int mtstateInt = Integer.parseInt(mtstate);
			int mtenumInt = Integer.parseInt(mtenum);
			
			user.setMtenum(mtenumInt);
			user.setPnum(pnum);
			user.setPtel(ptel);
			user.setMtnum(mtnum);
			user.setMtetime(mtetime);
			user.setMtstate(mtstateInt);
			if(userService.addUser(user, 7)) {
				path = "/BackendManage_Administrator.jsp?add=1";
			}else {
				path = "/BackendManage_Administrator.jsp?add=2";
			}
		}

		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher = request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);
		
	}
}
