/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporteador;

import BD.ConfiguracionBD;
import Controlador.Controlador;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import vista.Acceso;
import vista.ListaBD;

public class Reporteador {

    public static void main(String[] args) throws SQLException, IOException, UnsupportedLookAndFeelException {
        // TODO code application logic here

        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("OptionPane.foreground", Color.BLUE);
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Calibri", 0, 22));
        UIManager.put("OptionPane.buttonFont", new java.awt.Font("Arial", 0, 18));
        UIManager.put("OptionPane.informationIcon",
                new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\logo.jpg"));
        UIManager.put("OptionPane.titleText", "Reporteador");
        UIManager.put("OptionPane.inputDialogTitle", "Reporteador");
        UIManager.put("OptionPane.messageDialogTitle", "Reporteador");

        UIManager.put("Panel.background", new ColorUIResource(255, 255, 255));
        UIManager.put("Button.background", new ColorUIResource(255, 255, 255));
        UIManager.put("TabbedPane.selected", new Color(191, 191, 243));
        UIManager.put("TabbedPane.selectedForeground", new Color(255, 255, 255));
        UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        UIManager.put("ComboBox.disabledBackground", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", Color.WHITE);
        UIManager.put("ComboBox.selectionForeground", Color.BLUE);
        UIManager.put("ComboBox.buttonBackground", new Color(0, 102, 51));
        UIManager.put("ComboBox.buttonDarkShadow", Color.WHITE);
        UIManager.put("ScrollBar.width", 10);
        UIManager.put("ScrollBar.trackHighlightForeground", (new Color(0, 102, 102)));
        UIManager.put("scrollbar", (new Color(0, 102, 102)));
        UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(0, 102, 102)));
        UIManager.put("ScrollBar.thumbHeight", 2);
        UIManager.put("ScrollBar.background", (new Color(255, 255, 255)));
        UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(new Color(0, 102, 102)));
        UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(new Color(0, 102, 102)));
        UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(new Color(0, 102, 102)));
        UIManager.put("ScrollBar.trackForeground", new ColorUIResource(new Color(0, 102, 102)));
        UIManager.put("ScrollBar.trackHighlight", new ColorUIResource(new Color(0, 102, 102)));
        UIManager.put("ScrollBar.foreground", new ColorUIResource(new Color(0, 102, 102)));
        UIManager.put("ScrollBar.shadow", new ColorUIResource(new Color(0, 102, 102)));
        UIManager.put("ScrollBar.highlight", new ColorUIResource(new Color(0, 102, 102)));

        UIManager.put("ScrollPane.background", (new Color(0, 102, 102)));
        UIManager.put("Table.background", (new Color(255, 255, 255)));
        UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Acceso().setVisible(true);
            }
        });

    }
}
