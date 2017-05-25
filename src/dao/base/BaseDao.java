package dao.base;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
	//建立连接，自己写
	private Connection con;

	public Connection getCon(SqlKey sqlKey) throws Exception {
		if (con==null) {
			Class.forName(sqlKey.getDriverName());
			con = DriverManager.getConnection(sqlKey.getUrl(), sqlKey.getName(),sqlKey.getPassword());
			System.out.println("change con");
		}
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	public void conclose(){
		try {
			if (con!=null) {
				con.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con=null;
	}
}
