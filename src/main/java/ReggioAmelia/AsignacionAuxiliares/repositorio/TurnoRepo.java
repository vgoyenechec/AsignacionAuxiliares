package ReggioAmelia.AsignacionAuxiliares.repositorio;


import ReggioAmelia.AsignacionAuxiliares.modelo.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TurnoRepo extends JpaRepository<Turno, LocalDate> {

    Turno save(Turno turno);

    void deleteById(LocalDate id);

    void deleteAll();

    List<Turno> findAllByOrderByTurnoIdAsc();

}
