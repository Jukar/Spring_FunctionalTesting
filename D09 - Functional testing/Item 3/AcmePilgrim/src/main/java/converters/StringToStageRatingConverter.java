package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.StageRatingRepository;
import domain.StageRating;
@Component
@Transactional
public class StringToStageRatingConverter implements Converter<String, StageRating>{
	@Autowired 
	private StageRatingRepository stageRatingRepository;

	@Override
	public StageRating convert(String text) {
		StageRating result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result=null;
			else{
				id=Integer.valueOf(text);
				result=stageRatingRepository.findOne(id);
			}
		}catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		
		return result;
	}
}
