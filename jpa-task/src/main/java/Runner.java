import dao.impl.UnitDaoImpl;
import models.Employee;
import models.EmployeePersonalInfo;
import models.Project;
import models.Unit;
import service.EmployeeService;
import service.ProjectService;
import service.UnitService;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.From;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        UnitService unitService = new UnitService();
        ProjectService projectService = new ProjectService();
    }
}
