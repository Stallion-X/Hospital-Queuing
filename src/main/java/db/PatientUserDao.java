package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.*;
/** 
* @author Stallion-X
*/
public class PatientUserDao extends ConnBean{
	private Connection connection=null;
	
	public List<PatientBean> getAllPatients() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<PatientBean> list = new ArrayList<PatientBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from Patient";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PatientBean user = new PatientBean();
				user.setPnum(rs.getString(1));
				user.setPname(rs.getString(2));
				user.setPsex(rs.getString(3));
				user.setPage(rs.getInt(4));
				user.setPid(rs.getString(5));
				user.setPtel(rs.getString(6));
				user.setPpsd(rs.getString(7));
				user.setPinfor(rs.getString(8));
				user.setPmdad(rs.getString(9));
				list.add(user);
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public String getPnameByPnum(String pnum) {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		String pname = null;
		try {			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select pname from patient where pnum=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setString(1,pnum);
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()){
				pname = rs.getString(1);
			}
			else
			{	
				System.out.println("pname null");
			}
			pstmt.close();					
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return pname;
	}
	
	public List<String> getDnameDtitleByDnum(String dnum) {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		String dname = null;
		String dtitle = null;
		List<String> dlist = new ArrayList<>();
		try {			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select dname,dtitle from doctor where dnum=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setString(1, dnum);
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()){
				dname = rs.getString(1);
				dtitle = rs.getString(2);
				dlist.add(dname);
				dlist.add(dtitle);
			}
			else
			{	
				System.out.println("dname null");
			}
			pstmt.close();					
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return dlist;
	}
	
	public List<AppointmentBean> getAppoRecords(){
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		ArrayList <AppointmentBean> list=new ArrayList<AppointmentBean>();
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			if(connection==null)
				System.out.println("error");
			String sql="select * from appointment";
			pstmt =connection.prepareStatement(sql);
			pstmt.setFetchSize(500);
			rs=pstmt.executeQuery();
			rs.setFetchSize(500);
			while(rs.next()){
				AppointmentBean appo=new AppointmentBean();
				appo.setAnum(rs.getInt(1));
				appo.setPnum(rs.getString(2));
				appo.setDdepartment(rs.getString(3));
				appo.setDnum(rs.getString(4));
				appo.setYytime(rs.getString(5));
				appo.setJztime(rs.getString(6));
				appo.setDcon(rs.getString(7));
				appo.setAstate(rs.getInt(8));
				appo.setAtype(rs.getInt(9));
				appo.setPname(getPnameByPnum(rs.getString(2)));
				List<String> dtemp = getDnameDtitleByDnum(rs.getString(4));
				appo.setDname(dtemp.get(0));
				appo.setDtitle(dtemp.get(1));
				list.add(appo);
			}
			pstmt.close();	
			connection.close();
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return list;
	}
	
	public List<AppointmentBean> getAppoRecordsByPnum(String pnum){
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		ArrayList <AppointmentBean> list=new ArrayList<AppointmentBean>();
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			if(connection==null)
				System.out.println("error");
			String sql="select * from appointment where pnum=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setString(1, pnum);
			pstmt.setFetchSize(500);
			rs=pstmt.executeQuery();
			rs.setFetchSize(500);
			while(rs.next()){
				AppointmentBean appo=new AppointmentBean();
				appo.setAnum(rs.getInt(1));
				appo.setPnum(rs.getString(2));
				appo.setDdepartment(rs.getString(3));
				appo.setDnum(rs.getString(4));
				appo.setYytime(rs.getString(5));
				appo.setJztime(rs.getString(6));
				appo.setDcon(rs.getString(7));
				appo.setAstate(rs.getInt(8));
				appo.setAtype(rs.getInt(9));
				appo.setPname(getPnameByPnum(rs.getString(2)));
				List<String> dtemp = getDnameDtitleByDnum(rs.getString(4));
				appo.setDname(dtemp.get(0));
				appo.setDtitle(dtemp.get(1));
				list.add(appo);
			}
			pstmt.close();	
			connection.close();
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return list;
	}
	
	public AppointmentBean getLatestAppoRecord(String pnum) {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		AppointmentBean appo=null;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select * from appointment where pnum=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setString(1, pnum);
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()){
				appo=new AppointmentBean();
				appo.setAnum(rs.getInt(1));
				appo.setPnum(rs.getString(2));
				appo.setDdepartment(rs.getString(3));
				appo.setDnum(rs.getString(4));
				appo.setYytime(rs.getString(5));
				appo.setJztime(rs.getString(6));
				appo.setDcon(rs.getString(7));
				appo.setAstate(rs.getInt(8));
				appo.setAtype(rs.getInt(9));
				appo.setPname(getPnameByPnum(rs.getString(2)));
				List<String> dtemp = getDnameDtitleByDnum(rs.getString(4));
				appo.setDname(dtemp.get(0));
				appo.setDtitle(dtemp.get(1));
			}
			else
			{	
				System.out.println("appointment record null");
				appo=null;
			}
			pstmt.close();					
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return appo;
  }
	
	public MedicalTechBean getLatestMTRecord(String pnum) {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		MedicalTechBean mtb = new MedicalTechBean();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from medicaltecexam where pnum=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, pnum);
			rs = pstmt.executeQuery();
			if (rs!=null&&rs.next()) {
				mtb.setMtenum(rs.getInt(1));
				mtb.setPnum(rs.getString(2));
				mtb.setMtnum(rs.getString(3));
				mtb.setMtetime(rs.getString(4));
				mtb.setMtstate(rs.getInt(5));
			}
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mtb;
	}
	
	public List<MedicalTechBean> getMedicalTechRecords() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<MedicalTechBean> list = new ArrayList<MedicalTechBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from medicaltecexam";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MedicalTechBean mtb = new MedicalTechBean();
				mtb.setMtenum(rs.getInt(1));
				mtb.setPnum(rs.getString(2));
				mtb.setMtnum(rs.getString(3));
				mtb.setMtetime(rs.getString(4));
				mtb.setMtstate(rs.getInt(5));
				list.add(mtb);
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public TakeMedicineBean getLatestTMRecord(String pnum) {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		TakeMedicineBean tmb = new TakeMedicineBean();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from takemedicine where pnum=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, pnum);
			rs = pstmt.executeQuery();
			if (rs!=null&&rs.next()) {
				tmb.setTmnum(rs.getInt(1));
				tmb.setPnum(rs.getString(2));
				tmb.setDrnum(rs.getString(3));
				tmb.setDrquantity(rs.getInt(4));
				tmb.setTmstate(rs.getInt(5));
			}
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tmb;
	}
	
	public List<TakeMedicineBean> getTakeMedicineRecords() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<TakeMedicineBean> list = new ArrayList<TakeMedicineBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from takemedicine";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TakeMedicineBean tmb = new TakeMedicineBean();
				tmb.setTmnum(rs.getInt(1));
				tmb.setPnum(rs.getString(2));
				tmb.setDrnum(rs.getString(3));
				tmb.setDrquantity(rs.getInt(4));
				tmb.setTmstate(rs.getInt(5));
				list.add(tmb);
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<DoctorBean> getAllDoctors() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<DoctorBean> list = new ArrayList<DoctorBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from Doctor";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DoctorBean user = new DoctorBean();
				user.setDnum(rs.getString(1));
				user.setDname(rs.getString(2));
				user.setDsex(rs.getString(3));
				user.setDage(rs.getInt(4));
				user.setDtel(rs.getString(5));
				user.setDtitle(rs.getString(6));
				user.setDpost(rs.getString(7));
				user.setDedu(rs.getString(8));
				user.setDdepartment(rs.getString(9));
				user.setDcon(rs.getString(10));
				list.add(user);
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<DoctorBean> getDoctorsByDepartment(String ddepartment) {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<DoctorBean> list = new ArrayList<DoctorBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from Doctor where ddepartment='"+ddepartment+"'";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DoctorBean user = new DoctorBean();
				user.setDnum(rs.getString(1));
				user.setDname(rs.getString(2));
				user.setDsex(rs.getString(3));
				user.setDage(rs.getInt(4));
				user.setDtel(rs.getString(5));
				user.setDtitle(rs.getString(6));
				user.setDpost(rs.getString(7));
				user.setDedu(rs.getString(8));
				user.setDdepartment(rs.getString(9));
				user.setDcon(rs.getString(10));
				list.add(user);
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && (!connection.isClosed())) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int getMaxAnum() {
		ResultSet rs=null;
		Statement stmt=null;
		int maxid = 0;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="SELECT MAX(anum) FROM appointment";
			stmt =connection.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				maxid = rs.getInt(1);
			}
			stmt.close();					
			connection.close();			
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return maxid;
	}
	
	public boolean insertAppo(AppointmentBean record) {
		PreparedStatement pstmt=null;
		String sql="insert into appointment values(?,?,?,?,?,?,?,?,?)";
		if(record==null) 
			return false;
		boolean result=false;
		int number=0;
		try{
			connection=getConnection();	
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, getMaxAnum()+1);
			pstmt.setString(2, record.getPnum());
			pstmt.setString(3, record.getDdepartment());
			pstmt.setString(4, record.getDnum());
			pstmt.setString(5, record.getYytime());
			pstmt.setString(6, record.getJztime());
			pstmt.setString(7, record.getDcon());
			pstmt.setInt(8, record.getAstate());
			pstmt.setInt(9, record.getAtype());
			number=pstmt.executeUpdate();
			pstmt.close();
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(number>0)
			result=true;
		return result;
	}
	
	public boolean deleteAppo(int anum) {
		PreparedStatement pstmt=null;
		String sql="delete from appointment where anum=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, anum);
			result=pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(result>0)
			return true;
		else
			return false;
	}
	
	public boolean deleteMExam(int mtenum) {
		PreparedStatement pstmt=null;
		String sql="delete from medicaltecexam where mtenum=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, mtenum);
			result=pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(result>0)
			return true;
		else
			return false;
	}
	
	public boolean deleteDrug(int tmnum) {
		PreparedStatement pstmt=null;
		String sql="delete from takemedicine where tmnum=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, tmnum);
			result=pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(result>0)
			return true;
		else
			return false;
	}
	
	public String getPPwd(String pnum) {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		String ppsd = null;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="SELECT ppsd FROM patient where pnum=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setString(1, pnum);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ppsd = rs.getString(1);
			}
			pstmt.close();					
			connection.close();			
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return ppsd;
	}
	
	public boolean editPPwd(String pnum,String newpwd) {
		PreparedStatement pstmt=null;
		String sql="update patient set ppsd=? where pnum=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, newpwd);
			pstmt.setString(2, pnum);
			result=pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(result>0)
			return true;
		else
			return false;
	}
}
