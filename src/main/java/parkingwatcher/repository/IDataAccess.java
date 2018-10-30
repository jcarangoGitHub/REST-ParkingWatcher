package parkingwatcher.repository;

import parkingwatcher.model.parkedvehicle.ParkedVehicle;

import java.util.List;

public interface IDataAccess {
    ParkedVehicle insertParkedVehicle(ParkedVehicle pv);

    ParkedVehicle searchParkedVehicleByIdVehicleAndStatusParked(String idVehicle);

    ParkedVehicle updateParkedVehicle(ParkedVehicle parkedVehicle);

    List<ParkedVehicle> getParkedVehiclesByTypeAndStatusParked(String typeVehicle);

    List<ParkedVehicle> fetchAllVehiclesParked();
}
