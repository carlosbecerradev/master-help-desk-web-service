package com.ws.masterhelpdesk.model.service;

import java.util.List;

import com.ws.masterhelpdesk.model.entity.RequestType;

public interface IRequestTypeService {
	public List<String> getEnabledNames() throws Exception;

	public RequestType findRequestTypeByName(String name) throws Exception;
}
