package nl.novi.techiteasyfull.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "televisions")
public class Television {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String brand;
    private String name;
    private Double price;
    private Integer originalStock;
    private Integer sold;
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


    // One-to-one relationship with RemoteController
    // One RemoteController can be used for one Television
    @OneToOne
    private RemoteController remoteController;

    // many-to-many relationship with WallBracket
    // Many Televisions can be used with many WallBrackets
    @ManyToMany
    @JoinTable(
            name = "television_wall_bracket",
            joinColumns = @JoinColumn(name = "television_id"),
            inverseJoinColumns = @JoinColumn(name = "wall_bracket_id"))
    private List<WallBracket> wallBrackets;


    // Many-to-one relationship with CIModule
    // Many Televisions can be used with one CIModule
    @ManyToOne(fetch = FetchType.EAGER)
    private CIModule ciModule;


}
