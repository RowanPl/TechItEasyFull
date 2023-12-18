package nl.novi.techiteasyfull.dtos.input;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoteControllerInputDto {

    private Long id;
    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;
    private Long televisionId;
}
