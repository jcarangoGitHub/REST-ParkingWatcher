package parkingwatcher.util;

import com.mongodb.DBObject;
import parkingwatcher.model.parkedvehicle.ParkedVehicle;

import java.util.Map;

public class Converter {
    public Converter() {
    }

    public ParkedVehicle DBObjectToParkedVehicle(DBObject dbObject) {
        Map map = dbObject.toMap();
        String idVehicle = (String) map.get("idVehicle");
        String typeVehicle = (String) map.get("typeVehicle");
        String entryDate = (String) map.get("entryDate");
        String exitDate = (String) map.get("exitDate");
        String status = (String) map.get("status");
        Double paidValue = (Double) map.get("paidValue");
        double engineCapacity = (double) map.get("engineCapacity");

        ParkedVehicle parkedVehicle = new ParkedVehicle(idVehicle);
        parkedVehicle.setTypeVehicle(typeVehicle);
        parkedVehicle.setEntryDate(entryDate);
        parkedVehicle.setExitDate(exitDate);
        parkedVehicle.setStatus(status);
        parkedVehicle.setPaidValue(paidValue);
        parkedVehicle.setEngineCapacity(engineCapacity);

        return parkedVehicle;
    }
}
