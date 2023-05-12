package ReggioAmelia.AsignacionAuxiliares.repositorio;


import ReggioAmelia.AsignacionAuxiliares.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TurnoAsignadoRepo extends JpaRepository<TurnoAsignado, Date> {

    TurnoAsignado save(TurnoAsignado entity);

    @Modifying
    @Query("UPDATE TurnoAsignado t SET t.docenteAuxiliar = :docenteAuxiliar WHERE t.id = :id")
    void updateDocenteAuxiliarById(Long id, Auxiliar docenteAuxiliar);
}
