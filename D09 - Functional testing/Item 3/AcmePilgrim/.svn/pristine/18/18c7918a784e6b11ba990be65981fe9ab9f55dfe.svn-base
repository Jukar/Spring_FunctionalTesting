package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.StageOrder;

@Component
@Transactional
public class StageOrderToStringConverter implements Converter<StageOrder, String>{
	@Override
	public String convert(StageOrder source) {
		String result;
		
		if(source==null) 
			result=null;
		else 
			result=String.valueOf(source.getId());
		
		return result;
	}
}
