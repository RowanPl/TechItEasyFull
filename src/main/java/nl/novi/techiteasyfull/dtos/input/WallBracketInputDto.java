package nl.novi.techiteasyfull.dtos.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WallBracketInputDto {

        private Long id;
        private String size;
        private String name;
        private Double price;
        private Boolean adjustable;


}
