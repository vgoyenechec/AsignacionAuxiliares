package ReggioAmelia.AsignacionAuxiliares.repositorio;


import ReggioAmelia.AsignacionAuxiliares.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TurnoAsignadoRepo extends JpaRepository<TurnoAsignado, Date> {

    TurnoAsignado save(TurnoAsignado entity);

    @Query("SELECT t FROM TurnoAsignado t WHERE t.turno.turnoId > CURRENT_DATE OR t.turno.turnoId IS NULL")
    List<TurnoAsignado> findTurnosPendientes();

    @Modifying
    @Query("DELETE FROM TurnoAsignado t WHERE t.turno.turnoId > CURRENT_DATE OR t.turno.turnoId IS NULL")
    void deleteTurnosPendientes();

    @Query("SELECT t FROM TurnoAsignado t WHERE t.turno.turnoId <= CURRENT_DATE ORDER BY t.turno.turnoId DESC LIMIT 1")
    TurnoAsignado findUltimoTurnoTerminado();

    @Modifying
    @Query("UPDATE TurnoAsignado t SET t.docenteAuxiliar = :docenteAuxiliar WHERE t.id = :id")
    void updateDocenteAuxiliarById(Long id, Auxiliar docenteAuxiliar);
}
