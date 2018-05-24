package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.DiscussRepository;
import domain.Discuss;
@Component
@Transactional
public class StringToDiscussConverter implements Converter<String, Discuss>{
	@Autowired 
	private DiscussRepository discussRepository;

	@Override
	public Discuss convert(String text) {
		Discuss result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result=null;
			else{
				id=Integer.valueOf(text);
				result=discussRepository.findOne(id);
			}
		}catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		
		return result;
	}
}
