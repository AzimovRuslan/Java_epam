package service;

import dao.impl.EmployeeDaoImpl;
import models.Employee;
import models.Project;
import models.Unit;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
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

    public void addUnitById(Employee employee, int id) {
        UnitService unitService = new UnitService();
        Unit unit = unitService.getUnit(id);
        employee.setUnit(unit);
    }

    public void assignProjectById(Employee employee, int id) {
        ProjectService projectService = new ProjectService();
        Project project = projectService.getProject(id);
        employee.addProject(project);
    }

    public List<Employee> getEmployeeWhichAreNotExternal() {
        Session session = null;
        List<Employee> list = new ArrayList<>();
        try {
            session = HibernateSessionFactoryUtil.getSession();
            list = (List<Employee>) session.createQuery("from Employee where external =: external").setParameter("external", false).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list;
    }
}
