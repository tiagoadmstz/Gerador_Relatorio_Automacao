/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.dal;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.drda.NetworkServerControl;

/**
 *
 * @author Tiago D. Teixeira
 */
public class AlgoritimosDB {
    
    private static ThreadLocal<NetworkServerControl> servidordb = new ThreadLocal<NetworkServerControl>();
    private static NetworkServerControl servidor;
    
    public static void algoritimosDB(){
        try{
            //servidor = new NetworkServerControl(InetAddress.getLocalHost(),1527);
            servidor = new NetworkServerControl("127.0.0.1","1527");
            servidordb.set(servidor);
            //JOptionPane.showMessageDialog(null, "Servidor Estabelecido em " + InetAddress.getLocalHost(), "Endere�o do Servidor", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Servidor Estabelecido em 127.0.0.1", "Endereço do Servidor", JOptionPane.INFORMATION_MESSAGE);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao criar instancia do servidor!", "ERRO!!!!!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void iniciarServidor(){
        try{
            servidor.start(null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "N�o foi poss�vel iniciar o servidor", "ERRO!!!!!!!!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void desligarServidor(){
        try{
           servidor.shutdown();
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "O servidor n�o foi iniciado", "Servi�o n�o encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static String getDbAdress(){
        try {
            return InetAddress.getLocalHost() + ":1527/SimpleJob";
        } catch (UnknownHostException ex) {
            Logger.getLogger(AlgoritimosDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }
}
