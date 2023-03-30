package service;

import beans.BackendManage_UserBean;
import db.BackendManage_UserDAO;

import java.util.List;

public class BackendManage_UserService {

	public List<BackendManage_UserBean> browse1() {
		BackendManage_UserDAO dao = new BackendManage_UserDAO();
		return dao.getAllRecords1();
	}
	public List<BackendManage_UserBean> browse2() {
		BackendManage_UserDAO dao = new BackendManage_UserDAO();
		return dao.getAllRecords2();
	}
	public List<BackendManage_UserBean> browse3() {
		BackendManage_UserDAO dao = new BackendManage_UserDAO();
		return dao.getAllRecords3();
	}
	public List<BackendManage_UserBean> browse4() {
		BackendManage_UserDAO dao = new BackendManage_UserDAO();
		return dao.getAllRecords4();
	}
	public List<BackendManage_UserBean> browse5() {
		BackendManage_UserDAO dao = new BackendManage_UserDAO();
		return dao.getAllRecords5();
	}
	public List<BackendManage_UserBean> browse6() {
		BackendManage_UserDAO dao = new BackendManage_UserDAO();
		return dao.getAllRecords6();
	}
	public List<BackendManage_UserBean> browse7() {
		BackendManage_UserDAO dao = new BackendManage_UserDAO();
		return dao.getAllRecords7();
	}
	

	public boolean addUser(BackendManage_UserBean user, int table_no) {
		BackendManage_UserDAO PatientUserDao = new BackendManage_UserDAO();
		return PatientUserDao.insertRecord(user, table_no);
	}

//	public BackendManage_UserBean getRecord(int userId) {
//		BackendManage_UserDAO PatientUserDao = new BackendManage_UserDAO();
//		return PatientUserDao.getRecord(userId);
//	}

	public int delete(String no, String table, String num) {
		BackendManage_UserDAO PatientUserDao = new BackendManage_UserDAO();
		if (PatientUserDao.deleteRecord(no, table, num))
			return 1;
		else
			return 2;
	}
	public List<String> analyze(List<BackendManage_UserBean> list3, List<BackendManage_UserBean> list5, List<BackendManage_UserBean> list7) {
		// TODO Auto-generated method stub
		BackendManage_UserDAO dao = new BackendManage_UserDAO();
		return dao.analyze(list3, list5, list7);
	}
}
