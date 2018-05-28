/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.util;

import java.awt.Component;
import java.util.Arrays;
import javax.swing.JComponent;

/**
 *
 * @author tiago.teixeira
 */
public class Toogle {

    public static void toogleEnable(Component component) {
        component.setEnabled(!component.isEnabled());
    }

    public static void toogleEnable(JComponent component) {
        component.setEnabled(!component.isEnabled());
    }

    public static void toggleVisible(Component... comps) {
        Arrays.asList(comps).stream().forEach(c -> {
            c.setVisible(!c.isVisible());
        });
    }
    
    public static void toggleVisible(JComponent... comps) {
        Arrays.asList(comps).stream().forEach(c -> {
            c.setVisible(!c.isVisible());
        });
    }
}
