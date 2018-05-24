package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Register;

@Component
@Transactional
public class RegisterToStringConverter implements Converter<Register, String>{
	@Override
	public String convert(Register source) {
		String result;
		if(source==null) 
			result=null;
		else 
			result=String.valueOf(source.getId());
		
		return result;
	}
}
