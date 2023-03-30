package service;

import beans.PatientBean;
import db.LogRegDAO;

/** 
 * @author Stallion-X
 */
public class LogRegService {
	public boolean validateP(String pnum,String ppsd) {
		LogRegDAO dao =new LogRegDAO();
		return dao.validateP(pnum,ppsd);
	}
	public boolean registerP(PatientBean p) {
		LogRegDAO dao =new LogRegDAO();
		return dao.insertRegister(p);
	}
	public boolean validateD(String dcon) {
		LogRegDAO dao =new LogRegDAO();
		return dao.validateD(dcon);
	}
}
