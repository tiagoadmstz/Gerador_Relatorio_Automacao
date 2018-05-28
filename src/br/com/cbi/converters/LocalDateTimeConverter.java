/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.converters;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Tiago
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Long> {

    @Override
    public Long convertToDatabaseColumn(LocalDateTime attribute) {
        long millis = 0;
        if (attribute != null) {
            millis = attribute.toInstant(ZoneOffset.UTC).toEpochMilli();
        }
        return millis;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Long dbData) {
        LocalDateTime data = null;
        if (dbData != null && dbData > 0) {
            LocalDateTime.ofInstant(Instant.ofEpochMilli(dbData), ZoneId.systemDefault());
        }
        return data;
    }

}
