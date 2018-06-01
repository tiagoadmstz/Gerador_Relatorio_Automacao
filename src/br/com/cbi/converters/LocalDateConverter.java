/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.converters;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Tiago
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {

    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
        String data = "";
        if (attribute != null) {
            data = attribute.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return data;
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        LocalDate data = null;
        if (dbData != null && !dbData.equals("")) {
            data = LocalDate.parse(dbData, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return data;
    }

}
