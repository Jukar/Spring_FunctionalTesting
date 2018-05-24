package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Booking;

@Component
@Transactional
public class BookingToStringConverter implements Converter<Booking, String>{
	@Override
	public String convert(Booking source) {
		String result;
		if(source==null) 
			result=null;
		else 
			result=String.valueOf(source.getId());
		
		return result;
	}
}
