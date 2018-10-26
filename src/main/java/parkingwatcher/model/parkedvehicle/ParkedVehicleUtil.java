package parkingwatcher.model.parkedvehicle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ParkedVehicleUtil {

    public ParkedVehicleUtil() {
    }

    public double calculateTotalToPayVehicle(long hours, double vehicleHourCost, double vehicleDayCost) {
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

    public String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        TimeZone timeZone = calendar.getTimeZone();
        format.setTimeZone(timeZone);
        Date today = new Date();
        String strToday = format.format(today);

        return strToday;
    }
}
