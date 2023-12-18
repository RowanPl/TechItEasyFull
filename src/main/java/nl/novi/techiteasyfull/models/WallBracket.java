package nl.novi.techiteasyfull.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "wall_brackets")
public class WallBracket {

    @Id
    @GeneratedValue
    private Long id;

    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;

    @ManyToMany (mappedBy = "wallBrackets")
    private List<Television> televisions;
}
