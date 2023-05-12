package ReggioAmelia.AsignacionAuxiliares.repositorio;


import ReggioAmelia.AsignacionAuxiliares.modelo.Auxiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuxiliarRepo extends JpaRepository<Auxiliar,Long> {

    Auxiliar save(Auxiliar entity);
    Auxiliar findByCedula(String id);
    void deleteByCedula(String id);
    List<Auxiliar> findAllByOrderByNombreAsc();

}
