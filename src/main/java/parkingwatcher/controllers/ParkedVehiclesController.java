package parkingwatcher.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parkingwatcher.model.ParkedVehicles;
import parkingwatcher.repository.ParkedVehiclesRepository;
import parkingwatcher.util.Creator;

import java.net.UnknownHostException;

@RestController
public class ParkedVehiclesController {
    @RequestMapping("/entryVehicle")
    public ParkedVehicles entryVehicle(@RequestParam(value = "typeVehicle") String type,
                                       @RequestParam(value = "idVehicle") String idVehicle) throws UnknownHostException {
        System.out.println("entryVehicle...");
        ParkedVehiclesRepository repository = new ParkedVehiclesRepository();
        return repository.insertParkedVehicle(Creator.createNewParkedVehicleToRegister(type, idVehicle));
    }

}