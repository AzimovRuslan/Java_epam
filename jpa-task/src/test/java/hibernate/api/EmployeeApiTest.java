package hibernate.api;

import models.Employee;
import models.EmployeePersonalInfo;
import models.Project;
import models.Unit;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.EmployeeService;
import service.ProjectService;
import service.UnitService;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeApiTest {
    private Employee employee = new Employee();

    @BeforeEach
    public void init() {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.createQuery("delete from Employee").executeUpdate();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.createQuery("delete from Unit ").executeUpdate();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.createQuery("delete from Project ").executeUpdate();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            session = HibernateSessionFactoryUtil.getSession();
            session.beginTransaction();
            session.createQuery("delete from EmployeePersonalInfo ").executeUpdate();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        employee.setEmployeeStatus("student");
        employee.setAddress("Mazurova");
        employee.setExternal(true);
        EmployeePersonalInfo employeePersonalInfo = new EmployeePersonalInfo("Ruslan", "Azimov", 20);
        employee.setEmployeePersonalInfo(employeePersonalInfo);
    }

    @Test
    void saveEmployeeTest() {
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee(employee);
        int employeeId = employee.getId();

        String actual = employeeService
                .getEmployee(employeeId)
                .getEmployeePersonalInfo()
                .getName();

        assertEquals("Ruslan", actual);
    }

    @Test
    void updateEmployeeTest() {
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee(employee);
        int employeeId = employee.getId();

        Employee employeeForUpdate = employeeService.getEmployee(employeeId);
        employeeForUpdate.setAddress("Kosareva");
        employeeService.updateEmployee(employeeForUpdate);

        String actual = employeeService.getEmployee(employeeId).getAddress();

        assertEquals("Kosareva", actual);
    }

    @Test
    void deleteEmployeeTest() {
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveEmployee(employee);
        int employeeId = employee.getId();

        Employee employeeForDelete = employeeService.getEmployee(employeeId);
        employeeService.deleteEmployee(employeeForDelete);

        List<Employee> employees;

        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            employees = (List<Employee>) session.createQuery("from Employee").list();
            session.getTransaction();
        }

        assertEquals(0, employees.size());
    }

    @Test
    void addUnitByIdTest() {
        UnitService unitService = new UnitService();
        Unit unit = new Unit("Spring");
        unitService.saveUnit(unit);
        int unitId = unit.getId();

        EmployeeService employeeService = new EmployeeService();
        employeeService.addUnitById(employee, unitId);
        employeeService.saveEmployee(employee);
        int employeeId = employee.getId();

        String actual = employeeService
                .getEmployee(employeeId)
                .getUnit()
                .getTitle();

        assertEquals("Spring", actual);
    }

    @Test
    void assignProjectByIdTest() {
        ProjectService projectService = new ProjectService();
        Project project = new Project("Hospital");
        projectService.saveProject(project);
        int projectId = project.getId();

        EmployeeService employeeService = new EmployeeService();
        employeeService.assignProjectById(employee, projectId);
        employeeService.saveEmployee(employee);
        int employeeId = employee.getId();

        String actual = employeeService
                .getEmployee(employeeId)
                .getProjects()
                .iterator()
                .next()
                .getName();

        assertEquals("Hospital", actual);
    }

    @Test
    void getProjectsWithEmployeesWhichAreNotExternalTest() {
        ProjectService projectService = new ProjectService();
        EmployeeService employeeService = new EmployeeService();

        Project school = new Project("School");
        projectService.saveProject(school);
        int schoolProjectId = school.getId();

        Project hospital = new Project("Hospital");
        projectService.saveProject(hospital);
        int hospitalProjectId = hospital.getId();

        Employee ruslan = new Employee("student", "Rechickiy", true);
        EmployeePersonalInfo ruslanEmployeePersonalInfo = new EmployeePersonalInfo("Ruslan", "Azimov", 20);
        ruslan.setEmployeePersonalInfo(ruslanEmployeePersonalInfo);
        employeeService.assignProjectById(ruslan, schoolProjectId);
        employeeService.saveEmployee(ruslan);

        Employee pavel = new Employee("student", "Kosareva", false);
        EmployeePersonalInfo pavelEmployeePersonalInfo = new EmployeePersonalInfo("Pavel", "Perunov", 21);
        pavel.setEmployeePersonalInfo(pavelEmployeePersonalInfo);
        employeeService.assignProjectById(pavel, schoolProjectId);
        employeeService.saveEmployee(pavel);

        Employee vadim = new Employee("student", "Mazurova", false);
        EmployeePersonalInfo vadimEmployeePersonalInfo = new EmployeePersonalInfo("Vadim", "Lepeev", 22);
        vadim.setEmployeePersonalInfo(vadimEmployeePersonalInfo);
        employeeService.assignProjectById(vadim, hospitalProjectId);
        employeeService.saveEmployee(vadim);

        List<Employee> employeesWhichAreNotExternal = employeeService.getEmployeeWhichAreNotExternal();

        String pavelProject = employeesWhichAreNotExternal
                .iterator()
                .next()
                .getProjects()
                .iterator()
                .next()
                .getName();

        String vadimProject = employeesWhichAreNotExternal
                .get(1)
                .getProjects()
                .iterator()
                .next()
                .getName();

        String pavelName = employeesWhichAreNotExternal
                .iterator()
                .next()
                .getEmployeePersonalInfo()
                .getName();

        String vadimName = employeesWhichAreNotExternal
                .get(1)
                .getEmployeePersonalInfo()
                .getName();

        assertEquals("Pavel", pavelName);
        assertEquals("Vadim", vadimName);
        assertEquals("School", pavelProject);
        assertEquals("Hospital", vadimProject);
        assertEquals(2, employeesWhichAreNotExternal.size());
    }
}
