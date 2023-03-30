package service;

import java.util.*;
import beans.*;
import db.*;
/** 
 * @author Stallion-X
 */
public class AppointmentService {
	
	public List<AppointmentBean> browse() {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.getAllRecords();
	}
	
	public List<AppointmentBean> browseByAtype(int atype) {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.getRecordsByAtype(atype);
	}
	
	public List<AppointmentBean> browseExp() {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.getAllRecordsExp();
	}
	
	public List<AppointmentBean> browseByAstate(int astate) {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.getRecordsByAstate(astate);
	}
	
	public boolean addAppointment (AppointmentBean appo) {
		AppointmentDAO appoDao=new AppointmentDAO();
		return appoDao.insertRecord(appo);
	}
	
	public AppointmentBean getRecord(int anum) {
		AppointmentDAO appoDao=new AppointmentDAO();
		return appoDao.getRecord(anum);
	}
	
	public boolean editAppointment(AppointmentBean beforeRecord,AppointmentBean afterRecord) {
		AppointmentDAO appoDao=new AppointmentDAO();
		return appoDao.updateRecord(beforeRecord,afterRecord);
	}
	
	public void priorAppointment(int anum) {
		AppointmentDAO appoDao=new AppointmentDAO();
		backwardAppointment(1);
		int maxid = appoDao.getMaxAnum();
		appoDao.priorRecord(appoDao.getRecord(anum+1));
		for (int i=anum+2;i<=maxid;i++) {
			appoDao.forwardRecord(appoDao.getRecord(i),1);
		}
	}
	
	public void backwardAppointment(int anum) {
		AppointmentDAO appoDao=new AppointmentDAO();
		int maxid = appoDao.getMaxAnum();
		for (int i=maxid;i>=anum;i--) {
			appoDao.backwardRecord(appoDao.getRecord(i),1);
		}
	}
	
	public void pushAppointment(int anum) {
		AppointmentDAO appoDao=new AppointmentDAO();
		backwardAppointment(anum+1);
		int maxid = appoDao.getMaxAnum();
		appoDao.backwardRecord(appoDao.getRecord(anum),1);
		appoDao.forwardRecord(appoDao.getRecord(anum+2),2);
		for (int i=anum+3;i<=maxid;i++) {
			appoDao.forwardRecord(appoDao.getRecord(i),1);
		}
	}
	
	public void tailAppointment() {
		AppointmentDAO appoDao=new AppointmentDAO();
		int maxid = appoDao.getMaxAnum();
		appoDao.backwardRecord(appoDao.getRecord(1),maxid);
		for (int i=2;i<=maxid+1;i++) {
			appoDao.forwardRecord(appoDao.getRecord(i),1);
		}
	}
	
	public boolean delAppointment(int anum) {
		AppointmentDAO appoDao=new AppointmentDAO();
		return appoDao.deleteRecord(anum);
	}
	
	public boolean changeStateToDoing(int anum) {
		AppointmentDAO appoDao=new AppointmentDAO();
		return appoDao.changeState(anum, 2);
	}
	
	public boolean changeStateToComplete(int anum) {
		AppointmentDAO appoDao=new AppointmentDAO();
		return appoDao.changeState(anum, 3);
	}
	
	public boolean changeStateToOver(int anum) {
		AppointmentDAO appoDao=new AppointmentDAO();
		return appoDao.changeState(anum, 5);
	}
	
	public String getPname(String pnum) {
		AppointmentDAO appoDao=new AppointmentDAO();
		return appoDao.getPnameByPnum(pnum);
	}

	/*
	 * public String getDname(String dnum) { AppointmentDAO appoDao=new
	 * AppointmentDAO(); return appoDao.getDnameByDnum(dnum); }
	 * 
	 * public String getDtitle(String dnum) { AppointmentDAO appoDao=new
	 * AppointmentDAO(); return appoDao.getDtitleByDnum(dnum); }
	 */
	
	public String getMtname(String mtnum) {
		AppointmentDAO appoDao=new AppointmentDAO();
		return appoDao.getMtnameByMtnum(mtnum);
	}
	
	public boolean transferQueueToOthers(String dnumold,String dnumnew) {
		AppointmentDAO appoDao=new AppointmentDAO();
		return appoDao.changeQueue(dnumold,dnumnew);
	}
	
	public List<MedicalTechBean> browseM() {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.getAllMRecords();
	}
	
	public boolean newDrug(DoctorDrugBean ddrug,String pmdad) {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.insertDrug(ddrug,pmdad);
	}
	
	public boolean newMExam(MedicalTechBean mtb) {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.insertMExam(mtb);
	}
	
	public int getMaxTmnum() {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.getMaxTmnum();
	}
	
	public int getMaxMtenum() {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.getMaxMtenum();
	}
	public List<Integer> statics() {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.getStats();
	}
	
	public PatientBean getPInfo(String pnum) {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.getPInfoByPnum(pnum);
	}
	
	public DoctorBean getDInfo(String dnum) {
		AppointmentDAO dao =new AppointmentDAO();
		return dao.getDInfoByDnum(dnum);
	}
	
}
