package hr.tvz.cosic.service;

import hr.tvz.cosic.vaccine.Vaccine;

public class VaccineDTO {

    private final String researchName;
    private final String manufacturerName;
    private final Vaccine.VaccineType type;
    private final Integer numberOfShots;
    private final Integer availableDoses;


    public VaccineDTO(String researchName, String manufacturerName, Vaccine.VaccineType type, Integer numberOfShots, Integer availableDoses) {
        this.researchName = researchName;
        this.manufacturerName = manufacturerName;
        this.type = type;
        this.numberOfShots = numberOfShots;
        this.availableDoses = availableDoses;
    }

    public String getResearchName() {
        return researchName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public Vaccine.VaccineType getType() {
        return type;
    }

    public Integer getNumberOfShots() {
        return numberOfShots;
    }

    public Integer getAvailableDoses() {
        return availableDoses;
    }

    @Override
    public String toString() {
        return "VaccineDTO{" +
                "researchName='" + researchName + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", type='" + type + '\'' +
                ", numberOfShots=" + numberOfShots +
                ", availableDoses=" + availableDoses +
                '}';
    }
}
