package hr.tvz.cosic.sideeffect;

import hr.tvz.cosic.vaccine.Vaccine;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "SideEffect")
public class SideEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "kratki_opis")
    private String kratkiOpis;

    @Column(name = "dugi_opis")
    private String dugiOpis;

    @Column(name = "ucestalost")
    private Integer ucestalost;

    @ManyToMany(targetEntity = Vaccine.class, mappedBy = "sideEffects")
    private List<Vaccine> vaccine = new ArrayList<>();

    public SideEffect(Long id, String kratkiOpis, String dugiOpis, Integer ucestalost) {
        this.id = id;
        this.kratkiOpis = kratkiOpis;
        this.dugiOpis = dugiOpis;
        this.ucestalost = ucestalost;
    }

    public SideEffect(String kratkiOpis, String dugiOpis, Integer ucestalost) {
        this.kratkiOpis = kratkiOpis;
        this.dugiOpis = dugiOpis;
        this.ucestalost = ucestalost;
    }

    public SideEffect() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKratkiOpis() {
        return kratkiOpis;
    }

    public void setKratkiOpis(String kratkiOpis) {
        this.kratkiOpis = kratkiOpis;
    }

    public String getDugiOpis() {
        return dugiOpis;
    }

    public void setDugiOpis(String dugiOpis) {
        this.dugiOpis = dugiOpis;
    }

    public Integer getUcestalost() {
        return ucestalost;
    }

    public void setUcestalost(Integer ucestalost) {
        this.ucestalost = ucestalost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SideEffect that = (SideEffect) o;
        return id.equals(that.id) && kratkiOpis.equals(that.kratkiOpis) && dugiOpis.equals(that.dugiOpis) && ucestalost.equals(that.ucestalost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kratkiOpis, dugiOpis, ucestalost);
    }

    @Override
    public String toString() {
        return "SideEffect{" +
                "id=" + id +
                ", kratkiOpis='" + kratkiOpis + '\'' +
                ", dugiOpis='" + dugiOpis + '\'' +
                ", ucestalost=" + ucestalost +
                '}';
    }
}
