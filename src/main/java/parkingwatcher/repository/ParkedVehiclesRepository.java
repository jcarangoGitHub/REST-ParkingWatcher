package parkingwatcher.repository;

import parkingwatcher.model.ParkedVehicles;

public class ParkedVehiclesRepository {
    public ParkedVehiclesRepository() {
    }

    public ParkedVehicles insertParkedVehicle(ParkedVehicles pv) {

        IDataAccess dataAccess = new MongoDataAccess();
        pv = dataAccess.mongo_insertParkedVehicle(pv);

        return pv;
    }
}
