package parkingwatcher.model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import parkingwatcher.repository.ParkedVehiclesRepository;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.*;

public class ParkedVehicleTest {

    @Test
    public void registerVehicleEntry_EntryDateMustNotBeNull() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC123"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);

        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.insertParkedVehicle(Mockito.any())).thenReturn(parkedVehicleSpy);

        ParkedVehicle ans = parkedVehicleSpy.registerVehicleEntry();

        Assert.assertNotNull(ans.getEntryDate());
    }

    @Test
    public void registerVehicleEntry_ExitDateMustBeNull() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC123"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);

        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.insertParkedVehicle(Mockito.any())).thenReturn(parkedVehicleSpy);

        ParkedVehicle ans = parkedVehicleSpy.registerVehicleEntry();

        Assert.assertNull(ans.getExitDate());
    }

    @Test
    public void registerVehicleEntry_MustSetStatusPARKED() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC123"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);

        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.insertParkedVehicle(Mockito.any())).thenReturn(parkedVehicleSpy);

        ParkedVehicle ans = parkedVehicleSpy.registerVehicleEntry();

        Assert.assertEquals(ans.getStatus(), "PARKED");
    }

    @Test
    public void registerVehicleEntry_MustAlwaysCallOnceInsertParkedVehicle() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC123"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);

        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.insertParkedVehicle(Mockito.any())).thenReturn(parkedVehicleSpy);

        parkedVehicleSpy.registerVehicleEntry();

        verify(parkedVehiclesRepositoryMock, times(1)).insertParkedVehicle(Mockito.any());
    }

    @Test
    public void searchVehicleByIdVehicle_MustCallOnceSearchParkedVehicleByIdVehicle() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC321"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);

        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.searchParkedVehicleByIdVehicle(Mockito.any())).thenReturn(parkedVehicleSpy);


        parkedVehicleSpy.searchVehicleByIdVehicle();

        verify(parkedVehiclesRepositoryMock, times(1)).searchParkedVehicleByIdVehicle(Mockito.anyString());
    }


    @Test
    public void registerVehicleExit_MustSetStatusPAID() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC321"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);
        String dateToReturn = "2018-10-25T13:48:00";

        when(parkedVehicleSpy.getEntryDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.getExitDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.getCurrentDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.calculateTotalToPay()).thenReturn(new Double(1500));
        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.updateParkedVehicle(Mockito.any())).thenReturn(parkedVehicleSpy);

        ParkedVehicle ans = parkedVehicleSpy.registerVehicleExit();

        Assert.assertEquals(ans.getStatus(), "PAID");
    }

    @Test
    public void registerVehicleExit_MustCallGetCurrentDateAndExitDateMustNotBeNull() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC321"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);
        String dateToReturn = "2018-10-25T13:48:00";

        when(parkedVehicleSpy.getEntryDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.getExitDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.getCurrentDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.calculateTotalToPay()).thenReturn(new Double(1500));
        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.updateParkedVehicle(Mockito.any())).thenReturn(parkedVehicleSpy);

        ParkedVehicle ans = parkedVehicleSpy.registerVehicleExit();

        verify(parkedVehicleSpy, times(1)).getCurrentDate();
        Assert.assertEquals(ans.getExitDate(), dateToReturn);
    }

    @Test
    public void registerVehicleExit_MustCallCalculateTotalToPayAndPaidValueMustNotBeNull() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC321"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);
        String dateToReturn = "2018-10-25T13:48:00";
        Double valueToReturn = new Double(1500);

        when(parkedVehicleSpy.getEntryDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.getExitDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.getCurrentDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.calculateTotalToPay()).thenReturn(new Double(valueToReturn));
        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehicleSpy.getEntryDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.getExitDate()).thenReturn(dateToReturn);
        when(parkedVehiclesRepositoryMock.updateParkedVehicle(Mockito.any())).thenReturn(parkedVehicleSpy);

        ParkedVehicle ans = parkedVehicleSpy.registerVehicleExit();

        verify(parkedVehicleSpy, times(2)).calculateTotalToPay();
        Assert.assertEquals(ans.getPaidValue(), valueToReturn);
    }

    @Test
    public void registerVehicleExit_MustAlwaysCallUpdateParkedVehicle() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC321"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);
        String dateToReturn = "2018-10-25T13:48:00";
        Double valueToReturn = new Double(1500);

        when(parkedVehicleSpy.getEntryDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.getExitDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.getCurrentDate()).thenReturn(dateToReturn);
        when(parkedVehicleSpy.calculateTotalToPay()).thenReturn(new Double(valueToReturn));
        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.updateParkedVehicle(Mockito.any())).thenReturn(parkedVehicleSpy);

        parkedVehicleSpy.registerVehicleExit();

        verify(parkedVehiclesRepositoryMock, times(1)).updateParkedVehicle(Mockito.any());
    }

    @Test
    public void getCurrentDate_MustNotReturnNull() {
        ParkedVehicle parkedVehicle = new ParkedVehicle("ABC123");
        String ans = parkedVehicle.getCurrentDate();
        Assert.assertNotNull(ans);
    }

    @Test
    public void getCurrentDate_MustReturnDateFormatted() {
        ParkedVehicle parkedVehicle = new ParkedVehicle("ABC123");

        String ans = parkedVehicle.getCurrentDate();

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

    @Test
    public void validateTypeVehicle_MustReturnTrueWhenTypeVehicleIsC() {
        ParkedVehicle parkedVehicle = new ParkedVehicle("ABC123");

        boolean ans = parkedVehicle.validateTypeVehicle("C");

        Assert.assertTrue(ans);
    }

    @Test
    public void validateTypeVehicle_MustReturnTrueWhenTypeVehicleIsM() {
        ParkedVehicle parkedVehicle = new ParkedVehicle("ABC123");

        boolean ans = parkedVehicle.validateTypeVehicle("M");

        Assert.assertTrue(ans);
    }

    @Test
    public void validateTypeVehicle_MustReturnFalseWhenTypeVehicleIsDifferentOfCAndM() {
        ParkedVehicle parkedVehicle = new ParkedVehicle("ABC123");

        boolean ans = parkedVehicle.validateTypeVehicle("X");

        Assert.assertFalse(ans);
    }

    @Test
    public void validateNumberOfVehiclesParkedCurrently_MustReturnTrueWhenTypeVehicleIsCAndListSizeIsLessThan20() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC321"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);
        List<ParkedVehicle> listMock = mock(List.class);

        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.getParkedVehiclesByTypeAndStatusParked(Mockito.any())).thenReturn(listMock);
        when(listMock.size()).thenReturn(19);

        boolean ans = parkedVehicleSpy.validateNumberOfVehiclesParkedCurrently();

        Assert.assertTrue(ans);
    }

    @Test
    public void validateNumberOfVehiclesParkedCurrently_MustReturnFalseWhenTypeVehicleIsCAndListSizeIsGraterThan20() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC321"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);
        List<ParkedVehicle> listMock = mock(List.class);

        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.getParkedVehiclesByTypeAndStatusParked(Mockito.any())).thenReturn(listMock);
        when(listMock.size()).thenReturn(21);

        boolean ans = parkedVehicleSpy.validateNumberOfVehiclesParkedCurrently();

        Assert.assertFalse(ans);
    }

    @Test
    public void validateNumberOfVehiclesParkedCurrently_MustReturnTrueWhenTypeVehicleIsMAndListSizeIsLessThan10() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("M", "ABC321"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);
        List<ParkedVehicle> listMock = mock(List.class);

        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.getParkedVehiclesByTypeAndStatusParked(Mockito.any())).thenReturn(listMock);
        when(listMock.size()).thenReturn(9);

        boolean ans = parkedVehicleSpy.validateNumberOfVehiclesParkedCurrently();

        Assert.assertTrue(ans);
    }

    @Test
    public void validateNumberOfVehiclesParkedCurrently_MustReturnFalseWhenTypeVehicleIsMAndListSizeIsGraterThan20() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("M", "ABC321"));
        ParkedVehiclesRepository parkedVehiclesRepositoryMock = mock(ParkedVehiclesRepository.class);
        List<ParkedVehicle> listMock = mock(List.class);

        when(parkedVehicleSpy.getInstanceOfParkedVehiclesRepository()).thenReturn(parkedVehiclesRepositoryMock);
        when(parkedVehiclesRepositoryMock.getParkedVehiclesByTypeAndStatusParked(Mockito.any())).thenReturn(listMock);
        when(listMock.size()).thenReturn(11);

        boolean ans = parkedVehicleSpy.validateNumberOfVehiclesParkedCurrently();

        Assert.assertFalse(ans);
    }

    @Test
    public void startIdVehicleWithDefinedLetter_MustReturnTrueWhenIdVehicleIsABC123() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("M", "ABC321"));

        when(parkedVehicleSpy.isTodayADayToValidate()).thenReturn(true);

        boolean ans = parkedVehicleSpy.startIdVehicleWithDefinedLetter();

        Assert.assertTrue(ans);
    }

    @Test
    public void startIdVehicleWithDefinedLetter_MustReturnFalseWhenIdVehicleIsBCA123() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("M", "BCA321"));

        when(parkedVehicleSpy.isTodayADayToValidate()).thenReturn(true);

        boolean ans = parkedVehicleSpy.startIdVehicleWithDefinedLetter();

        Assert.assertFalse(ans);
    }

    @Test
    public void isTodayADayToValidate_MustReturnTrueWhenDayOfWeekIs2() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("M", "ABC321"));
        Calendar calendar = spy(new GregorianCalendar(2018,9,1));

        when(parkedVehicleSpy.getInstanceOfCalendar()).thenReturn(calendar);

        boolean ans = parkedVehicleSpy.isTodayADayToValidate();

        Assert.assertTrue(ans);
    }

    @Test
    public void isTodayADayToValidate_MustReturnTrueWhenDayOfWeekIs1() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("M", "ABC321"));
        Calendar calendar = spy(new GregorianCalendar(2018,8,30));

        when(parkedVehicleSpy.getInstanceOfCalendar()).thenReturn(calendar);

        boolean ans = parkedVehicleSpy.isTodayADayToValidate();

        Assert.assertTrue(ans);
    }

    @Test
    public void isTodayADayToValidate_MustReturnFalseWhenDayOfWeekIs3() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("M", "ABC321"));
        Calendar calendar = spy(new GregorianCalendar(2018,9,2));

        when(parkedVehicleSpy.getInstanceOfCalendar()).thenReturn(calendar);

        boolean ans = parkedVehicleSpy.isTodayADayToValidate();

        Assert.assertFalse(ans);
    }

    @Test
    public void calculateTotalToPay_MustReturn32000WhenACarParked3DaysAnd18hours() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC321"));

        when(parkedVehicleSpy.getEntryDate()).thenReturn("2018-10-20T00:08:10");
        when(parkedVehicleSpy.getExitDate()).thenReturn("2018-10-23T18:08:10");

        double ans = parkedVehicleSpy.calculateTotalToPay();

        Assert.assertEquals(ans, 32000.0, 1);
    }

    @Test
    public void calculateTotalToPay_MustReturn11000WhenACarParked1DayAnd3hours() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("C", "ABC321"));

        when(parkedVehicleSpy.getEntryDate()).thenReturn("2018-10-20T00:08:10");
        when(parkedVehicleSpy.getExitDate()).thenReturn("2018-10-21T03:08:10");

        double ans = parkedVehicleSpy.calculateTotalToPay();

        Assert.assertEquals(ans, 11000, 1);
    }

    @Test
    public void calculateTotalToPay_MustReturn5500WhenAMotorcycleParked1DayAnd3hours() {
        ParkedVehicle parkedVehicleSpy = spy(new ParkedVehicle("M", "ABC321"));

        when(parkedVehicleSpy.getEntryDate()).thenReturn("2018-10-20T00:08:10");
        when(parkedVehicleSpy.getExitDate()).thenReturn("2018-10-21T03:08:10");

        double ans = parkedVehicleSpy.calculateTotalToPay();

        Assert.assertEquals(ans, 5500, 1);
    }
}
