/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Tiago
 * @param <T>
 */
public abstract class ManipulaBean<T> implements Serializable {

    private static final long serialVersionUID = 3404889011617196182L;

    public void clear() {
        try {
            List<Field> fields = Arrays.asList(getClass().getDeclaredFields());
            fields.stream().forEach(f -> {
                f.setAccessible(true);
                if (!f.getName().equals("serialVersionUID")) {
                    try {
                        f.set(this, null);
                    } catch (Exception ex) {
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copiar(T object) {
        try {
            Field[] f1 = object.getClass().getDeclaredFields();
            Field[] f2 = getClass().getDeclaredFields();
            for (int i = 0; i < f1.length; i++) {
                f1[i].setAccessible(true);
                f2[i].setAccessible(true);
                if (!f1[i].getName().equals("serialVersionUID")) {
                    try {
                        f2[i].set(this, f1[i].get(object));
                    } catch (Exception ex) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T clonar() {
        try {
            Field[] f1 = getClass().getDeclaredFields();
            Object ob = getClass().getConstructor().newInstance();
            Field[] f2 = ob.getClass().getDeclaredFields();
            for (int i = 0; i < f1.length; i++) {
                f1[i].setAccessible(true);
                f2[i].setAccessible(true);
                if (!f1[i].getName().equals("serialVersionUID")) {
                    try {
                        f2[i].set(ob, f1[i].get(this));
                    } catch (Exception ex) {
                    }
                }
            }
            return (T) ob;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
