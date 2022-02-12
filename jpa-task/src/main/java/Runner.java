import dao.impl.UnitDaoImpl;
import models.Employee;
import models.EmployeePersonalInfo;
import models.Project;
import models.Unit;
import service.EmployeeService;
import service.ProjectService;
import service.UnitService;
import utils.HibernateSessionFactoryUtil;

import javax.management.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        UnitService unitService = new UnitService();
        ProjectService projectService = new ProjectService();

        employeeService.deleteEmployee(employeeService.getEmployee(19));

//        Project project = projectService.getProject(1);
//        Unit unit = unitService.getUnit(1);
//        EmployeePersonalInfo employeePersonalInfo = new EmployeePersonalInfo("Ruslan", "Azimov", 20);
//        Employee employee = new Employee("11", "22", true);
//        employee.setEmployeePersonalInfo(employeePersonalInfo);
//        employeeService.addUnitById(employee, 1);
//        employeeService.saveEmployee(employee);
//        Project project = projectService.getProject(1);
//        employeeService.getEmployee(18).addProject(project);
//        employeeService.deleteEmployee(employeeService.getEmployee(18));
//        employee.setUnit(unit);
//        employee.addProject(project);
//        employeeService.saveEmployee(employee);

//
//        Project project = new Project("EPAM");
//
//        projectService.saveProject(project);


//        employeeService.deleteEmployee(employeeService.getEmployee(15));

//        Employee employee = new Employee("11", "22", true);
//        EmployeePersonalInfo employeePersonalInfo = new EmployeePersonalInfo("Ruslan", "Azimov", 20);
//        Unit unit = unitService.getUnit(1);
//        employee.setUnit(unit);
//        employee.setEmployeePersonalInfo(employeePersonalInfo);
//        employeeService.saveEmployee(employee);
//        Unit unit = new Unit("Spring");
//        employee.setUnit(unit);
//        employee.setEmployeePersonalInfo(employeePersonalInfo);
//        employeeService.saveEmployee(employee);
    }
}
