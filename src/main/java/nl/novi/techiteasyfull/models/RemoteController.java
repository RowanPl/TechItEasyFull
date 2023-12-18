package nl.novi.techiteasyfull.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "remote_controllers")
public class RemoteController {
    @Id
    @GeneratedValue
    private Long id;

    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;

    @OneToOne(mappedBy = "remoteController")
    private Television television;

}
