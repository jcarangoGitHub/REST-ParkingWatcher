package parkingwatcher.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parkingwatcher.model.ParkedVehicle;


@RestController
public class ParkedVehiclesController {
    @RequestMapping("/entryVehicle")
    public ResponseEntity<ParkedVehicle> entryVehicle(@RequestParam(value = "typeVehicle") String type,
                                       @RequestParam(value = "idVehicle") String idVehicle) {

        try {
            ParkedVehicle parkedVehicle = new ParkedVehicle(type, idVehicle);
            if (parkedVehicle.searchVehicleByIdVehicle() != null) {
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.setPragma("Sorry, the vehicle with ID  " + idVehicle + " is already registered");
                return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
            }

            if(!parkedVehicle.validateTypeVehicle(type)) {
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.setPragma("The type of vehicle " + type +
                        " is not allowed ,you can only choose between C or M");
                return new ResponseEntity<>(responseHeaders, HttpStatus.BAD_REQUEST);
            }

            if (!parkedVehicle.validateNumberOfVehiclesParkedCurrently()) {
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.setPragma("Sorry, we currently do not have space available for the type vehicle: " + type);
                return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
            }

            if(parkedVehicle.startIdVehicleWithDefinedLetter() && parkedVehicle.isTodayADayToValidate()) {
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.setPragma("Sorry, today you can not enter the vehicle: " + idVehicle);
                return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
            }

            parkedVehicle = parkedVehicle.registerVehicleEntry();

            return new ResponseEntity<>(parkedVehicle, HttpStatus.CREATED);
        }catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setPragma(e.toString());

            return new ResponseEntity<>(responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/exitVehicle")
    public ResponseEntity<ParkedVehicle> exitVehicle(@RequestParam(value = "idVehicle") String idVehicle) {

        ParkedVehicle parkedVehicle = new ParkedVehicle(idVehicle);
        ParkedVehicle result = parkedVehicle.searchVehicleByIdVehicle();
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