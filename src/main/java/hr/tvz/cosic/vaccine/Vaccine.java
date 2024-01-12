package hr.tvz.cosic.vaccine;

import hr.tvz.cosic.sideeffect.SideEffect;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity

public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "research_name")
    private String researchName;

    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Column(name = "numberOfShots")
    private Integer numberOfShots;

    @Column(name = "quantity")
    private Integer quantityInStorage;

    @Column(name = "vaccine_type")
    private VaccineType vaccineType;

    @ManyToMany(targetEntity = SideEffect.class)
    @JoinTable(
            name = "vaccine_sideEffect",
            joinColumns = @JoinColumn(name = "vaccine_id"),
            inverseJoinColumns = @JoinColumn(name = "sideEffect_id")
    )
    private List<SideEffect> sideEffects = new ArrayList<>();

    protected Vaccine() {

    }

    public enum VaccineType {
        RNA, VIRALNI_VEKTOR
    }

    public Vaccine(Long id, String researchName, String manufacturerName, Integer numberOfShots, Integer quantityInStorage, VaccineType vaccineType){
        this.id = id;
        this.researchName = researchName;
        this.manufacturerName = manufacturerName;
        this.vaccineType = vaccineType;
        this.numberOfShots = numberOfShots;
        this.quantityInStorage = quantityInStorage;
    }

    public Vaccine(String researchName, String manufacturerName, Integer numberOfShots, Integer quantityInStorage, VaccineType vaccineType){
        this.researchName = researchName;
        this.manufacturerName = manufacturerName;
        this.vaccineType = vaccineType;
        this.numberOfShots = numberOfShots;
        this.quantityInStorage = quantityInStorage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResearchName() {
        return researchName;
    }

    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Integer getNumberOfShots() {
        return numberOfShots;
    }

    public void setNumberOfShots(Integer numberOfShots) {
        this.numberOfShots = numberOfShots;
    }

    public Integer getQuantityInStorage() {
        return quantityInStorage;
    }

    public void setQuantityInStorage(Integer quantityInStorage) {
        this.quantityInStorage = quantityInStorage;
    }

    public List<SideEffect> getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(List<SideEffect> sideEffects) {
        this.sideEffects = sideEffects;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccine vaccine = (Vaccine) o;
        return id.equals(vaccine.id) && researchName.equals(vaccine.researchName) && manufacturerName.equals(vaccine.manufacturerName) && numberOfShots.equals(vaccine.numberOfShots) && quantityInStorage.equals(vaccine.quantityInStorage) && vaccineType == vaccine.vaccineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, researchName, manufacturerName, numberOfShots, quantityInStorage, vaccineType);
    }
}
