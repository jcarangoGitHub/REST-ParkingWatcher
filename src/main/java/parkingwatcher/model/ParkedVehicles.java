package parkingwatcher.model;

import java.util.Date;

public class ParkedVehicles {

    private final String typeVehicle;
    private final String idVehicle;
    private Date entryDate;
    private Date exitDate;
    private String status;
    private Double paidValue;

    public ParkedVehicles(String typeVehicle, String idVehicle) {
        this.typeVehicle = typeVehicle;
        this.idVehicle = idVehicle;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public String getIdVehicle() {
        return idVehicle;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(Double paidValue) {
        this.paidValue = paidValue;
    }
}