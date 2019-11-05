
package baserelacionalc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseRelacionalC {
//Neste exNeste exercicio imos traballar as insercions , modificacions consulta   mediante sentenzas preparadas.
//Unha sentenza preparada e unha sentenza SQL na que en lugar de valores concretos situamos interrogantes .
//Antes de executar a sentenza debemos sustituir ditos interogantes polos valores concretos con que queremos que se execute.
//A ventaxa disto e que a base de datos pode subir a cache e reusar as sentenzas preparadas co que se executan mais rapido que as sentenzas normais.
    
    Connection conn;
    
    public Connection Conexion() throws SQLException{
          String driver = "jdbc:oracle:thin:";
    String host = "localhost.localdomain";
    String porto = "1521";
    String sid = "orcl";
    String usuario = "hr";
    String password = "hr";
    String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
    
    Connection conn = DriverManager.getConnection(url);
    
    return conn;
    }
    public void Cerrar() throws SQLException{
        conn.close();
    }
    
    public void inserirProduto(String codigo, String descricion, int precio) throws SQLException{
        Connection conn= Conexion();
        try{
            PreparedStatement ps = conn.prepareStatement("insert into produtos values(?,?,?)");
            
            ps.setString(1,codigo);
            ps.setString(2, descricion);
            ps.setInt(3, precio);
            ps.executeUpdate();
            System.out.println("Insertado o novo produto");
        }catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalC.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro no actualizado do produto");
        }
        
    }
    public void actualizarProducto(String codigo, int precio) throws SQLException{
        Connection conn = Conexion();
        try{
          PreparedStatement ps = conn.prepareStatement("update produtos set precio =? where codigo =?");
                  ps.setInt(1,precio);
                  ps.setString(2, codigo);
                  
                  ps.executeUpdate();
                  System.out.println("produto actualizado");
        }catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalC.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro na actualizacion");
        
    }
        
    }
    public void lista() throws SQLException{
        Connection conn = Conexion();
        try{
            PreparedStatement ps = conn.prepareStatement("select produtos.* from produtos");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {

            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3));
            }
            }catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalC.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro fatal");
            
    }
    }
    public static void main(String[] args) throws SQLException {
      BaseRelacionalC obj = new BaseRelacionalC();
//      obj.inserirProduto("p20", "Galletas", 15);
//      obj.actualizarProducto("p1", 100);
      obj.lista();
      
      
      
    }
    
}
