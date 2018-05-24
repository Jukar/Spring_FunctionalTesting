package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Message;

import repositories.MessageRepository;

@Component
@Transactional
public class MessageToStringConverter implements Converter<Message,String>{
	@Autowired MessageRepository messageRepository;
	@Override 
	public String convert(Message message){
		String result;
		
		if(message==null){
			result=null;
		}
		else
			result=String.valueOf(message.getId());
		
		return result;
	}
	
	
}
