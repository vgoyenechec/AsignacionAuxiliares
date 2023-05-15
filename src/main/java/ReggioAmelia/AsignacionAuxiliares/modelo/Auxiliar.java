package ReggioAmelia.AsignacionAuxiliares.modelo;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "taux_docente_auxiliar")
public class Auxiliar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taux_auxiliar_id")
    private Long id;

    @Column(name = "taux_cedula", unique = true, nullable = false)
    private String cedula;

    @Column(name = "taux_nombre", nullable = false)
    private String nombre;

    @Column(name = "taux_cargo", nullable = false)
    private String cargo;

    @Column(name = "taux_celular", nullable = false)
    private String celular;

    @Column(name = "taux_sede", nullable = false)
    private String sede;

//    @JsonIgnore
    @OneToMany(mappedBy = "docenteAuxiliar", cascade = CascadeType.REMOVE)
    private List<TurnoAsignado> turnoAsignados;
}
