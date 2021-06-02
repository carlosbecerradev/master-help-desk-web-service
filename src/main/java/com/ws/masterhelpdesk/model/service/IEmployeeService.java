package com.ws.masterhelpdesk.model.service;

import java.util.List;

import com.ws.masterhelpdesk.dto.EmployeeDto;
import com.ws.masterhelpdesk.dto.insert.EmployeeInsert;
import com.ws.masterhelpdesk.model.entity.Employee;

public interface IEmployeeService {

	public List<EmployeeDto> getAllEmployeeDto();

	public Employee getEmployeeById(Long id);

	public EmployeeDto getEmployeeDtoById(Long id);

	public void insertEmployee(EmployeeInsert employeeInsert);

	public void updateEmployee(EmployeeDto employeeDto);

	public void deleteEmployee(Long id);
}
