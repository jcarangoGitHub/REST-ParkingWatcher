package parkingwatcher.entities;

public class EParkedVehicle {

    private String typeVehicle;
    private final String idVehicle;
    private String entryDate;
    private String exitDate;
    private String status;
    private Double paidValue;
    private double engineCapacity;

    public EParkedVehicle(String idVehicle) {
        this.idVehicle = idVehicle;
    }

    public EParkedVehicle(String typeVehicle, String idVehicle) {
        this.typeVehicle = typeVehicle;
        this.idVehicle = idVehicle;
    }

    public EParkedVehicle(String typeVehicle, String idVehicle, double engineCapacity) {
        this.typeVehicle = typeVehicle;
        this.idVehicle = idVehicle;
        this.engineCapacity = engineCapacity;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public String getIdVehicle() {
        return idVehicle;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
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

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
}