package nl.novi.techiteasyfull.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "ci_modules")
public class CIModule {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String type;
    private Double price;

    @OneToMany(mappedBy = "ciModule")
    private List<Television> televisions = new ArrayList<>();

}
