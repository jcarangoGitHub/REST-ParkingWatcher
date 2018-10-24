package parkingwatcher.repository;

import parkingwatcher.model.ParkedVehicles;

public interface IDataAccess {
    ParkedVehicles mongo_insertParkedVehicle(ParkedVehicles pv);
}
