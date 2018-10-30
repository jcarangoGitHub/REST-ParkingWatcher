package parkingwatcher.model.parkedvehicle;

import parkingwatcher.entities.EParkedVehicle;
import parkingwatcher.repository.ParkedVehiclesRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParkedVehicle extends EParkedVehicle {

    private static final int _numberOfCarsAllowed = 20;
    private static final int _numberOfMotorcyclesAllowed = 10;
    private static final String _idThatStartWithLetter = "A";
    private static final int[] _daysOfTheWeekToValidate = {1,2};
    private static final double _carHourCost = 1000.0;
    private static final double _carDayCost = 8000.0;
    private static final double _motorcycleHourCost = 500.0;
    private static final double _motorcycleDayCost = 4000.0;
    private static final double _maxEngineCapacity = 500.0;

    public ParkedVehicle() {
    }

    public ParkedVehicle(String typeVehicle, String idVehicle) {
        super(typeVehicle, idVehicle);
    }

    public ParkedVehicle(String typeVehicle, String idVehicle, double engineCapacity) {
        super(typeVehicle, idVehicle, engineCapacity);
    }

    public ParkedVehicle(String idVehicle) {
        super(idVehicle);
    }

    public ParkedVehicle registerVehicleEntry() {
        this.setEntryDate(getInstanceOfUtil().getCurrentDate());
        this.setStatus("PARKED");
        this.setExitDate(null);
        ParkedVehiclesRepository repository = getInstanceOfParkedVehiclesRepository();
        return repository.insertParkedVehicle(this);
    }

    public ParkedVehicle searchVehicleByIdVehicleAndStatusParked() {
        ParkedVehiclesRepository repository = getInstanceOfParkedVehiclesRepository();
        return repository.searchParkedVehicleByIdVehicleAndStatusParked(this.getIdVehicle());
    }

    public List<ParkedVehicle> fetchAllVehiclesParked() {
        ParkedVehiclesRepository repository = getInstanceOfParkedVehiclesRepository();
        return repository.fetchAllVehiclesParked();

    }

    public ParkedVehicle registerVehicleExit() {
        this.setStatus("PAID");
        this.setExitDate(getInstanceOfUtil().getCurrentDate());
        this.setPaidValue(calculateTotalToPay());

        ParkedVehiclesRepository repository = getInstanceOfParkedVehiclesRepository();
        return repository.updateParkedVehicle(this);
    }

    protected Double calculateTotalToPay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        ParkedVehicleUtil util = getInstanceOfUtil();
        try {
            Date exitDate = format.parse(this.getExitDate());
            Date entryDate = format.parse(this.getEntryDate());
            long hours = (exitDate.getTime() - entryDate.getTime())/(60*60*1000);
            if (this.getTypeVehicle().equals("C")) {
                return util.calculateTotalToPayVehicle(hours, _carHourCost, _carDayCost);
            }else if(this.getTypeVehicle().equals("M")) {
                Double toPay = util.calculateTotalToPayVehicle(hours, _motorcycleHourCost, _motorcycleDayCost);
                if (this.getEngineCapacity() > _maxEngineCapacity) {
                    toPay = toPay + 2000;
                }
                return toPay;
            }
        }catch (ParseException pe) {
            System.out.println(pe);
        }
        return -1.0;
    }

    public boolean validateTypeVehicle(String typeVehicle) {
        if (typeVehicle.equals("C") || typeVehicle.equals("M")) {
            return true;
        }
        return false;
    }

    public boolean validateNumberOfVehiclesParkedCurrently() {
        ParkedVehiclesRepository repository = getInstanceOfParkedVehiclesRepository();
        List<ParkedVehicle> list = repository.getParkedVehiclesByTypeAndStatusParked(this.getTypeVehicle());
        if (this.getTypeVehicle().equals("C") && list.size() >= _numberOfCarsAllowed) {
            return false;
        }
        if (this.getTypeVehicle().equals("M") && list.size() >= _numberOfMotorcyclesAllowed) {
            return false;
        }

        return true;
    }

    public boolean startIdVehicleWithDefinedLetter() {
        return this.getIdVehicle().startsWith(_idThatStartWithLetter);
    }

    public boolean isTodayADayToValidate() {
        Calendar calendar = getInstanceOfCalendar();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        for (int i=0; i < _daysOfTheWeekToValidate.length; i++) {
            if(day == _daysOfTheWeekToValidate[i]) {
                return true;
            }
        }
        return false;
    }

    protected ParkedVehiclesRepository getInstanceOfParkedVehiclesRepository() {
        return new ParkedVehiclesRepository();
    }

    protected Calendar getInstanceOfCalendar() {
        return Calendar.getInstance();
    }

    protected ParkedVehicleUtil getInstanceOfUtil() {
        return new ParkedVehicleUtil();
    }
}
