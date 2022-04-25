package hibernate.api;

import models.Project;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ProjectService;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectApiTest {
    private Project project = new Project();

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

        project.setName("Hospital");
    }

    @Test
    void saveProjectTest() {
        ProjectService projectService = new ProjectService();
        projectService.saveProject(project);
        int projectId = project.getId();

        String actual = projectService
                .getProject(projectId)
                .getName();

        assertEquals("Hospital", actual);
    }

    @Test
    void updateProjectTest() {
        ProjectService projectService = new ProjectService();
        projectService.saveProject(project);
        int projectId = project.getId();

        Project projectForUpdate = projectService.getProject(projectId);
        projectForUpdate.setName("School");
        projectService.updateProject(projectForUpdate);

        String actual = projectService
                .getProject(projectId)
                .getName();

        assertEquals("School", actual);
    }

    @Test
    void deleteProjectTest() {
        ProjectService projectService = new ProjectService();
        projectService.saveProject(project);
        int projectId = project.getId();

        Project projectForDelete = projectService.getProject(projectId);
        projectService.deleteProject(projectForDelete);

        List<Project> projects;

        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            projects = (List<Project>) session.createQuery("from Project ").list();
            session.getTransaction();
        }

        assertEquals(0, projects.size());
    }
}
