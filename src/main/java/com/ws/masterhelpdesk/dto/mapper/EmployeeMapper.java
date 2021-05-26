package com.ws.masterhelpdesk.dto.mapper;

import org.springframework.stereotype.Component;

import com.ws.masterhelpdesk.dto.EmployeeDto;
import com.ws.masterhelpdesk.model.entity.Employee;
import com.ws.masterhelpdesk.model.service.IUserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmployeeMapper {

	private final IUserService userService;

	public EmployeeDto mapEntityToDto(Employee employee) {
		return EmployeeDto.builder().id(employee.getEmployeeId()).name(employee.getName())
				.surname(employee.getSurname()).createdAt(employee.getCreatedAt()).enabled(employee.getEnabled())
				.userId(employee.getUser().getUserId()).build();
	}

	public Employee mapDtoToEntity(EmployeeDto employeeDto, Employee employee) {

		employee.setName(employeeDto.getName());
		employee.setSurname(employeeDto.getSurname());
		employee.setEnabled(employeeDto.getEnabled());
		employee.setUser(userService.getUserById(employeeDto.getUserId()));

		return employee;
	}
}
