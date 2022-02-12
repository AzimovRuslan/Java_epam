package service;

import dao.impl.EmployeeDaoImpl;
import models.Employee;
import models.Project;
import models.Unit;

import java.util.List;

public class EmployeeService {
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    public EmployeeService() {

    }

    public Employee getEmployee(int id) {
        return employeeDao.getByID(id);
    }

    public void saveEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }

    public void addUnitById(Employee employee, int id) {
        UnitService unitService = new UnitService();
        Unit unit = unitService.getUnit(id);
        employee.setUnit(unit);
    }
}
