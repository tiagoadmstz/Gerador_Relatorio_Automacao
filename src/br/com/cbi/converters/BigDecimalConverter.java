/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.converters;

import java.math.BigDecimal;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Tiago
 */
@Converter(autoApply = true)
public class BigDecimalConverter implements AttributeConverter<BigDecimal, String>{

    @Override
    public String convertToDatabaseColumn(BigDecimal x) {
        return x.toString();
    }

    @Override
    public BigDecimal convertToEntityAttribute(String y) {
        return new BigDecimal(y);
    }
    
}
