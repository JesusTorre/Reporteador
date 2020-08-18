
package BD;

import com.mysql.jdbc.Connection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionBD {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String user = "root";
    private static final String pass = "";
    private static Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private static Connection conn2;

    public void conectar() {
        conn = null;

        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Conexión");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void desconectar() {
        conn = null;
    }

    public List listarBD() {
        List<String> db = new ArrayList<>();
        String sql = "show databases";

        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                db.add(rs.getString(1)); 
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (List) db;
    }

    //Conecta a una BD en especifico
    public void conectarBD(String nombreDB) {
        conn2 = null;
        try {
            Class.forName(driver);
            conn2 = (Connection) DriverManager.getConnection(url + "/" + nombreDB, user, pass);
            if (conn2 != null) {
                System.out.println("Conexión");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnectionDB() {
        return conn2;
    }

    public List listarTablas() {
        List<String> db = new ArrayList<>();
        String sql = "SHOW TABLES";
        try {
            ps = getConnectionDB().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                db.add(rs.getString(1));  
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (List) db;
    }

    public List listarAtributos(String tabla) {
        List<String> db = new ArrayList<>();
        String sql = "DESCRIBE " + tabla;

        try {
            ps = getConnectionDB().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                db.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (List) db;
    }

    public List consulta(String consulta, String tabla, int tamano) throws IOException {

        List<String> resul = new ArrayList<>();
        String sql = "SELECT " + consulta + " FROM " + tabla;
        CrearArchivoQuery(sql);
        try {
            ps = getConnectionDB().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int j = 1;
                for (int i = 0; i < tamano; i++) {
                    resul.add(i, rs.getString(j));
                    j++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (List) resul;
    }

    public void CrearArchivoQuery(String query) throws IOException {
        String ruta = "query.sql";
        File archivo = new File(ruta);
        BufferedWriter bw;
        //Codicion para verificar si el archivo existe
       
        if (archivo.exists()) {
            //BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(archivo));
            bw = new BufferedWriter(new FileWriter(archivo, true));
            //En caso de que ya exista manda a guardar los datos 
            bw.write("\n");
            bw.write(query);
            bw.close();

        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw = new BufferedWriter(new FileWriter(archivo, true));
            //En caso de que ya exista manda a guardar los datos 
            bw.write("\n");
            bw.write(query);
            bw.close();

        }
    }

}
