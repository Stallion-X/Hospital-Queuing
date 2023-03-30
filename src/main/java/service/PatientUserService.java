package service;

import java.util.List;

import beans.AppointmentBean;
import beans.DoctorBean;
import beans.MedicalTechBean;
import beans.PatientBean;
import beans.TakeMedicineBean;
import db.*;

public class PatientUserService {

	public List<PatientBean> browse() {
		PatientUserDao dao = new PatientUserDao();
        return dao.getAllPatients();
	}
	
	public List<AppointmentBean> browseAppo() {
		PatientUserDao dao = new PatientUserDao();
        return dao.getAppoRecords();
	}
	
	public List<AppointmentBean> browseAppoByPnum(String pnum) {
		PatientUserDao dao = new PatientUserDao();
        return dao.getAppoRecordsByPnum(pnum);
	}
	
	public AppointmentBean latestAppo(String pnum) {
		PatientUserDao dao = new PatientUserDao();
        return dao.getLatestAppoRecord(pnum);
	}
	
	public MedicalTechBean latestMTB(String pnum) {
		PatientUserDao dao = new PatientUserDao();
        return dao.getLatestMTRecord(pnum);
	}
	
	public List<MedicalTechBean> browseMTB() {
		PatientUserDao dao = new PatientUserDao();
        return dao.getMedicalTechRecords();
	}
	
	public TakeMedicineBean latestTMB(String pnum) {
		PatientUserDao dao = new PatientUserDao();
        return dao.getLatestTMRecord(pnum);
	}
	
	public List<TakeMedicineBean> browseMB() {
		PatientUserDao dao = new PatientUserDao();
        return dao.getTakeMedicineRecords();
	}
	
	public List<DoctorBean> browseDoctors() {
		PatientUserDao dao = new PatientUserDao();
        return dao.getAllDoctors();
	}
	
	public List<DoctorBean> browseDoctorsByDept(String ddepartment) {
		PatientUserDao dao = new PatientUserDao();
        return dao.getDoctorsByDepartment(ddepartment);
	}
	
	public boolean confirmAppo(int anum) {
		PatientUserDao dao = new PatientUserDao();
        return dao.deleteAppo(anum);
	}
	
	public boolean confirmMExam(int mtenum) {
		PatientUserDao dao = new PatientUserDao();
        return dao.deleteMExam(mtenum);
	}
	
	public boolean confirmDrug(int tmnum) {
		PatientUserDao dao = new PatientUserDao();
        return dao.deleteDrug(tmnum);
	}
	
	public boolean insertAppo(AppointmentBean record) {
		PatientUserDao dao = new PatientUserDao();
        return dao.insertAppo(record);
	}
	
	public String getPwd(String pnum) {
		PatientUserDao dao = new PatientUserDao();
        return dao.getPPwd(pnum);
	}
	
	public boolean editPwd(String pnum,String newpwd) {
		PatientUserDao dao = new PatientUserDao();
        return dao.editPPwd(pnum,newpwd);
	}

}
