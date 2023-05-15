package ReggioAmelia.AsignacionAuxiliares.usecase;

import ReggioAmelia.AsignacionAuxiliares.dto.TurnoAsignadoDTO;
import ReggioAmelia.AsignacionAuxiliares.mapper.TurnoAsignadoMapper;
import ReggioAmelia.AsignacionAuxiliares.modelo.Auxiliar;
import ReggioAmelia.AsignacionAuxiliares.modelo.Turno;
import ReggioAmelia.AsignacionAuxiliares.modelo.TurnoAsignado;
import ReggioAmelia.AsignacionAuxiliares.repositorio.TurnoAsignadoRepo;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
public class TurnoAsignadoUsecase {

    @Autowired
    private TurnoAsignadoRepo turnoAsignadoRepo;
    @Autowired
    private AuxiliarUsecase auxiliarUsecase;
    @Autowired
    private TurnoUsecase turnoUsecase;
    private static final Gson gson = new Gson();

    public List<TurnoAsignadoDTO> asignarTurnos(int auxIni, boolean asignar) {
        List<Auxiliar> auxiliares = auxiliarUsecase.obtenerListadoAuxiliares();
        List<Turno> turnos = asignar ? turnoUsecase.obtenerListadoTurnos() : turnoUsecase.obtenerListadoTurnosPendientes();
        List<TurnoAsignado> asignados = new ArrayList<>();
        int auxCont = auxiliares.size();
        int turnoCont = turnos.size();

        for (int i = 0; i < turnoCont; i++) {
            Auxiliar auxiliar = auxiliares.get((i + auxIni) % auxCont);
            Turno turno = turnos.get(i);
            TurnoAsignado asignado = new TurnoAsignado();
            asignado.setTurno(turno);
            asignado.setDocenteAuxiliar(auxiliar);
            asignados.add(asignado);
        }
        return TurnoAsignadoMapper.INSTANCE.entitiesToDtos(turnoAsignadoRepo.saveAll(asignados));
    }

    private TurnoAsignadoDTO asignarTurno(TurnoAsignado turnoAsignado){
        turnoAsignadoRepo.save(turnoAsignado);
        TurnoAsignadoDTO dto = TurnoAsignadoMapper.INSTANCE.toDto(turnoAsignado);
        log.info("Turno asignado: "+gson.toJson(dto));
        return dto;
    }

    public void updateTurnoAsignado(Long id, Auxiliar auxiliar){
        turnoAsignadoRepo.updateDocenteAuxiliarById(id, auxiliar);
    }

    public List<TurnoAsignadoDTO> obtenerListadoTurnosAsignados(){
        return TurnoAsignadoMapper.INSTANCE.entitiesToDtos(turnoAsignadoRepo.findAll());
    }

    public List<TurnoAsignadoDTO> obtenerTurnosPendientes(){
        return TurnoAsignadoMapper.INSTANCE.entitiesToDtos(turnoAsignadoRepo.findTurnosPendientes());
    }

    public List<TurnoAsignadoDTO> reasignarTurnos(){
        TurnoAsignado ultimoTurno = turnoAsignadoRepo.findUltimoTurnoTerminado();
        Auxiliar ultimoAuxiliar = ultimoTurno.getDocenteAuxiliar();
        turnoAsignadoRepo.deleteTurnosPendientes();
        return asignarTurnos((ultimoAuxiliar.getId().intValue()), false);

    }


}
