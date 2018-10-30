package parkingwatcher.repository;

import parkingwatcher.model.parkedvehicle.ParkedVehicle;

import java.util.List;

public class ParkedVehiclesRepository {
    public ParkedVehiclesRepository() {
    }

    public ParkedVehicle insertParkedVehicle(ParkedVehicle pv) {

        IDataAccess dataAccess = new MongoDataAccess();
        pv = dataAccess.insertParkedVehicle(pv);

        return pv;
    }

    public ParkedVehicle searchParkedVehicleByIdVehicleAndStatusParked(String idVehicle) {
        IDataAccess dataAccess = new MongoDataAccess();
        return dataAccess.searchParkedVehicleByIdVehicleAndStatusParked(idVehicle);
    }

    public List<ParkedVehicle> fetchAllVehiclesParked() {
        IDataAccess dataAccess = new MongoDataAccess();
        return dataAccess.fetchAllVehiclesParked();
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
