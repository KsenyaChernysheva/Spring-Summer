package spring.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.dao.ReasonDao;
import spring.model.Reason;

@Component
public class StringToReasonConverter implements Converter<String, Reason> {
    @Autowired
    private ReasonDao reasonDao;

    @Override
    public Reason convert(String id) {
        try {
            return reasonDao.findOne(Long.valueOf(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
