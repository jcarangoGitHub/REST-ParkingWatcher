package parkingwatcher.util;

import org.junit.Assert;
import org.junit.Test;
import parkingwatcher.model.ParkedVehicles;

public class CreatorTest {
    @Test
    public void createNewParkedVehicleToRegister_EntryDateMustNotBeNull() {
        ParkedVehicles pv = Creator.createNewParkedVehicleToRegister("M", "abc987");

        Assert.assertNotNull(pv.getEntryDate());
    }

    @Test
    public void createNewParkedVehicleToRegister_StatusMustBePARKED() {
        ParkedVehicles pv = Creator.createNewParkedVehicleToRegister("M", "abc987");

        Assert.assertEquals(pv.getStatus(), "PARKED");
    }

}
