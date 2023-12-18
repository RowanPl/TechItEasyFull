package nl.novi.techiteasyfull.repository.SearchCriteria;

import lombok.Data;

@Data
public class RemoteControllerSearchCriteria {
    public Long id;
    public String type;
    public String brand;
    public String name;
    public Double price;
    public Integer originalStock;
    public Integer sold;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean bluetooth;

    public RemoteControllerSearchCriteria() {
    }
}
