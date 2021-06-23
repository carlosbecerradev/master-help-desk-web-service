package com.ws.masterhelpdesk.model.service.imp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.UserDto;
import com.ws.masterhelpdesk.dto.mapper.UserMapper;
import com.ws.masterhelpdesk.model.entity.Authority;
import com.ws.masterhelpdesk.model.entity.User;
import com.ws.masterhelpdesk.model.repository.IUserRepository;
import com.ws.masterhelpdesk.model.service.IUserService;
import com.ws.masterhelpdesk.security.service.UserSecurityService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

	private final IUserRepository iUserRepository;
	private final UserMapper userMapper;
	private final UserSecurityService userSecurityService;

	@Override
	public Authority[] getEnumAuthorities() {
		return Authority.values();
	}

	@Override
	public List<String> getAllAuthorities() {
		List<String> list = new ArrayList<String>();
		for (Authority authority : getEnumAuthorities()) {
			list.add(authority.toString());
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> getAllUserDto() {
		return iUserRepository.findAll().stream().map(userMapper::mapEntityToDto).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserById(Long id) {
		return iUserRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User with ID: " + id + " is not found."));
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getUserDtoById(Long id) {
		User userFound = getUserById(id);
		return userMapper.mapEntityToDto(userFound);
	}

	@Override
	@Transactional(readOnly = false)
	public void insertUser(UserDto userDto) {
		User newUser = new User();
		userDto.setPassword(userSecurityService.encodePassword(userDto.getPassword()));
		newUser = userMapper.mapDtoToEntity(userDto, newUser);
		newUser.setCreatedAt(Instant.now());
		iUserRepository.save(newUser);
		System.out.println("insert: " + newUser.toString());
	}

	@Override
	@Transactional(readOnly = false)
	public void updateUser(UserDto userDto) {
		User user = getUserById(userDto.getId());
		userDto.setPassword(userSecurityService.encodePassword(userDto.getPassword()));
		user = userMapper.mapDtoToEntity(userDto, user);
		iUserRepository.save(user);
		System.out.println("update: " + user.toString());
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteUser(Long id) {
		iUserRepository.deleteById(id);
	}

}
