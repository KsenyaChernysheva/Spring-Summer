package spring.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.model.Reason;

@Component
public class ReasonToStringConverter implements Converter<Reason, String> {
    @Override
    public String convert(Reason reason) {
        return reason.getId().toString();
    }
}
