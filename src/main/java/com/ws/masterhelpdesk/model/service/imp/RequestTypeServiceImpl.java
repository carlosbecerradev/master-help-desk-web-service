package com.ws.masterhelpdesk.model.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.model.entity.RequestType;
import com.ws.masterhelpdesk.model.repository.IRequestTypeRepository;
import com.ws.masterhelpdesk.model.service.IRequestTypeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestTypeServiceImpl implements IRequestTypeService {

	private final IRequestTypeRepository iRequestTypeRepository;

	@Override
	@Transactional(readOnly = true)
	public List<String> getEnabledNames() throws Exception {
		return iRequestTypeRepository.findEnabledNames();
	}

	@Override
	@Transactional(readOnly = true)
	public RequestType findRequestTypeByName(String name) throws Exception {
		return iRequestTypeRepository.findByName(name)
				.orElseThrow(() -> new RuntimeException("Request type whith Name: " + name + "is not found."));
	}

}
