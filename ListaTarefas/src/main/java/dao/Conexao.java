package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;

public class Conexao {
    
    private static Driver driver = null;
    
    private static void registrarDriver(){    
        if(driver == null){
            
            try{
                driver = new Driver();
                DriverManager.registerDriver(driver);
                
                Conexao.driver = driver;
                
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    public static Connection abrirConexao(){
        registrarDriver();
        
        Connection connection = null;
        
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return connection;
    }
    
}
