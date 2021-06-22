package com.ws.masterhelpdesk.model.service.imp;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.RequestTypeDto;
import com.ws.masterhelpdesk.dto.insert.RequestTypeInsert;
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

	@Override
	@Transactional(readOnly = true)
	public List<RequestType> findAll() {
		return iRequestTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public RequestType findRequestTypeById(Long id) {
		return iRequestTypeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Request Type with id: " + id + " is not found."));
	}

	@Override
	@Transactional(readOnly = false)
	public void insert(RequestTypeInsert requestTypeInsert) {
		RequestType entity = new RequestType();
		entity.setName(requestTypeInsert.getName());
		entity.setEnabled(true);
		entity.setCreatedAt(Instant.now());
		iRequestTypeRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(RequestTypeDto requestTypeDto) {
		RequestType entity = findRequestTypeById(requestTypeDto.getId());
		entity.setName(requestTypeDto.getName());
		entity.setEnabled(requestTypeDto.getEnabled());
		iRequestTypeRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		iRequestTypeRepository.deleteById(id);
	}

}
