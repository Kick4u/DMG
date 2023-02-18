package DBTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aventstack.extentreports.Status;

import Base.BaseClass;
import org.junit.Test;

public class MSSQL extends BaseClass
{

	public ResultSet rs=null;
	
	public ResultSet CheckTodoItemsMSSQL() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Boolean status = false;
		try
		{			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			log.info("Loading driver");
			
			//DB Connection & Fetch data
			Connection con = DriverManager.getConnection("jdbc:sqlserver://103.253.109.23:1433;databaseName=TestAppDB;user=dmg;password=dmg@123qweASD");
			
			Statement stmt = con.createStatement();
			String qry="SELECT * FROM todoItems";
			
			rs=stmt.executeQuery(qry);
		}
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
		}
		return rs;
	}
	
	@Test
	public void CheckTodoItems() throws ClassNotFoundException, SQLException
	{
		try
		{
			test = extent.createTest("Todo Items checking with database ");

			ResultSet rs= CheckTodoItemsMSSQL();
		
			while(rs.next()) 
			{ 
				System.out.println(rs.getString("title") + "   " + rs.getString("complete")); 
			}

			test.log(Status.INFO, "Fetching data from DB");
			log.info("Fetching data from DB");
			}
			catch (Exception e) {
				// TODO: handle exception
				log.info(e.getLocalizedMessage());
			}
	}
}
