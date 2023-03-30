package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletContext;
import beans.AppointmentBean;
import beans.DoctorBean;
import beans.PatientBean;
import beans.TakeMedicineBean;
import service.*;

/**
 * Servlet implementation class PatientServlet
 */
/** 
* @author Stallion-X
*/
@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equals("Init")) {
			Initialization(request, response);
		}else if(action.equals("appoList")){//科室候诊队列
			appoList(request, response);
		}else if(action.equals("appoListOnly")){//科室候诊队列
			appoListOnly(request, response);
		}else if(action.equals("mExamList")){//医技检查队列
			mExamList(request, response);
		}else if(action.equals("TMB")){//取药排队队列
			TMB(request, response);
		}else if(action.equals("toAppo")) {//就诊单界面
			toAppo(request, response);
		}else if(action.equals("toMExam")) {//检查单界面
			toMExam(request, response);
		}else if(action.equals("toDrug")) {//取药单界面
			toDrug(request, response);
		}else if(action.equals("Appo")) {//就诊单提交
			Appo(request, response);
		}else if(action.equals("MExam")) {//检查单提交
			MExam(request, response);
		}else if(action.equals("Drug")) {//取药单提交
			Drug(request, response);
		}else if(action.equals("toReg")) {//挂号界面
			toReg(request, response);
		}else if(action.equals("Reg")) {//挂号提交
			Reg(request, response);
		}else if(action.equals("Del")) {//ajax叫号自动刷新
			Del(request, response);
		}else if(action.equals("fresh")) {//ajax叫号自动刷新
			fresh(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void Initialization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		PatientUserService userService = new PatientUserService();
		List<DoctorBean> Doctor_list = userService.browseDoctors();
		String pmenu = request.getParameter("pmenu");
		request.setAttribute("Doctor_list",Doctor_list);
		request.setAttribute("pmenu",pmenu);
		
		String path = "/patient_index.jsp";
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher = request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);
		
	}
	
	private void appoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String pmenu = request.getParameter("pmenu");
		PatientUserService userService = new PatientUserService();
		request.setAttribute("Appo_list",userService.browseAppo());
		request.setAttribute("pmenu",pmenu);
		Initialization(request, response);
	}
	
	private void appoListOnly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String pmenu = request.getParameter("pmenu");
		String pnum = request.getParameter("pnum");
		PatientUserService userService = new PatientUserService();
		request.setAttribute("Appo_list",userService.browseAppoByPnum(pnum));
		request.setAttribute("pmenu",pmenu);
		Initialization(request, response);
	}
	
	private void mExamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String pmenu = request.getParameter("pmenu");
		PatientUserService userService = new PatientUserService();
		request.setAttribute("MTB_list",userService.browseMTB());
		request.setAttribute("pmenu",pmenu);
		Initialization(request, response);
	}
	
	private void TMB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String pmenu = request.getParameter("pmenu");
		PatientUserService userService = new PatientUserService();
		List<TakeMedicineBean> TMB_list = userService.browseMB();
		request.setAttribute("TMB_list",TMB_list);
		request.setAttribute("pmenu",pmenu);
		Initialization(request, response);
	}
	
	private void toAppo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String pmenu = request.getParameter("pmenu");
		String pnum = request.getParameter("pnum");
		PatientUserService userService = new PatientUserService();
		request.setAttribute("latestAppo", userService.latestAppo(pnum));
		request.setAttribute("pmenu",pmenu);
		Initialization(request, response);
	}
	
	private void toMExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String pmenu = request.getParameter("pmenu");
		String pnum = request.getParameter("pnum");
		PatientUserService userService = new PatientUserService();
		request.setAttribute("latestMTB", userService.latestMTB(pnum));
		request.setAttribute("pmenu",pmenu);
		Initialization(request, response);
	}
	
	private void toDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String pmenu = request.getParameter("pmenu");
		String pnum = request.getParameter("pnum");
		PatientUserService userService = new PatientUserService();
		request.setAttribute("latestTMB", userService.latestTMB(pnum));
		request.setAttribute("pmenu",pmenu);
		Initialization(request, response);
	}
	
	private void Appo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int anum = Integer.parseInt(request.getParameter("anum"));
		PatientUserService userService = new PatientUserService();
		userService.confirmAppo(anum);
		Initialization(request, response);
	}
	
	private void MExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int mtenum = Integer.parseInt(request.getParameter("mtenum"));
		PatientUserService userService = new PatientUserService();
		userService.confirmMExam(mtenum);
		Initialization(request, response);
	}
	
	private void Drug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int tmnum = Integer.parseInt(request.getParameter("tmnum"));
		PatientUserService userService = new PatientUserService();
		userService.confirmDrug(tmnum);
		Initialization(request, response);
	}
	
	private void toReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String pmenu = request.getParameter("pmenu");
		request.setAttribute("pmenu",pmenu);
		if (request.getParameter("selected")!=null) {
			request.setAttribute("selected",request.getParameter("selected"));
		}
		Initialization(request, response);
	}
	
	private void Reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String pnum = request.getParameter("pnum");
		String ddepartment=request.getParameter("ddepartment");
		String dnum=request.getParameter("dnum");
		String yytime=request.getParameter("yytime");
		String jztime=request.getParameter("jztime");
		String dcon=request.getParameter("dcon");
		PatientUserService userService = new PatientUserService();
		AppointmentBean appo = new AppointmentBean();
		appo.setPnum(pnum);
		appo.setDdepartment(ddepartment);
		appo.setDnum(dnum);
		appo.setYytime(yytime);
		appo.setJztime(jztime);
		appo.setDcon(dcon);
		userService.insertAppo(appo);
		Initialization(request, response);
	}
	
	private void Del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int anum = Integer.parseInt(request.getParameter("anum"));
		PatientUserService userService = new PatientUserService();
		userService.confirmAppo(anum);
		Initialization(request, response);
	}
	
	private void fresh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		ServletContext application = getServletContext();
		String pnum = request.getParameter("pnum");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/text;charset=UTF-8");
		if (application.getAttribute("callpnum")!=null&&application.getAttribute("callstate")!=null) {
			if (application.getAttribute("callpnum").equals(pnum)&&application.getAttribute("callstate").equals("1")) {
			    writer.write("1");
			}
			else {
				writer.write("0");
			}
		}
		else {
			writer.write("0");
		}
	}

}
