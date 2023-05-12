package ReggioAmelia.AsignacionAuxiliares.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "taux_turno_asignado")
public class TurnoAsignado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taux_asignacion_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "taux_auxiliar_id")
    private Auxiliar docenteAuxiliar;

    @ManyToOne
    @JoinColumn(name = "taux_turno_id")
    private Turno turno;
}
