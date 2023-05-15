package ReggioAmelia.AsignacionAuxiliares.usecase;

import ReggioAmelia.AsignacionAuxiliares.dto.AuxiliarDTO;
import ReggioAmelia.AsignacionAuxiliares.mapper.AuxiliarMapper;
import ReggioAmelia.AsignacionAuxiliares.modelo.Auxiliar;
import ReggioAmelia.AsignacionAuxiliares.repositorio.AuxiliarRepo;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@Transactional
public class AuxiliarUsecase {

    @Autowired
    private AuxiliarRepo auxiliarRepo;
    private static final Gson gson = new Gson();

    public Auxiliar addAuxiliar(AuxiliarDTO auxiliar){
        Auxiliar nuevoAuxiliar = AuxiliarMapper.INSTANCE.toEntity(auxiliar);
        auxiliarRepo.save(nuevoAuxiliar);
        log.info("Auxiliar agregado: "+nuevoAuxiliar);
        return nuevoAuxiliar;
    }

    public void deleteAuxiliar(String cedula){
        Auxiliar auxiliar = auxiliarRepo.findByCedula(cedula);
        auxiliarRepo.deleteByCedula(cedula);
    }

    public List<Auxiliar> obtenerListadoAuxiliares(){
        return auxiliarRepo.findAllByOrderById();
    }


}
