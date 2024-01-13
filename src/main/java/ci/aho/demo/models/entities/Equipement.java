package ci.aho.demo.models.entities;

import ci.aho.demo.models.enums.Etat;
import ci.aho.demo.models.enums.Site;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipements")
@Data @NoArgsConstructor
public class Equipement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String site;
    private String etat;

    public Equipement(String site, String etat){
        this.site = site;
        this.etat = etat;
    }
}
