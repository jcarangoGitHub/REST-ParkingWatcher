package parkingwatcher.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parkingwatcher.model.parkedvehicle.ParkedVehicle;
import parkingwatcher.model.parkedvehicle.ParkedVehicleValidator;


@RestController
public class ParkedVehiclesController {
    @RequestMapping(value = "/entryVehicle", method = RequestMethod.POST)
    public ResponseEntity<ParkedVehicle> entryVehicle(@RequestParam(value = "typeVehicle") String type,
                                       @RequestParam(value = "idVehicle") String idVehicle) {

        try {
            ParkedVehicle parkedVehicle = new ParkedVehicle(type, idVehicle);
            ParkedVehicleValidator validator = new ParkedVehicleValidator(parkedVehicle);
            ResponseEntity<ParkedVehicle> responseEntity = validator.validateAll();
            if (responseEntity != null) {
                return responseEntity;
            }

            parkedVehicle = parkedVehicle.registerVehicleEntry();

            return new ResponseEntity<>(parkedVehicle, HttpStatus.CREATED);
        }catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setPragma(e.toString());

            return new ResponseEntity<>(responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/exitVehicle", method = RequestMethod.POST)
    public ResponseEntity<ParkedVehicle> exitVehicle(@RequestParam(value = "idVehicle") String idVehicle) {

        ParkedVehicle parkedVehicle = new ParkedVehicle(idVehicle);
        ParkedVehicle result = parkedVehicle.searchVehicleByIdVehicleAndStatusParked();
        if (null == result) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Server answer", "The vehicle with id " + idVehicle + "was not found");
            responseHeaders.setPragma("The vehicle with id " + idVehicle + " was not found");
            return new ResponseEntity<>(result, responseHeaders, HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            result.registerVehicleExit();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}