package parkingwatcher.model;

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


    public ParkedVehicle(String typeVehicle, String idVehicle) {
        super(typeVehicle, idVehicle);
    }

    public ParkedVehicle(String idVehicle) {
        super(idVehicle);
    }

    public ParkedVehicle registerVehicleEntry() {
        this.setEntryDate(getCurrentDate());
        this.setStatus("PARKED");
        this.setExitDate(null);
        ParkedVehiclesRepository repository = getInstanceOfParkedVehiclesRepository();
        repository.insertParkedVehicle(this);

        return this;
    }

    public ParkedVehicle searchVehicleByIdVehicle() {
        ParkedVehiclesRepository repository = getInstanceOfParkedVehiclesRepository();
        return repository.searchParkedVehicleByIdVehicle(this.getIdVehicle());
    }

    public ParkedVehicle registerVehicleExit() {
        this.setStatus("PAID");
        this.setExitDate(getCurrentDate());
        this.setPaidValue(calculateTotalToPay());

        ParkedVehiclesRepository repository = getInstanceOfParkedVehiclesRepository();
        return repository.updateParkedVehicle(this);
    }

    protected Double calculateTotalToPay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date exitDate = format.parse(this.getExitDate());
            Date entryDate = format.parse(this.getEntryDate());
            long hours = (exitDate.getTime() - entryDate.getTime())/(60*60*1000);
            if (this.getTypeVehicle().equals("C")) {
                return calculateTotalToPayVehicle(hours, _carHourCost, _carDayCost);
            }else if(this.getTypeVehicle().equals("M")) {
                return calculateTotalToPayVehicle(hours, _motorcycleHourCost, _motorcycleDayCost);
            }
        }catch (ParseException pe) {
            System.out.println(pe);
        }
        return -1.0;
    }

    protected double calculateTotalToPayVehicle(long hours, double vehicleHourCost, double vehicleDayCost) {
        if (hours < 9) {
            return hours * vehicleHourCost;
        } else {
            double days = hours / 24.0;

            if (days <= 1) {
                return vehicleDayCost;
            } else {
                int daysToPay = (int) days;
                double hoursToPay = getHoursToPay(days, daysToPay);
                if (hoursToPay >= 9) {
                    daysToPay ++;
                    hoursToPay = 0;
                }
                return (daysToPay * vehicleDayCost) + (hoursToPay * vehicleHourCost);
            }
        }
    }

    protected double getHoursToPay(double days, int daysToPay) {
        double hoursToPay = days - daysToPay;
        hoursToPay = hoursToPay * 24;

        return hoursToPay;
    }

    protected String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        TimeZone timeZone = calendar.getTimeZone();
        format.setTimeZone(timeZone);
        Date today = new Date();
        String strToday = format.format(today);

        return strToday;
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

}
