package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import repositories.StageOrderRepository;
import domain.StageOrder;

public class StringToStageOrderConverter implements Converter<String, StageOrder>{
	@Autowired 
	private StageOrderRepository stageOrderRepository;

	@Override
	public StageOrder convert(String text) {
		StageOrder result;
		int id;
		try{
			if(StringUtils.isEmpty(text))
				result=null;
			else{
				id=Integer.valueOf(text);
				result=stageOrderRepository.findOne(id);
			}
		}catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		
		return result;
	}
}
