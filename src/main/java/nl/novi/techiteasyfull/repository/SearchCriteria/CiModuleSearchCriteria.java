package nl.novi.techiteasyfull.repository.SearchCriteria;


import lombok.Data;

@Data
public class CiModuleSearchCriteria {

    public Long id;
    public String name;
    public String type;
    public Double price;

    public CiModuleSearchCriteria() {
    }

}
