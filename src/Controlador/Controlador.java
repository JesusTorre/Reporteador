
package Controlador;

import BD.ConfiguracionBD;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Controlador {

    List re;

    //Mustra las listas de Base de Datos que existen en el SISTEMA GESTOR
    public List listaBD() {

        ConfiguracionBD cc = new ConfiguracionBD();
        cc.conectar();
        java.util.List<String> lista = new ArrayList<>();
        lista = (List<String>) cc.listarBD();
        cc.desconectar();
        return (List) lista; 
    }

    //Accede a la Base de Datos seleccionada
    public void BD(String BD) {

        ConfiguracionBD cc = new ConfiguracionBD();
        cc.conectarBD(BD);
        cc.desconectar();

    }

    //Crea una lista con las Tablas que contiene la Base de Datos seleccionada
    public List listaTablas(String nombre) {

        ConfiguracionBD cc = new ConfiguracionBD();
        cc.conectarBD(nombre);
        java.util.List<String> lista = new ArrayList<>();
        lista = (List<String>) cc.listarTablas();
        return (List) lista;
    }

    //Crea una lista de los Atributos que contiene la Tabla seleccionada
    public List listaAtributos(String nombreBD, String nombreTabla) {

        ConfiguracionBD cc = new ConfiguracionBD();
        cc.conectarBD(nombreBD);
        java.util.List<String> lista = new ArrayList<>();
        lista = (List<String>) cc.listarAtributos(nombreTabla);
        return (List) lista;
    }

    
    //Se encarga de llama los reportes
    public void reporte(String nombreBD, String atributos, String nombreTabla, int tamano, String tipoReporte) throws IOException {

        ConfiguracionBD cc = new ConfiguracionBD();
        cc.conectarBD(nombreBD);
        re = cc.consulta(atributos, nombreTabla, tamano);

        if (tipoReporte.equals("CSV")) {
            CrearArchivoCSV(atributos, tamano);
        } else if (tipoReporte.equals("Excel")) {
            CrearArchivoExcel(atributos, tamano);
        }

    }

    //Crear el reporte .CSV
    public void CrearArchivoCSV(String nombreAtributos, int tamano) throws IOException {
        String ruta = "reporte.csv";
        File archivo = new File(ruta);
        BufferedWriter bw;
        //Codicion para verificar si el archivo existe
        if (archivo.exists()) {
            archivo.delete();

            bw = new BufferedWriter(new FileWriter(archivo));
            bw = new BufferedWriter(new FileWriter(archivo, true));
            bw.write(nombreAtributos);
            //En caso de que ya exista manda a guardar los datos 
            int j = 1;
            String dato = "";
            for (int i = 0; i < re.size(); i++) {
                dato += re.get(i) + ",";
                if (j == tamano) {
                    bw.write("\n");
                    bw.write(dato);
                    dato = "";
                    j = 0;
                }
                j++;
            }
            bw.close();
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));

            bw = new BufferedWriter(new FileWriter(archivo, true));
            bw.write(nombreAtributos);
            //En caso de que ya exista manda a guardar los datos 
            int j = 1;
            String dato = "";
            for (int i = 0; i < re.size(); i++) {
                dato += re.get(i) + ",";
                if (j == tamano) {
                    bw.write("\n");
                    bw.write(dato);
                    dato = "";
                    j = 0;
                }
                j++;
            }
            bw.close();

        }
    }

    //Crea el reporte de Excel
    public void CrearArchivoExcel(String nombreAtributos, int tamano) {
        String nombreArchivo = "Reporte.xls";
        String rutaArchivo = nombreArchivo;
        String hoja = "reporte";

        Workbook libro = new HSSFWorkbook();
        Sheet hoja1 = libro.createSheet(hoja);

        String[] header = nombreAtributos.split(",");
        Row row = hoja1.createRow(0);
        for (int i = 0; i < header.length; i++) {
            row.createCell(i).setCellValue(header[i]);
        }
        int j = 0;

        int count = 1;
        row = hoja1.createRow(count);
        for (int i = 0; i < re.size(); i++) {
            if (j == tamano) {
                row = hoja1.createRow(count++);
                j = 0;
            }
            row.createCell(j).setCellValue((String) re.get(i));
            j++;
        }

        File file;
        file = new File(rutaArchivo);
        try (FileOutputStream fileOuS = new FileOutputStream(file)) {
            if (file.exists()) {
                file.delete();
            }
            libro.write(fileOuS);
            fileOuS.flush();
            fileOuS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error a generar el Reporte.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error a generar el Reporte.");
        }

    }

    //Se encarga de dar acceso al sistema o denegar
    public boolean acceso(String usuario, String contrasenia) {
        boolean control = false;
        if (usuario.equals("admin") && contrasenia.equals("admin")) {
            control = true;
        } else {
            control = false;
        }
       
        return control;
    }

}
