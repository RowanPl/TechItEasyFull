package nl.novi.techiteasyfull.dtos.input;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import nl.novi.techiteasyfull.dtos.output.CIModuleDto;
import nl.novi.techiteasyfull.dtos.output.RemoteControllerDto;

@Getter
@Setter
public class TelevisionInputDto {
    private Long id;

    @NotEmpty
    private String type;
    @NotNull
    private String brand;
    private String name;
    private Double price;
    private Double availableSize;
    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Integer sold;

    private CIModuleDto ciModuleDto;
    private RemoteControllerDto remoteControllerDto;
}
