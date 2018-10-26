package parkingwatcher.repository;

import parkingwatcher.model.ParkedVehicle;

import java.util.List;

public class ParkedVehiclesRepository {
    public ParkedVehiclesRepository() {
    }

    public ParkedVehicle insertParkedVehicle(ParkedVehicle pv) {

        IDataAccess dataAccess = new MongoDataAccess();
        pv = dataAccess.insertParkedVehicle(pv);

        return pv;
    }

    public ParkedVehicle searchParkedVehicleByIdVehicle(String idVehicle) {
        IDataAccess dataAccess = new MongoDataAccess();
        return dataAccess.searchParkedVehicleByIdVehicleAndStatusParked(idVehicle);
    }

    public ParkedVehicle updateParkedVehicle(ParkedVehicle parkedVehicle) {
        IDataAccess dataAccess = new MongoDataAccess();
        return dataAccess.updateParkedVehicle(parkedVehicle);
    }

    public List<ParkedVehicle> getParkedVehiclesByTypeAndStatusParked(String typeVehicle) {
        IDataAccess dataAccess = new MongoDataAccess();
        return dataAccess.getParkedVehiclesByTypeAndStatusParked(typeVehicle);
    }
}
