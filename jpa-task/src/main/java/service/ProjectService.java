package service;

import dao.impl.ProjectDaoImpl;
import models.Project;

public class ProjectService {
    private ProjectDaoImpl projectDao = new ProjectDaoImpl();

    public ProjectService() {
    }

    public Project getProject(int id) {
        return projectDao.getByID(id);
    }

    public void saveProject(Project project) {
        projectDao.save(project);
    }

    public void updateProject(Project project) {
        projectDao.update(project);
    }

    public void deleteProject(Project project) {
        projectDao.delete(project);
    }
}
