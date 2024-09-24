package com.krushit.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.krushit.entity.EligibilityDetailsEntity;
import com.krushit.model.EligibilityDetails;

@Component
public class EDDetailsProcessor implements ItemProcessor<EligibilityDetailsEntity, EligibilityDetails> {

	@Override
	public EligibilityDetails process(EligibilityDetailsEntity item) throws Exception {
		if (item.getPlanStatus().equalsIgnoreCase("active")) {
			EligibilityDetails details = new EligibilityDetails();
			BeanUtils.copyProperties(item, details);
			return details;
		}
		return null;
	}

}
