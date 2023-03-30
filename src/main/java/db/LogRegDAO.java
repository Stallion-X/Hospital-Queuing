package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.DoctorDrugBean;
import beans.PatientBean;

/** 
 * @author Stallion-X
 */
public class LogRegDAO extends ConnBean {
	private Connection connection=null;
	public boolean validateP(String pnum,String ppsd) {
		PreparedStatement pstmt=null;
		String sql="select * from patient where pnum=? and ppsd=?";
		ResultSet rs=null;
		int result = 0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, pnum);
			pstmt.setString(2, ppsd);
			rs=pstmt.executeQuery();
			rs.last();
			result = rs.getRow();
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
	
	public boolean insertRegister(PatientBean p) {
		PreparedStatement pstmt=null;
		String sql="insert into patient values(?,?,?,?,?,?,?,?,?)";
		if(p==null) 
			return false;
		boolean result=false;
		int number=0;
		try{
			connection=getConnection();	
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, p.getPnum());
			pstmt.setString(2, p.getPname());
			pstmt.setString(3, p.getPsex());
			pstmt.setInt(4, p.getPage());
			pstmt.setString(5, p.getPid());
			pstmt.setString(6, p.getPtel());
			pstmt.setString(7, p.getPpsd());
			pstmt.setString(8, "患者");
			pstmt.setString(9, null);
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
	
	public boolean validateD(String dcon) {
		PreparedStatement pstmt=null;
		String sql="select dnum from doctor where dcon=?";
		ResultSet rs=null;
		int result = 0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, dcon);
			rs=pstmt.executeQuery();
			rs.last();
			result = rs.getRow();
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
