package parkingwatcher.repository;

import com.mongodb.DBObject;
import parkingwatcher.model.ParkedVehicle;

import java.util.List;

public interface IDataAccess {
    ParkedVehicle insertParkedVehicle(ParkedVehicle pv);

    ParkedVehicle searchParkedVehicleByIdVehicleAndStatusParked(String idVehicle);

    ParkedVehicle updateParkedVehicle(ParkedVehicle parkedVehicle);

    List<ParkedVehicle> getParkedVehiclesByTypeAndStatusParked(String typeVehicle);
}
