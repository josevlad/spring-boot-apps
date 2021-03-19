package ar.com.ada.second.springboottests.model.mapper.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Year;

@Converter(autoApply = true)
public class YearAttributeConverter implements AttributeConverter<Year, Short> {

    @Override
    public Short convertToDatabaseColumn(Year year) {
        // Short.valueOf(year.toString());

        return year != null
                ? (short) year.getValue()
                : null;
    }

    @Override
    public Year convertToEntityAttribute(Short dbShort) {
        return dbShort != null
                ? Year.of(dbShort)
                : null;
    }
}
