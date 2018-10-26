package parkingwatcher.model.parkedvehicle;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ParkedVehicleValidator {

    private ParkedVehicle parkedVehicle;

    public ParkedVehicleValidator(ParkedVehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public ResponseEntity<ParkedVehicle> validateAll() {
        ResponseEntity<ParkedVehicle> response;

        response = validationsGroupOne();
        if(response != null) {
            return response;
        }

        response = validationsGroupTwo();
        if(response != null) {
            return response;
        }
        return null;
    }

    private ResponseEntity<ParkedVehicle> validationsGroupTwo() {
        ResponseEntity<ParkedVehicle> response;
        response = validateAvailableSpace();
        if(response != null) {
            return response;
        }
        response = canEnterVehicleToday();
        if(response != null) {
            return response;
        }
        return null;
    }

    private ResponseEntity<ParkedVehicle> validationsGroupOne() {
        ResponseEntity<ParkedVehicle> response;
        response = hasTheVehicleAlreadyBeenRegistered();
        if(response != null) {
            return response;
        }
        response = isTypeVehicleAllowed();
        if(response != null) {
            return response;
        }
        response = validateAvailableSpace();
        if(response != null) {
            return response;
        }
        return null;
    }

    private ResponseEntity<ParkedVehicle> canEnterVehicleToday() {
        if(parkedVehicle.startIdVehicleWithDefinedLetter() && parkedVehicle.isTodayADayToValidate()) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setPragma("Sorry, today you can not enter the vehicle: " + parkedVehicle.getIdVehicle());
            return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
        }
        return null;
    }

    private ResponseEntity<ParkedVehicle> validateAvailableSpace() {
        if (!parkedVehicle.validateNumberOfVehiclesParkedCurrently()) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setPragma("Sorry, currently we do not have space available for the type of vehicle: "
                    + parkedVehicle.getTypeVehicle());
            return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
        }
        return null;
    }

    private ResponseEntity<ParkedVehicle> hasTheVehicleAlreadyBeenRegistered() {
        if (parkedVehicle.searchVehicleByIdVehicleAndStatusParked() != null) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setPragma("Sorry, the vehicle with ID  " + parkedVehicle.getIdVehicle()
                    + " is already registered");
            return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
        }
        return null;
    }

    private ResponseEntity<ParkedVehicle> isTypeVehicleAllowed() {
        if(!parkedVehicle.validateTypeVehicle(parkedVehicle.getTypeVehicle())) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setPragma("The type of vehicle " + parkedVehicle.getTypeVehicle() +
                    " is not allowed ,you can only choose between C or M");
            return new ResponseEntity<>(responseHeaders, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
