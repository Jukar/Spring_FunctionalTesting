package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Discuss;

@Component
@Transactional
public class DiscussToStringConverter implements Converter<Discuss, String>{
	@Override
	public String convert(Discuss source) {
		String result;
		
		if(source==null) 
			result=null;
		else 
			result=String.valueOf(source.getId());
		
		return result;
	}
}
