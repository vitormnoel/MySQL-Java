package application;

import db.DB;
import db.DbException;
//import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args){
    	
    	//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			//conn.setAutoCommit(false);
			
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"delete from cliente "
					+"where "
					+" name = ?"
					);
			
			st.setString(1, "Teste");
			st.executeUpdate();
			
			//conn.commit();
			
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Rolled back. caused by: "+e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro. Cased: "+e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
    }
}