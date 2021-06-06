package com.ws.masterhelpdesk.model.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ws.masterhelpdesk.model.repository.IRequestTypeRepository;
import com.ws.masterhelpdesk.model.service.IRequestTypeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestTypeServiceImpl implements IRequestTypeService {

	private final IRequestTypeRepository iRequestTypeRepository;

	@Override
	public List<String> getAllNames() throws Exception {
		return iRequestTypeRepository.findAllNames();
	}

}
