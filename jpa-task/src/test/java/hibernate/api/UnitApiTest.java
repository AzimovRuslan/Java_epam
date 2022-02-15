package hibernate.api;

import models.Unit;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UnitService;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitApiTest {
    private Unit unit = new Unit();

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

        unit.setTitle("Spring");
    }

    @Test
    void saveUnitTest() {
        UnitService unitService = new UnitService();
        unitService.saveUnit(unit);
        int unitId = unit.getId();

        String actual = unitService
                .getUnit(unitId)
                .getTitle();

        assertEquals("Spring", actual);
    }

    @Test
    void updateUnitTest() {
        UnitService unitService = new UnitService();
        unitService.saveUnit(unit);
        int unitId = unit.getId();

        Unit unitForUpdate = unitService.getUnit(unitId);
        unitForUpdate.setTitle("ASP Net");
        unitService.updateUnit(unitForUpdate);

        String actual = unitService
                .getUnit(unitId)
                .getTitle();

        assertEquals("ASP Net", actual);
    }

    @Test
    void deleteUnitTest() {
        UnitService unitService = new UnitService();
        unitService.saveUnit(unit);
        int unitId = unit.getId();

        Unit unitForDelete = unitService.getUnit(unitId);
        unitService.deleteUnit(unitForDelete);

        List<Unit> units;

        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            session.beginTransaction();
            units = (List<Unit>) session.createQuery("from Unit ").list();
            session.getTransaction();
        }

        assertEquals(0, units.size());
    }
}
