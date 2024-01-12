package hr.tvz.cosic.service;

public class SideEffectDTO {

    private final String shortDescription;
    private final String description;
    private final Integer frequency;

    @Override
    public String toString() {
        return "SideEffectDTO{" +
                "shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", frequency=" + frequency +
                '}';
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public SideEffectDTO(String shortDescription, String description, Integer frequency) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.frequency = frequency;
    }


}
