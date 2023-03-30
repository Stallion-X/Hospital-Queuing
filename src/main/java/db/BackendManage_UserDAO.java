package db;

import beans.BackendManage_UserBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** 
* @author PC
*/
public class BackendManage_UserDAO extends BackendManage_ConnBean {
	private Connection connection = null;

	public List<BackendManage_UserBean> getAllRecords1() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<BackendManage_UserBean> list = new ArrayList<BackendManage_UserBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from Doctor";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BackendManage_UserBean user = new BackendManage_UserBean();
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
	
	public List<BackendManage_UserBean> getAllRecords2() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<BackendManage_UserBean> list = new ArrayList<BackendManage_UserBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from Patient";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BackendManage_UserBean user = new BackendManage_UserBean();
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
	public List<BackendManage_UserBean> getAllRecords3() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<BackendManage_UserBean> list = new ArrayList<BackendManage_UserBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from Appointment";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BackendManage_UserBean user = new BackendManage_UserBean();
				user.setAnum(rs.getInt(1));
				user.setPnum(rs.getString(2));
				user.setPtel(rs.getString(3));
				user.setDnum(rs.getString(4));
				user.setYytime(rs.getString(5));
				user.setJztime(rs.getString(6));
				user.setDcon(rs.getString(7));
				user.setAstate(rs.getInt(8));
				user.setAtype(rs.getInt(9));
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
	public List<BackendManage_UserBean> getAllRecords4() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<BackendManage_UserBean> list = new ArrayList<BackendManage_UserBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");	
			String sql = "select * from Drug";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BackendManage_UserBean user = new BackendManage_UserBean();
				user.setDrnum(rs.getString(1));
				user.setDrname(rs.getString(2));
				user.setDrprice(rs.getFloat(3));
				user.setDrtype(rs.getString(4));
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
	
	public List<BackendManage_UserBean> getAllRecords5() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<BackendManage_UserBean> list = new ArrayList<BackendManage_UserBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from takemedicine";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BackendManage_UserBean user = new BackendManage_UserBean();
				user.setTmnum(rs.getInt(1));
				user.setPnum(rs.getString(2));
				user.setDrnum(rs.getString(3));
				user.setDrquantity(rs.getInt(4));
				user.setTmstate(rs.getInt(5));
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
	
	public List<BackendManage_UserBean> getAllRecords6() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<BackendManage_UserBean> list = new ArrayList<BackendManage_UserBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from medicaltec";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BackendManage_UserBean user = new BackendManage_UserBean();
				user.setMtnum(rs.getString(1));
				user.setMtname(rs.getString(2));
				user.setMtprice(rs.getFloat(3));			
				user.setMtplace(rs.getString(4));
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
	public List<BackendManage_UserBean> getAllRecords7() {
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<BackendManage_UserBean> list = new ArrayList<BackendManage_UserBean>();
		try {
			connection = getConnection();
			if (connection == null)
				System.out.println("error");
			String sql = "select * from medicaltecexam";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BackendManage_UserBean user = new BackendManage_UserBean();
				user.setMtenum(rs.getInt(1));
				user.setPnum(rs.getString(2));
				
				user.setMtnum(rs.getString(3));
				user.setMtetime(rs.getString(4));
				user.setMtstate(rs.getInt(5));
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


	public boolean insertRecord(BackendManage_UserBean record, int table_no) {
		PreparedStatement pstmt = null;
		if (record == null)
			return false;
		boolean result = false;
		int number = 0;
		
		if(table_no == 1) {
			String sql = "insert into Doctor values(?,?,?,?,?,?,?,?,?,?)";
			try {				
				connection = getConnection();
				pstmt = connection.prepareStatement(sql);
		
				pstmt.setString(1, record.getDnum());
				pstmt.setString(2, record.getDname());
				pstmt.setString(3, record.getDsex());
				pstmt.setInt(4, record.getDage());
				pstmt.setString(5, record.getDtel());
				pstmt.setString(6, record.getDtitle());
				pstmt.setString(7, record.getDpost());
				pstmt.setString(8, record.getDedu());
				pstmt.setString(9, record.getDdepartment());
				pstmt.setString(10, record.getDcon());
				
				number = pstmt.executeUpdate();
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
		}else if(table_no == 2) {
			String sql = "insert into Patient values(?,?,?,?,?,?,?,?,?)";
			try {	
				connection = getConnection();
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, record.getPnum());
				pstmt.setString(2, record.getPname());
				pstmt.setString(3, record.getPsex());
				pstmt.setInt(4, record.getPage());
				pstmt.setString(5, record.getPid());
				pstmt.setString(6, record.getPtel());
				pstmt.setString(7, record.getPpsd());
				pstmt.setString(8, record.getPinfor());
				pstmt.setString(9, record.getPmdad());
				
				number = pstmt.executeUpdate();
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

		}else if(table_no == 3) {
			String sql = "insert into Appointment values(?,?,?,?,?,?,?,?)";
			try {
				connection = getConnection();
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1, record.getAnum());
				pstmt.setString(2, record.getPnum());
				pstmt.setString(3, record.getPtel());
				pstmt.setString(4, record.getDnum());
				pstmt.setString(5, record.getYytime());
				pstmt.setString(6, record.getJztime());
				pstmt.setString(7, record.getDcon());
				pstmt.setInt(8, record.getAstate());
				pstmt.setInt(9, record.getAtype());
				
				number = pstmt.executeUpdate();
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
		}else if(table_no == 4) {
			String sql = "insert into Drug values(?,?,?,?)";
			try {	
				connection = getConnection();
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, record.getDrnum());
				pstmt.setString(2, record.getDrname());
				pstmt.setFloat(3, record.getDrprice());
				pstmt.setString(4, record.getDrtype());

				number = pstmt.executeUpdate();
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
	
		}else if(table_no == 5) {
			String sql = "insert into takemedicine values(?,?,?,?,?,?)";
			try {		
				connection = getConnection();
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1, record.getTmnum());
				pstmt.setString(2, record.getPnum());
				pstmt.setString(3, record.getDrnum());
				pstmt.setInt(4, record.getDrquantity());
				pstmt.setInt(5, record.getTmstate());
				pstmt.setString(6, record.getPtel());
				
				number = pstmt.executeUpdate();
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

		}else if(table_no == 6) {
			String sql = "insert into medicaltec values(?,?,?,?)";
			try {	
				connection = getConnection();
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, record.getMtnum());
				pstmt.setString(2, record.getMtname());
				pstmt.setFloat(3, record.getMtprice());
				pstmt.setString(4, record.getMtplace());
	
				number = pstmt.executeUpdate();
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

		}else if(table_no == 7) {
			String sql = "insert into medicaltecexam values(?,?,?,?,?,?)";
			try {
				connection = getConnection();
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1, record.getMtenum());
				pstmt.setString(2, record.getPnum());
				pstmt.setString(3, record.getMtnum());
				pstmt.setString(4, record.getMtetime());
				pstmt.setInt(5, record.getMtstate());
				
				number = pstmt.executeUpdate();
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
		}
		if (number > 0)
			result = true;
		return result;
	}

	public boolean deleteRecord(String no, String table, String num) {
		PreparedStatement pstmt = null;
		String sql = "delete from " +  table + " where " + num + " = " + no;
		System.out.println(sql);
		
		int result = 0;
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			result = pstmt.executeUpdate();
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
		if (result > 0)
			return true;
		else
			return false;
	}

	public List<String> analyze(List<BackendManage_UserBean> list3, List<BackendManage_UserBean> list5, List<BackendManage_UserBean> list7) {
		// TODO Auto-generated method stub
		List <String> list = new ArrayList<String>();
		int num = 0;
		for(int i = 0; i < list3.size(); i++) {
			if(list3.get(i).getAstate() == 1)
				num++;
		}
		list.add("" + num);
		list.add("" + (list3.size() - num));
		list.add("" + list3.size());
		
		num = 0;
		for(int i = 0; i < list5.size(); i++) {
			if(list5.get(i).getAstate() == 1)
				num++;
		}
		list.add("" + num);
		list.add("" + (list5.size() - num));
		list.add("" + list5.size());
		
		num = 0;
		for(int i = 0; i < list7.size(); i++) {
			if(list7.get(i).getAstate() == 1)
				num++;
		}
		list.add("" + num);
		list.add("" + (list7.size() - num));
		list.add("" + list7.size());

		return list;
	}

}
