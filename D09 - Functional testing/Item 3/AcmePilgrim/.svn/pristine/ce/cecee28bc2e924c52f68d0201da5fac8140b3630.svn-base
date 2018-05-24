package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.StageRating;

@Component
@Transactional
public class StageRatingToStringConverter implements Converter<StageRating, String>{
	@Override
	public String convert(StageRating source) {
		String result;
		
		if(source==null) 
			result=null;
		else 
			result=String.valueOf(source.getId());
		
		return result;
	}
}
