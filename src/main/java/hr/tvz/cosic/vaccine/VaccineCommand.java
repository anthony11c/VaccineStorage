package hr.tvz.cosic.vaccine;

import javax.validation.constraints.*;

public class VaccineCommand {

    @NotBlank(message = "ResearchVaccineName is not allowed to be empty!")
    private String researchName;

    @NotBlank(message = "ManufacturerCompany is not allowed to be empty!")
    private String manufacturerName;

    @NotNull(message = "Number of dosages have to be entered!")
    @Positive(message = "Dosage isn't allowed to be a negative number or zero!")
    @Max(message = "The dosage isn't allowed to be greater then 2!", value= 2)
    private Integer numberOfShots;

    @PositiveOrZero(message = "Quantity in storage has to be zero or greater!")
    @NotNull(message = "Quantity in storage has to be entered as a number!")
    private Integer quantityInStorage;

    @NotNull(message = "Vaccine type can't be empty!")
    private Vaccine.VaccineType vaccineType;

    public String getResearchName() {
        return researchName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public Integer getNumberOfShots() {
        return numberOfShots;
    }

    public Integer getQuantityInStorage() {
        return quantityInStorage;
    }

    public Vaccine.VaccineType getVaccineType() {
        return vaccineType;
    }

    public VaccineCommand(@NotBlank(message = "ResearchVaccineName is not allowed to be empty!") String researchName, @NotBlank(message = "ManufacturerCompany is not allowed to be empty!") String manufacturerName, @NotNull(message = "Number of dosages have to be entered!") @Positive(message = "Dosage isn't allowed to be a negative number or zero!") @Max(message = "The dosage isn't allowed to be greater then 2!", value = 2) Integer numberOfShots, @PositiveOrZero(message = "Quantity in storage has to be zero or greater!") @NotNull(message = "Quantity in storage has to be entered as a number!") Integer quantityInStorage, Vaccine.VaccineType vaccineType) {
        this.researchName = researchName;
        this.manufacturerName = manufacturerName;
        this.numberOfShots = numberOfShots;
        this.quantityInStorage = quantityInStorage;
        this.vaccineType = vaccineType;
    }
}
