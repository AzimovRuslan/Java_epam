package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employee_status")
    private String employeeStatus;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_personal_info_id")
    private EmployeePersonalInfo employeePersonalInfo;

    @Column(name = "external")
    private boolean external;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employees_projects",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private Set<Project> projects = new HashSet<>();

    public Employee() {
    }

    public Employee(String employeeStatus, String address, boolean external) {
        this.employeeStatus = employeeStatus;
        this.address = address;
        this.external = external;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmployeePersonalInfo getEmployeePersonalInfo() {
        return employeePersonalInfo;
    }

    public void setEmployeePersonalInfo(EmployeePersonalInfo employeePersonalInfo) {
        this.employeePersonalInfo = employeePersonalInfo;
    }
}
