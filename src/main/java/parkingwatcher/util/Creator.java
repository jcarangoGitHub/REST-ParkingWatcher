package parkingwatcher.util;

import parkingwatcher.model.ParkedVehicles;

import java.util.Date;

public class Creator {
    public static ParkedVehicles createNewParkedVehicleToRegister(String typeVehicle, String idVehicle) {
        ParkedVehicles pv = new ParkedVehicles(typeVehicle, idVehicle);
        pv.setEntryDate(new Date());
        pv.setStatus("PARKED");
        pv.setPaidValue(0.0);

        return pv;
    }
}
