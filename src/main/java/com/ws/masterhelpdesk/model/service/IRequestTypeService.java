package com.ws.masterhelpdesk.model.service;

import java.util.List;

import com.ws.masterhelpdesk.dto.RequestTypeDto;
import com.ws.masterhelpdesk.dto.insert.RequestTypeInsert;
import com.ws.masterhelpdesk.model.entity.RequestType;

public interface IRequestTypeService {
	public List<String> getEnabledNames() throws Exception;

	public RequestType findRequestTypeByName(String name) throws Exception;

	public List<RequestType> findAll();

	public RequestType findRequestTypeById(Long id);

	public void insert(RequestTypeInsert requestTypeInsert);

	public void update(RequestTypeDto requestTypeDto);

	public void deleteById(Long id);
}
