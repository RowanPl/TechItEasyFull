package nl.novi.techiteasyfull.repository.SearchCriteria;

import lombok.Data;

@Data
public class TelevisionSearchCriteria {
    public Long id;
    public String type;
    public String brand;
    public String name;
    public Double price;
    public Integer originalStock;
    public Integer sold;
    public Double availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;

    public TelevisionSearchCriteria() {
    }
}
