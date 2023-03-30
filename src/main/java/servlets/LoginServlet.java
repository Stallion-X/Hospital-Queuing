package servlets;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.LogRegService;
import beans.*;

/**
 * Servlet implementation class LoginServlet
 */
/** 
 * @author Stallion-X
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("laction");
		if ("login".equals(action)) {
			login(request, response);
		}
		if ("register".equals(action)) {
			register(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public boolean validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		ServletContext application = getServletContext();
		LogRegService lgService=new LogRegService();
		switch (type) {
		case "d":
			if (request.getParameter("pwd").equals(application.getAttribute("adminkey"))&&lgService.validateD(request.getParameter("acc"))) {
				return true;
			}
			return false;
		case "p":
			String pnum = request.getParameter("acc");
			String ppsd = request.getParameter("pwd");
			if (lgService.validateP(pnum, ppsd)) {
				return true;
			}
			return false;
		case "su":
			if (request.getParameter("pwd").equals(application.getAttribute("adminkey"))) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String path = null;
		switch (type) {
		case "d":
			String dcon = request.getParameter("acc");
			/*Cookie[] cookies = request.getCookies();
			if (cookies!=null ) {
				for (int i=0;i<cookies.length;i++) {
					if (cookies[i].getName().equals("dcon")) {
						cookies[i].setMaxAge(0);
						response.addCookie(cookies[i]);
					}
				}
			}*/
			Cookie cookie = new Cookie("dcon",dcon);
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			request.setAttribute("fromlogin", true);
			path = "/DoctorServlet";
			break;
		case "p":
			String pnum = request.getParameter("acc");
			String ppsd = request.getParameter("pwd");
			Cookie cpnum = new Cookie("pnum",pnum);
			cpnum.setMaxAge(60*60*24);
			response.addCookie(cpnum);
			Cookie cppsd = new Cookie("ppsd",ppsd);
			cppsd.setMaxAge(60*60*24);
			response.addCookie(cppsd);
			path = "PatientServlet";
			if (validate(request,response)) {
				response.sendRedirect(path+"?action=Init");
			}
			else {
				path = "index.html"+"?isSuc=0";
				response.sendRedirect(path);
			}
			return;
		case "su":
			String pname1 = request.getParameter("acc");
			String ppsd1 = request.getParameter("pwd");
			Cookie cpname1 = new Cookie("pname",pname1);
			cpname1.setMaxAge(60*60*24);
			response.addCookie(cpname1);
			Cookie cppsd1 = new Cookie("ppsd",ppsd1);
			cppsd1.setMaxAge(60*60*24);
			response.addCookie(cppsd1);
			if (validate(request,response)) {
				response.sendRedirect("BackendManage_Administrator.jsp");
			}
			else {
				path = "index.html"+"?isSuc=0";
				response.sendRedirect(path);
			}
			return;
		}
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
		if (validate(request,response)) {
			requestDistpatcher.forward(request, response);
		}
		else {
			path = "index.html"+"?isSuc=0";
			response.sendRedirect(path);
		}
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String pnum = request.getParameter("acc");
			String pname = request.getParameter("pname");
			String psex = request.getParameter("psex");
			int page = Integer.parseInt(request.getParameter("page"));
			String pid = request.getParameter("pid");
			String ptel = request.getParameter("ptel");
			String ppsd = request.getParameter("pwd");
			
			PatientBean p = new PatientBean();
			p.setPnum(pnum);
			p.setPname(pname);
			p.setPsex(psex);
			p.setPage(page);
			p.setPid(pid);
			p.setPtel(ptel);
			p.setPpsd(ppsd);
			
			LogRegService lgService=new LogRegService();
			if (lgService.registerP(p)) {
				response.sendRedirect("index.html");
			}
			else {
				response.sendRedirect("index.html?isSuc=0");
			}
		} catch (NumberFormatException e) {
			
		}
	}

}
