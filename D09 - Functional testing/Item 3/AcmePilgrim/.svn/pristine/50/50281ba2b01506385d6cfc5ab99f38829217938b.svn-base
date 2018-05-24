package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.LandmarkRepository;
import domain.Landmark;

@Component
@Transactional
public class StringToLandmarkConverter implements Converter<String,Landmark>{
	@Autowired 
	private LandmarkRepository landmarkRepository;

	@Override
	public Landmark convert(String text) {
		Landmark result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result=null;
			else{
				id=Integer.valueOf(text);
				result=landmarkRepository.findOne(id);
			}
		}catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		
		return result;
	}
}
