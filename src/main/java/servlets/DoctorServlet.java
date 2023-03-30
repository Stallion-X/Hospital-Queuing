package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.alibaba.fastjson.JSON;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.*;
import service.*;

/**
 * Servlet implementation class DoctorServlet
 */
/** 
* @author Stallion-X
*/
@WebServlet("/DoctorServlet")
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if (action==null) {
			browseAppo(request, response);
		}
		if ("exp".equals(action)) {
			exp(request, response);
		}
		if ("classify".equals(action)) {
			classify(request, response);
		}
		if ("call".equals(action)) {
			call(request, response);
		}
		if ("complete".equals(action)) {
			complete(request, response);
		}
		if("addAppo".equals(action)) {
			addAppo(request, response);
		}
		if("delAppo".equals(action)) {
			delAppo(request, response);
		}
		if("editAppo".equals(action)) {
			editAppo(request, response);
		}
		if("priorAppo".equals(action)) {
			priorAppo(request, response);
		}
		if("pushAppo".equals(action)) {
			pushAppo(request, response);
		}
		if("passAppo".equals(action)) {
			passAppo(request, response);
		}
		if ("browseMExam".equals(action)) {
			browseMExam(request, response);
		}
		if("newDrug".equals(action)) {
			newDrug(request, response);
		}
		if("newMExam".equals(action)) {
			newMExam(request, response);
		}
		if("getMaxTmnum".equals(action)) {
			ajaxGetMaxTmnum(request, response);
		}
		if("getMaxMtenum".equals(action)) {
			ajaxGetMaxMtenum(request, response);
		}
		if("transferD".equals(action)) {
			transferD(request, response);
		}
		if("getPInfo".equals(action)) {
			ajaxGetPInfo(request, response);
		}
		if("getDInfo".equals(action)) {
			ajaxGetDInfo(request, response);
		}
		if("search".equals(action)) {
			search(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void browseAppo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppointmentService appoService=new AppointmentService();
		List <AppointmentBean> list = appoService.browse();
		/*卡顿万恶之源
		 * list.forEach(item->item.setPname(appoService.getPname(item.getPnum())));
		 * list.forEach(item->item.setDname(appoService.getDname(item.getDnum())));
		 * list.forEach(item->item.setDtitle(appoService.getDtitle(item.getDnum())));
		 */
		request.setAttribute("allAppo", list);
		request.setAttribute("stats", appoService.statics());
		String path="/DoctorIndex.jsp";
	    response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);
	}
	
	public void exp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppointmentService appoService=new AppointmentService();
		List <AppointmentBean> list = appoService.browseExp();
		request.setAttribute("allAppo", list);
		request.setAttribute("stats", appoService.statics());
		String path="/DoctorIndex.jsp?action=exp";
	    response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);
		
	}
	
	public void classify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid=request.getParameter("cid");
		AppointmentService appoService=new AppointmentService();
		List <AppointmentBean> list = null;
		switch (cid) {
		case "1":
			list = appoService.browseByAtype(1);
			break;
		case "2":
			list = appoService.browseByAtype(2);
			break;
		case "3":
			list = appoService.browseByAtype(3);
			break;
		case "4":
			list = appoService.browseByAtype(4);
			break;
		case "5":
			list = appoService.browseByAstate(3);
			break;
		case "6":
			list = appoService.browseByAstate(4);
			break;
		case "7":
			list = appoService.browseByAstate(5);
			break;
		}
		/*
		 * list.forEach(item->item.setPname(appoService.getPname(item.getPnum())));
		 * list.forEach(item->item.setDname(appoService.getDname(item.getDnum())));
		 * list.forEach(item->item.setDtitle(appoService.getDtitle(item.getDnum())));
		 */
		request.setAttribute("allAppo", list);
		request.setAttribute("stats", appoService.statics());
		String path="/DoctorIndex.jsp";
	    response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);
	}
	
	public void call(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("callpnum", request.getParameter("pnum"));
		//complete(request, response);
		exp(request, response);
	}
	
	public void complete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppointmentService appoService=new AppointmentService();
		appoService.changeStateToDoing(1);
		appoService.tailAppointment();
		request.removeAttribute("callpnum");
		exp(request, response);
	}
	
	public void addAppo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AppointmentService appoService=new AppointmentService();
			int anum=Integer.parseInt(request.getParameter("anum"));
			String pnum=request.getParameter("pnum");
			String ddepartment=request.getParameter("ddepartment");
			String dnum=request.getParameter("dnum");
			String yytime=request.getParameter("yytime");
			String jztime=request.getParameter("jztime");
			String dcon=request.getParameter("dcon");
			int astate=Integer.parseInt(request.getParameter("astate"));
			int atype=Integer.parseInt(request.getParameter("atype"));
			
			AppointmentBean appo = new AppointmentBean();
			appo.setAnum(anum);
			appo.setPnum(pnum);
			appo.setDdepartment(ddepartment);
			appo.setDnum(dnum);
			appo.setYytime(yytime);
			appo.setJztime(jztime);
			appo.setDcon(dcon);
			appo.setAstate(astate);
			appo.setAtype(atype);
			if(appoService.addAppointment(appo))
			{
				request.setAttribute("isSuc", true);
			}
			else
			{
				request.setAttribute("isSuc", false);
			}
			browseAppo(request, response);
		} catch (NumberFormatException e) {
			response.sendRedirect("DoctorServlet");
		}
	}
	
	public void delAppo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int anum = Integer.parseInt(request.getParameter("anum"));
			AppointmentService appoService=new AppointmentService();
			appoService.delAppointment(anum);
			browseAppo(request, response);
		} catch (NumberFormatException e) {
			response.sendRedirect("DoctorServlet");
		}
	}
	
	public void editAppo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int anum = Integer.parseInt(request.getParameter("anum"));
			int astate = Integer.parseInt(request.getParameter("astate"));
			AppointmentService appoService=new AppointmentService();
			AppointmentBean beforeRecord = new AppointmentBean();
			beforeRecord.setAnum(anum);
			AppointmentBean afterRecord = appoService.getRecord(anum);
			afterRecord.setAstate(astate);
			appoService.editAppointment(beforeRecord, afterRecord);
			browseAppo(request, response);
		} catch (NumberFormatException e) {
			response.sendRedirect("DoctorServlet");
		}
	}
	
	public void priorAppo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int anum = Integer.parseInt(request.getParameter("anum"));
			AppointmentService appoService=new AppointmentService();
			appoService.priorAppointment(anum);
			browseAppo(request, response);
		} catch (NumberFormatException e) {
			response.sendRedirect("DoctorServlet");
		}
	}
	
	public void pushAppo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int anum = Integer.parseInt(request.getParameter("anum"));
			AppointmentService appoService=new AppointmentService();
			appoService.pushAppointment(anum);
			browseAppo(request, response);
		} catch (NumberFormatException e) {
			response.sendRedirect("DoctorServlet");
		}
	}
	
	public void passAppo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int anum = Integer.parseInt(request.getParameter("anum"));
		AppointmentService appoService=new AppointmentService();
		appoService.changeStateToOver(anum);
		exp(request, response);
	}
	
	public void browseMExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppointmentService appoService=new AppointmentService();
		List <MedicalTechBean> list = appoService.browseM();
		/*
		 * list.forEach(item->item.setPname(appoService.getPname(item.getPnum())));
		 * list.forEach(item->item.setMtname(appoService.getMtname(item.getMtnum())));
		 */
		request.setAttribute("allM", list);
		request.setAttribute("stats", appoService.statics());
		/*
		 * List <AppointmentBean> list1 = appoService.browse();
		 * list1.forEach(item->item.setPname(appoService.getPname(item.getPnum())));
		 * list1.forEach(item->item.setDname(appoService.getDname(item.getDnum())));
		 * list1.forEach(item->item.setDtitle(appoService.getDtitle(item.getDnum())));
		 * request.setAttribute("allAppo", list1);
		 */
		String path="/DoctorIndex.jsp?tableflag=1";
	    response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);
	}

	public void newDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AppointmentService appoService=new AppointmentService();
			int tmnum=Integer.parseInt(request.getParameter("tmnum"));
			String pnum=request.getParameter("pnum");
			String drnum=request.getParameter("drnum");
			int drquantity=Integer.parseInt(request.getParameter("drquantity"));
			int tmstate=Integer.parseInt(request.getParameter("tmstate"));
			String pmdad = request.getParameter("pmdad");
			
			DoctorDrugBean ddb = new DoctorDrugBean();
			ddb.setTmnum(tmnum);
			ddb.setPnum(pnum);
			ddb.setDrnum(drnum);
			ddb.setDrquantity(drquantity);
			ddb.setTmstate(tmstate);
			if(appoService.newDrug(ddb,pmdad)) {
				request.setAttribute("isSuc", true);
			}
			else {
				request.setAttribute("isSuc", false);
			}
			browseAppo(request, response);
		} catch (NumberFormatException e) {
			response.sendRedirect("DoctorServlet");
		}
	}

	public void newMExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AppointmentService appoService=new AppointmentService();
			int mtenum=Integer.parseInt(request.getParameter("mtenum"));
			String pnum=request.getParameter("pnum");
			String mtnum=request.getParameter("mtnum");
			String mtetime=request.getParameter("mtetime");
			int mtstate=Integer.parseInt(request.getParameter("mtstate"));
			
			MedicalTechBean mtb = new MedicalTechBean();
			mtb.setMtenum(mtenum);
			mtb.setPnum(pnum);
			mtb.setMtnum(mtnum);
			mtb.setMtetime(mtetime);
			mtb.setMtstate(mtstate);
			if(appoService.newMExam(mtb))
			{
				request.setAttribute("isSuc", true);
			}
			else
			{
				request.setAttribute("isSuc", false);
			}
			browseAppo(request, response);
		} catch (NumberFormatException e) {
			response.sendRedirect("DoctorServlet");
		}
	}
	
	public void ajaxGetMaxTmnum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppointmentService appoService=new AppointmentService();
		response.setContentType("text/text;charset=UTF-8");
	    PrintWriter writer = response.getWriter();
	    writer.write(appoService.getMaxTmnum()+"");
	}
	
	public void ajaxGetMaxMtenum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppointmentService appoService=new AppointmentService();
		response.setContentType("text/text;charset=UTF-8");
	    PrintWriter writer = response.getWriter();
	    writer.write(appoService.getMaxMtenum()+"");
	}
	
	public void transferD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppointmentService appoService=new AppointmentService();
		String dnumold = request.getParameter("dnumold");
		String dnumnew = request.getParameter("dnumnew");
		appoService.transferQueueToOthers(dnumold, dnumnew);
		browseAppo(request, response);
	}
	
	public void ajaxGetPInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppointmentService appoService=new AppointmentService();
		String pnum = request.getParameter("pnum");
		PatientBean pb = appoService.getPInfo(pnum);
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String jsonStr = JSON.toJSONString(pb);
		writer.write(jsonStr);
	}
	
	public void ajaxGetDInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppointmentService appoService=new AppointmentService();
		String dnum = request.getParameter("dnum");
		DoctorBean db = appoService.getDInfo(dnum);
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String jsonStr = JSON.toJSONString(db);
		writer.write(jsonStr);
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
