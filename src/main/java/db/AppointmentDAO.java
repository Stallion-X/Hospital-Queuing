package db;
import java.sql.*;
import java.util.*;
import beans.*;
/** 
 * @author Stallion-X
 */
public class AppointmentDAO extends ConnBean {
	private Connection connection=null;
	public List<AppointmentBean> getAllRecords(){
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
	
	
	public AppointmentBean getRecord(int aid) {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		AppointmentBean appo=null;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select * from appointment where anum=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setInt(1, aid);
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
	
	public List<AppointmentBean> getAllRecordsExp(){
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		ArrayList <AppointmentBean> list=new ArrayList<AppointmentBean>();
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			if(connection==null)
				System.out.println("error");
			String sql="select * from appointment except select * from appointment where astate=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setInt(1, 5);
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
	
	public boolean insertRecord(AppointmentBean record) {
		PreparedStatement pstmt=null;
		String sql="insert into appointment values(?,?,?,?,?,?,?,?,?)";
		if(record==null) 
			return false;
		boolean result=false;
		int number=0;
		try{
			connection=getConnection();	
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, record.getAnum());
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

	public boolean deleteRecord(int anum) {
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
	
	public boolean updateRecord(AppointmentBean beforeRecord,AppointmentBean afterRecord) {
		if(beforeRecord==null || afterRecord==null) 
			return false;
		PreparedStatement pstmt=null;
		String sql="update appointment set pnum=?,ddepartment=?,dnum=?,yytime=?,jztime=?,dcon=?,astate=?,atype=? where anum=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, afterRecord.getPnum());
			pstmt.setString(2, afterRecord.getDdepartment());
			pstmt.setString(3, afterRecord.getDnum());
			pstmt.setString(4, afterRecord.getYytime());
			pstmt.setString(5, afterRecord.getJztime());
			pstmt.setString(6, afterRecord.getDcon());
			pstmt.setInt(7, afterRecord.getAstate());
			pstmt.setInt(8, afterRecord.getAtype());
			pstmt.setInt(9, beforeRecord.getAnum());
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
	
	public int getMaxTmnum() {
		ResultSet rs=null;
		Statement stmt=null;
		int maxid = 0;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="SELECT MAX(tmnum) FROM takemedicine";
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
	
	public int getMaxMtenum() {
		ResultSet rs=null;
		Statement stmt=null;
		int maxid = 0;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="SELECT MAX(mtenum) FROM medicaltecexam";
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
	
	public boolean priorRecord(AppointmentBean record) {
		if(record==null) 
			return false;
		PreparedStatement pstmt=null;
		String sql="update appointment set anum=? where anum=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, record.getAnum());
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
	
	public boolean backwardRecord(AppointmentBean record,int value) {
		if(record==null) 
			return false;
		PreparedStatement pstmt=null;
		String sql="update appointment set anum=? where anum=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, record.getAnum()+value);
			pstmt.setInt(2, record.getAnum());
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
	
	public boolean forwardRecord(AppointmentBean record,int value) {
		if(record==null) 
			return false;
		PreparedStatement pstmt=null;
		String sql="update appointment set anum=? where anum=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, record.getAnum()-value);
			pstmt.setInt(2, record.getAnum());
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
	
	public boolean changeState(int anum,int astate) {
		PreparedStatement pstmt=null;
		String sql="update appointment set astate=? where anum=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, astate);
			pstmt.setInt(2, anum);
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
	
	public List<AppointmentBean> getRecordsByAtype(int atype){
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		ArrayList <AppointmentBean> list=new ArrayList<AppointmentBean>();
		try {			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select * from appointment where atype=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setInt(1, atype);
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
	
	public List<AppointmentBean> getRecordsByAstate(int astate){
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		ArrayList <AppointmentBean> list=new ArrayList<AppointmentBean>();
		try {			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select * from appointment where astate=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setInt(1, astate);
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
	
	public String getMtnameByMtnum(String mtnum) {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		String mtname = null;
		try {			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select mtname from medicaltec where mtnum=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setString(1, mtnum);
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()){
				mtname = rs.getString(1);
			}
			else {	
				System.out.println("mtname null");
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
		return mtname;
	}
	
	public boolean changeQueue(String dnumold,String dnumnew) {
		PreparedStatement pstmt=null;
		String sql="update appointment set dnum=? where dnum=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, dnumnew);
			pstmt.setString(2, dnumold);
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
	
	public List<MedicalTechBean> getAllMRecords() {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		ArrayList <MedicalTechBean> list=new ArrayList<MedicalTechBean>();
		try {			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select * from medicaltecexam";
			pstmt =connection.prepareStatement(sql);
			pstmt.setFetchSize(500);
			rs=pstmt.executeQuery();
			rs.setFetchSize(500);
			while(rs.next()){
				MedicalTechBean mtb=new MedicalTechBean();
				mtb.setMtenum(rs.getInt(1));
				mtb.setPnum(rs.getString(2));
				mtb.setMtnum(rs.getString(3));
				mtb.setMtetime(rs.getString(4));
				mtb.setMtstate(rs.getInt(5));
				mtb.setMtname(getMtnameByMtnum(rs.getString(3)));
				mtb.setPname(getPnameByPnum(rs.getString(2)));
				list.add(mtb);
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
	
	public boolean insertDrug(DoctorDrugBean record,String pmdad) {
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		String sql="insert into takemedicine values(?,?,?,?,?)";
		String sql2="update patient set pmdad=? where pnum=?";
		if(record==null) 
			return false;
		boolean result=false;
		int number=0,number2=0;
		try{
			connection=getConnection();	
			pstmt=connection.prepareStatement(sql);
			pstmt2=connection.prepareStatement(sql2);
			pstmt.setInt(1, record.getTmnum());
			pstmt.setString(2, record.getPnum());
			pstmt.setString(3, record.getDrnum());
			pstmt.setInt(4, record.getDrquantity());
			pstmt.setInt(5, record.getTmstate());
			pstmt2.setString(1, pmdad);
			pstmt2.setString(2, record.getPnum());
			number=pstmt.executeUpdate();
			number2=pstmt2.executeUpdate();
			pstmt.close();
			pstmt2.close();
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
		if(number>0&&number2>0)
			result=true;
		return result;
	}
	
	public boolean insertMExam(MedicalTechBean record) {
		PreparedStatement pstmt=null;
		String sql="insert into medicaltecexam values(?,?,?,?,?)";
		if(record==null) 
			return false;
		boolean result=false;
		int number=0;
		try{
			connection=getConnection();	
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, record.getMtenum());
			pstmt.setString(2, record.getPnum());
			pstmt.setString(3, record.getMtnum());
			pstmt.setString(4, record.getMtetime());
			pstmt.setInt(5, record.getMtstate());
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
	
	public List<Integer> getStats(){
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		PreparedStatement pstmt4=null;
		PreparedStatement pstmt5=null;
		PreparedStatement pstmt6=null;
		PreparedStatement pstmt7=null;
		ArrayList <Integer> list=new ArrayList<Integer>();
		try {			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select COUNT(anum) from appointment";
			String sql1="select COUNT(anum) from appointment where atype=?";
			String sql2="select COUNT(anum) from appointment where astate=?";
			
			pstmt1 =connection.prepareStatement(sql);
			pstmt2 =connection.prepareStatement(sql1);
			pstmt3 =connection.prepareStatement(sql1);
			pstmt4 =connection.prepareStatement(sql1);
			pstmt5 =connection.prepareStatement(sql1);
			pstmt6 =connection.prepareStatement(sql2);
			pstmt7 =connection.prepareStatement(sql2);
			
			pstmt2.setInt(1, 1);
			pstmt3.setInt(1, 2);
			pstmt4.setInt(1, 3);
			pstmt5.setInt(1, 4);
			pstmt6.setInt(1, 3);
			pstmt7.setInt(1, 4);
			
			pstmt1.setFetchSize(500);
			pstmt2.setFetchSize(500);
			pstmt3.setFetchSize(500);
			pstmt4.setFetchSize(500);
			pstmt5.setFetchSize(500);
			pstmt6.setFetchSize(500);
			pstmt7.setFetchSize(500);
			
			rs1=pstmt1.executeQuery();
			rs1.setFetchSize(500);
			rs2=pstmt2.executeQuery();
			rs2.setFetchSize(500);
			rs3=pstmt3.executeQuery();
			rs3.setFetchSize(500);
			rs4=pstmt4.executeQuery();
			rs4.setFetchSize(500);
			rs5=pstmt5.executeQuery();
			rs5.setFetchSize(500);
			rs6=pstmt6.executeQuery();
			rs6.setFetchSize(500);
			rs7=pstmt7.executeQuery();
			rs7.setFetchSize(500);
			
			while(rs1.next()){
				list.add(rs1.getInt(1));
			}
			pstmt1.close();
			while(rs2.next()){
				list.add(rs2.getInt(1));
			}
			pstmt2.close();
			while(rs3.next()){
				list.add(rs3.getInt(1));
			}
			pstmt3.close();
			while(rs4.next()){
				list.add(rs4.getInt(1));
			}
			pstmt4.close();
			while(rs5.next()){
				list.add(rs5.getInt(1));
			}
			pstmt5.close();
			while(rs6.next()){
				list.add(rs6.getInt(1));
			}
			pstmt6.close();
			while(rs7.next()){
				list.add(rs7.getInt(1));
			}
			pstmt7.close();
			
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
	
	public PatientBean getPInfoByPnum(String pnum) {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		PatientBean pb=null;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select * from patient where pnum=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setString(1, pnum);
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()){
				pb=new PatientBean();
				pb.setPnum(pnum);
				pb.setPname(rs.getString(2));
				pb.setPsex(rs.getString(3));
				pb.setPage(rs.getInt(4));
				pb.setPid(rs.getString(5));
				pb.setPtel(rs.getString(6));
				pb.setPinfor(rs.getString(8));
			}
			else
			{	
				System.out.println("patient record null");
				pb=null;
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
		return pb;
  }
	
	public DoctorBean getDInfoByDnum(String dnum) {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		DoctorBean db=null;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select * from doctor where dnum=?";
			pstmt =connection.prepareStatement(sql);
			pstmt.setString(1, dnum);
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()){
				db=new DoctorBean();
				db.setDnum(dnum);
				db.setDname(rs.getString(2));
				db.setDsex(rs.getString(3));
				db.setDage(rs.getInt(4));
				db.setDtel(rs.getString(5));
				db.setDtitle(rs.getString(6));
				db.setDpost(rs.getString(7));
				db.setDedu(rs.getString(8));
				db.setDdepartment(rs.getString(9));
			}
			else
			{	
				System.out.println("doctor record null");
				db=null;
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
		return db;
  }
}
