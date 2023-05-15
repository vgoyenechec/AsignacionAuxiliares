package ReggioAmelia.AsignacionAuxiliares.usecase;

import ReggioAmelia.AsignacionAuxiliares.dto.TurnoDTO;
import ReggioAmelia.AsignacionAuxiliares.mapper.TurnoMapper;
import ReggioAmelia.AsignacionAuxiliares.modelo.Turno;
import ReggioAmelia.AsignacionAuxiliares.repositorio.TurnoRepo;
import ReggioAmelia.AsignacionAuxiliares.util.CalcularDiasHabiles;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
public class TurnoUsecase {

    @Autowired
    private TurnoRepo turnoRepo;
    private static final Gson gson = new Gson();

    public Turno addTurno(TurnoDTO turnoDTO){
        Turno turno = TurnoMapper.INSTANCE.toEntity(turnoDTO);
        turnoRepo.save(turno);
        log.info("Turno agregado: "+turno);
        return turno;
    }

    public void addTurnoList(List<TurnoDTO> turnosDto){
        List<Turno> turnos = TurnoMapper.INSTANCE.dtosToEntities(turnosDto);
        turnoRepo.saveAll(turnos);
    }

    public void deleteTurno(TurnoDTO turno){
        String fecha = turno.turnoId();
        log.info("Turno eliminado: "+ fecha);
        turnoRepo.deleteById(LocalDate.parse(fecha));
    }

    public void deleteAllTurnos(){
        turnoRepo.deleteAll();
    }

    public List<Turno> obtenerListadoTurnos(){
        return turnoRepo.findAllByOrderByTurnoIdAsc();
    }

    public List<Turno> obtenerListadoTurnosPendientes(){
        return turnoRepo.findTurnoPendientes();
    }
}
