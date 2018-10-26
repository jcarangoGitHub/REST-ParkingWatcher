package parkingwatcher.model.parkedvehicle;

import org.junit.Assert;
import org.junit.Test;

public class ParkedVehicleUtilTest {

    @Test
    public void getCurrentDate_MustNotReturnNull() {
        ParkedVehicleUtil util = new ParkedVehicleUtil();
        String ans = util.getCurrentDate();
        Assert.assertNotNull(ans);
    }

    @Test
    public void getCurrentDate_MustReturnDateFormatted() {
        ParkedVehicleUtil util = new ParkedVehicleUtil();
        String ans = util.getCurrentDate();

        String firstSeparator = ans.substring(4,5);
        String secondSeparator = ans.substring(7,8);
        String timeSeparator = ans.substring(10, 11);
        String hourSeparator = ans.substring(13,14);
        String minuteSeparator = ans.substring(16,17);

        boolean fsAssert = firstSeparator.equals("-");
        boolean ssAssert = secondSeparator.equals("-");
        boolean tsAssert =  timeSeparator.equals("T");
        boolean hsAssert = hourSeparator.equals(":");
        boolean msAssert = minuteSeparator.equals(":");

        boolean finalAssert = fsAssert && ssAssert && tsAssert && hsAssert && msAssert;

        Assert.assertTrue(finalAssert);
    }
}
