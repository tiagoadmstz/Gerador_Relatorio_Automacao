/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.util;

import java.awt.BorderLayout;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author tiago.teixeira
 */
public class JasperUtil {

    public static void imprimirRelatorio(Connection conn, String titulo, Map parametros, InputStream inputStream) {
        try {
            JasperPrint jpPrint = JasperFillManager.fillReport(inputStream, parametros, conn);
            viewReportFrame(titulo, jpPrint);
        } catch (Exception exceptionReport) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório selecionado!", "Erro ao gerar relatório", JOptionPane.ERROR_MESSAGE);
            exceptionReport.printStackTrace();
            JOptionPane.showMessageDialog(null, exceptionReport.getMessage(), "Erro ao gerar relatório", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void imprimirRelatorio(InputStream relatorio, Map parametros, String titulo, List<?> listaResultados) {
        try {
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaResultados);
            JasperPrint jp = JasperFillManager.fillReport(relatorio, parametros, ds);
            JasperViewer.viewReport(jp, false);
            //viewReportFrame(titulo, jp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imprimirRelatorioPdfTemp(Connection conn, String tituloArquivo, Map parametros, InputStream inputStream) {
        try {
            String source = "" + tituloArquivo + ".pdf";
            JasperPrint jpPrint = JasperFillManager.fillReport(inputStream, parametros, conn);
            JasperExportManager.exportReportToPdfFile(jpPrint, source.trim());
            Runtime.getRuntime().exec("cmd /c start " + source);
            File file = new File(source);
            file.deleteOnExit();
        } catch (Exception exceptionReport) {
            JOptionPane.showMessageDialog(null, "Não fio possível gerar o relatório selecionado!", "Erro ao gerar relatório", JOptionPane.ERROR_MESSAGE);
            exceptionReport.printStackTrace();
        }
    }

    private static void viewReportFrame(String titulo, JasperPrint print) {
        JRViewer viewer = new JRViewer(print);
        JFrame frameReport = new JFrame(titulo);
        frameReport.add(viewer, BorderLayout.CENTER);
        frameReport.setSize(500, 500);
        frameReport.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameReport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameReport.setVisible(true);
    }

}
