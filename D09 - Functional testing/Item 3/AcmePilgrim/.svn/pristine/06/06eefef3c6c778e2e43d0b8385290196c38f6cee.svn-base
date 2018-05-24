package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.AnecdoteRepository;
import domain.Anecdote;

@Component
@Transactional
public class StringToAnecdoteConverter implements Converter<String, Anecdote>{
	@Autowired 
	private AnecdoteRepository anecdoteRepository;

	@Override
	public Anecdote convert(String text) {
		Anecdote result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result=null;
			else{
				id=Integer.valueOf(text);
				result=anecdoteRepository.findOne(id);
			}
		}catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		
		return result;
	}
}
