/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.util;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 *
 * @author tiago.teixeira
 */
public abstract class ManipulaBean<T> implements Serializable {

    private static final long serialVersionUID = 5442956909521649571L;

    public void clear() {
        try {
            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.getName().equals("serialVersionUID")) {
                    field.set(this, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copiar(T object) {
        try {
            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.getName().equals("serialVersionUID")) {
                    field.set(this, field.get(object));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T clonar() {
        try {
            T object = (T) getClass().getConstructor().newInstance();
            for(Field field : getClass().getDeclaredFields()){
                field.setAccessible(true);
                if (!field.getName().equals("serialVersionUID")) {
                    Field f = object.getClass().getDeclaredField(field.getName());
                    f.setAccessible(true);
                    f.set(object, field.get(this));
                }
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
