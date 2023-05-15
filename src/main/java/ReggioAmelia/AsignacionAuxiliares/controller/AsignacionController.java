package ReggioAmelia.AsignacionAuxiliares.controller;

import ReggioAmelia.AsignacionAuxiliares.dto.AuxiliarDTO;
import ReggioAmelia.AsignacionAuxiliares.dto.EliminarAuxReqDTO;
import ReggioAmelia.AsignacionAuxiliares.dto.TurnoAsignadoDTO;
import ReggioAmelia.AsignacionAuxiliares.dto.TurnoDTO;
import ReggioAmelia.AsignacionAuxiliares.modelo.Auxiliar;
import ReggioAmelia.AsignacionAuxiliares.modelo.Turno;
import ReggioAmelia.AsignacionAuxiliares.modelo.TurnoAsignado;
import ReggioAmelia.AsignacionAuxiliares.usecase.AuxiliarUsecase;
import ReggioAmelia.AsignacionAuxiliares.usecase.TurnoAsignadoUsecase;
import ReggioAmelia.AsignacionAuxiliares.usecase.TurnoUsecase;
import ReggioAmelia.AsignacionAuxiliares.util.CalcularDiasHabiles;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
public class AsignacionController {

    @Autowired
    private AuxiliarUsecase auxiliarUsecase;
    @Autowired
    private TurnoUsecase turnoUsecase;
    @Autowired
    private TurnoAsignadoUsecase turnoAsignadoUsecase;

    @Autowired
    private CalcularDiasHabiles calcularDiasHabiles;

    @PostMapping("/agregar-auxiliar")
    public Auxiliar agregarAuxiliar(@RequestBody AuxiliarDTO auxiliar){
        return auxiliarUsecase.addAuxiliar(auxiliar);
    }

    @PostMapping("/eliminar-auxiliar")
    public void eliminarAuxiliar(@RequestBody EliminarAuxReqDTO reqDTO){
        auxiliarUsecase.deleteAuxiliar(reqDTO.cedula());
    }

    @GetMapping("/listado-auxiliares")
    public List<Auxiliar> listaAuxiliares(){
        return auxiliarUsecase.obtenerListadoAuxiliares();
    }

    @PostMapping("/agregar-turno")
    public Turno agregarTurno(@RequestBody TurnoDTO turno){
        return turnoUsecase.addTurno(turno);
    }

    @PostMapping("/agregar-turno-lista")
    public String agregarTurnoLista(@RequestBody List<TurnoDTO> turnos){
        turnoUsecase.addTurnoList(turnos);
        return "Turnos agregados exitosamente";
    }

    @PostMapping("/eliminar-turno")
    public String eliminarTurno(@RequestBody TurnoDTO turno){
        turnoUsecase.deleteTurno(turno);
        return "Turno eliminado exitosamente";
    }

    @PostMapping("/eliminar-turno-lista")
    public String eliminarTurnoLista(){
        turnoUsecase.deleteAllTurnos();
        return "Todos los turnos han sido eliminados exitosamente";
    }

    @GetMapping("/listado-turnos")
    public List<Turno> listaTurnoes(){
        return turnoUsecase.obtenerListadoTurnos();
    }

    @PostMapping("/asignar-turnos")
    public List<TurnoAsignadoDTO> asignarTurnos(){
        return turnoAsignadoUsecase.asignarTurnos(0, true);
    }

    @PostMapping("/reasignar-turnos")
    public List<TurnoAsignadoDTO> reasignarTurnos(){
        return turnoAsignadoUsecase.reasignarTurnos();
    }

    @GetMapping("/turnos-asignados")
    public List<TurnoAsignadoDTO> obtenerTurnosAsignados(){
        return turnoAsignadoUsecase.obtenerListadoTurnosAsignados();
    }

    @GetMapping("/turnos-pendientes")
    public List<TurnoAsignadoDTO> obtenerTurnosPendientes(){
        return turnoAsignadoUsecase.obtenerTurnosPendientes();
    }

    @GetMapping("/obtener-sabados")
    public List<LocalDate> obtenerSabados(){
        CalcularDiasHabiles calcu = new CalcularDiasHabiles();
        return calcu.calcularSabadosHabiles();
    }

}
