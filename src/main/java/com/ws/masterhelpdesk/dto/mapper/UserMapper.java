package com.ws.masterhelpdesk.dto.mapper;

import org.springframework.stereotype.Component;

import com.ws.masterhelpdesk.dto.UserDto;
import com.ws.masterhelpdesk.model.entity.Authority;
import com.ws.masterhelpdesk.model.entity.User;

@Component
public class UserMapper {

	public UserDto mapEntityToDto(User user) {
		return UserDto.builder().id(user.getUserId()).username(user.getUsername()).password(user.getPassword())
				.authority(user.getAuthority().toString()).enabled(user.getEnabled()).createdAt(user.getCreatedAt())
				.build();
	}

	public User mapDtoToEntity(UserDto userDto, User user) {

		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setEnabled(userDto.getEnabled());

		for (Authority authority : Authority.values()) {
			if (authority.toString().equalsIgnoreCase(userDto.getAuthority())) {
				user.setAuthority(authority);
			}
		}
		return user;
	}

}
