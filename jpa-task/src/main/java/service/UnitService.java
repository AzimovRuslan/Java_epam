package service;

import dao.impl.UnitDaoImpl;
import models.Unit;

public class UnitService {
    private UnitDaoImpl unitDao = new UnitDaoImpl();

    public UnitService() {
    }

    public Unit getUnit(int id) {
        return unitDao.getByID(id);
    }

    public void saveUnit(Unit unit) {
        unitDao.save(unit);
    }

    public void updateUnit(Unit unit) {
        unitDao.update(unit);
    }

    public void deleteUnit(Unit unit) {
        unitDao.delete(unit);
    }
}
