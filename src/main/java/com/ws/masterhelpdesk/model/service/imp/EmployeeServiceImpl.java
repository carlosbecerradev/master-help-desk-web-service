package com.ws.masterhelpdesk.model.service.imp;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.masterhelpdesk.dto.EmployeeDto;
import com.ws.masterhelpdesk.dto.insert.EmployeeInsert;
import com.ws.masterhelpdesk.dto.mapper.EmployeeMapper;
import com.ws.masterhelpdesk.model.entity.Authority;
import com.ws.masterhelpdesk.model.entity.Employee;
import com.ws.masterhelpdesk.model.entity.User;
import com.ws.masterhelpdesk.model.repository.IEmployeeRepository;
import com.ws.masterhelpdesk.model.service.IEmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

	private final IEmployeeRepository iEmployeeRepository;
	private final EmployeeMapper employeeMapper;

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeDto> getAllEmployeeDto() {
		return iEmployeeRepository.findAll().stream().map(employeeMapper::mapEntityToDto).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Employee getEmployeeById(Long id) {
		return iEmployeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee with ID: " + id + " is not found."));
	}

	@Override
	@Transactional(readOnly = true)
	public EmployeeDto getEmployeeDtoById(Long id) {
		return employeeMapper.mapEntityToDto(getEmployeeById(id));
	}

	@Override
	@Transactional(readOnly = false)
	public void insertEmployee(EmployeeInsert employeeInsert) {
		Employee newEmployee = new Employee();
		newEmployee.setName(employeeInsert.getName());
		newEmployee.setSurname(employeeInsert.getSurname());
		newEmployee.setEnabled(true);
		newEmployee.setCreatedAt(Instant.now());
		User user = new User();
		user.setUsername(employeeInsert.getUsername());
		user.setPassword(employeeInsert.getPassword());
		user.setAuthority(Authority.valueOf(employeeInsert.getAuthority().toUpperCase()));
		user.setEnabled(true);
		user.setCreatedAt(Instant.now());
		newEmployee.setUser(user);
		iEmployeeRepository.save(newEmployee);
		System.out.println("insert: " + newEmployee.toString());
	}

	@Override
	@Transactional(readOnly = false)
	public void updateEmployee(EmployeeDto employeeDto) {
		Employee employee = getEmployeeById(employeeDto.getId());
		employee = employeeMapper.mapDtoToEntity(employeeDto, employee);
		iEmployeeRepository.save(employee);
		System.out.println("update: " + employee.toString());
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEmployee(Long id) {
		iEmployeeRepository.deleteById(id);
	}

}
