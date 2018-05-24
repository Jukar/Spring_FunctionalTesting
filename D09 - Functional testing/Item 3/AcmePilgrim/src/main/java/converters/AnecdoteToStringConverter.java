package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Anecdote;

@Component
@Transactional
public class AnecdoteToStringConverter implements Converter<Anecdote, String> {
	@Override
	public String convert(Anecdote source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());

		return result;
	}
}
