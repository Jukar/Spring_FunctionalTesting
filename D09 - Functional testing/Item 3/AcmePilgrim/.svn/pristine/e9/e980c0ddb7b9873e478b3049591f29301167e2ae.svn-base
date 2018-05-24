package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Lodge;

@Component
@Transactional
public class LodgeToStringConverter implements Converter<Lodge, String>{
	@Override
	public String convert(Lodge source) {
		String result;
		
		if(source==null) 
			result=null;
		else 
			result=String.valueOf(source.getId());
		
		return result;
	}
}
