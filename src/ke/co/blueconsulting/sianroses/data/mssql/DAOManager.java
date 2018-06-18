package ke.co.blueconsulting.sianroses.data.mssql;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DAOManager {
  
  public static DAOManager getInstance() {
    return DAOManagerSingleton.INSTANCE.get();
  }
  
  public void open() throws SQLException {
    try {
      if (this.con == null || this.con.isClosed())
        this.con = src.getConnection();
    } catch (SQLException e) {
      throw e;
    }
  }
  
  public void close() throws SQLException {
    try {
      if (this.con != null && !this.con.isClosed())
        this.con.close();
    } catch (SQLException e) {
      throw e;
    }
  }
  
  //Private
  private DataSource src;
  private Connection con;
  
  private DAOManager() throws Exception {
    try {
      InitialContext ctx = new InitialContext();
      this.src = (DataSource) ctx.lookup("jndi/MYSQL");
    } catch (Exception e) {
      throw e;
    }
  }
  
  private static class DAOManagerSingleton {
    
    static ThreadLocal<DAOManager> INSTANCE;
    
    static {
      ThreadLocal<DAOManager> dm;
      try {
        dm = new ThreadLocal<DAOManager>() {
          @Override
          protected DAOManager initialValue() {
            try {
              return new DAOManager();
            } catch (Exception e) {
              return null;
            }
          }
        };
      } catch (Exception e) {
        dm = null;
        INSTANCE = dm;
      }
      
    }
    
  }
  
}
