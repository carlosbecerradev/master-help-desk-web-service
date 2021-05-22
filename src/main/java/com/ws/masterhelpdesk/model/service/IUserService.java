package com.ws.masterhelpdesk.model.service;

import java.util.List;

import com.ws.masterhelpdesk.dto.UserDto;
import com.ws.masterhelpdesk.model.entity.Authority;
import com.ws.masterhelpdesk.model.entity.User;

public interface IUserService {

	public Authority[] getEnumAuthorities();

	public List<String> getAllAuthorities();

	public List<UserDto> getAllUserDto();

	public User getUserById(Long id);

	public UserDto getUserDtoById(Long id);

	public void insertUser(UserDto userDto);

	public void updateUser(UserDto userDto);
}
