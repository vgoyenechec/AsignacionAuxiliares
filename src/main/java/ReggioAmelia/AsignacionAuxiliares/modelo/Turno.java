package ReggioAmelia.AsignacionAuxiliares.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "taux_turno")
public class Turno {
    @Id
    @Column(name = "taux_turno_id")
    private LocalDate turnoId;
}
