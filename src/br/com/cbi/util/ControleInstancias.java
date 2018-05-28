package br.com.cbi.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

/**
 *
 * @author Tiago D. Teixeira
 */
public class ControleInstancias {

    private static final WindowCloseListener LISTENER = new WindowCloseListener();
    private static final Map<String, Object> INSTANCIAS = new HashMap();

    public static void setControleInstancias(String nome, Object object) {
        INSTANCIAS.put(nome, object);
        if (object instanceof JFrame) {
            JFrame frame = (JFrame) object;
            frame.addWindowListener(LISTENER);
        }
    }

    public static Object getInstance(String nome) {
        //return INSTANCIAS.get(nome);
        Object ob = INSTANCIAS.get(nome);
        if(ob == null){
            try{
                ob = createObject(Class.forName(nome));
                setControleInstancias(nome, ob);
            } catch (Exception ex){
                return ob;
            }
        }
        return ob;
    }

    public static void removeInstance(String nome) {
        INSTANCIAS.remove(nome);
    }

    public static void logout(JFrame loginFrame) {
        clear();
        setControleInstancias(loginFrame.getClass().getName(), loginFrame);
        loginFrame.setVisible(true);
    }

    private static void clear() {
        INSTANCIAS.values().forEach(ob -> {
            JFrame frame = (JFrame) ob;
            frame.dispose();
        });
        INSTANCIAS.clear();
    }

    private static class WindowCloseListener extends WindowAdapter {

        public WindowCloseListener() {
            super();
        }

        @Override
        public void windowClosed(WindowEvent e) {
            if (e.getID() == WindowEvent.WINDOW_CLOSED) {
                removeInstance(e.getSource().getClass().getName());
            }
        }
    }

    private static Object createObject(Class<?> classe) throws Exception {
            for (Constructor construtor : classe.getConstructors()) {
                if(construtor.getParameterCount() == 0){
                    return construtor.newInstance();
                }
            }
            return null;
    }

}
