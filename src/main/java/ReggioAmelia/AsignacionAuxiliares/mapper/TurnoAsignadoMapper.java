package ReggioAmelia.AsignacionAuxiliares.mapper;

import ReggioAmelia.AsignacionAuxiliares.dto.TurnoAsignadoDTO;
import ReggioAmelia.AsignacionAuxiliares.dto.TurnoDTO;
import ReggioAmelia.AsignacionAuxiliares.modelo.Turno;
import ReggioAmelia.AsignacionAuxiliares.modelo.TurnoAsignado;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TurnoAsignadoMapper {
    TurnoAsignadoMapper INSTANCE = Mappers.getMapper(TurnoAsignadoMapper.class);

    TurnoAsignadoDTO toDto(TurnoAsignado source);

    TurnoAsignado toEntity(TurnoAsignadoDTO source);

    List<TurnoAsignadoDTO> entitiesToDtos(List<TurnoAsignado> dtos);

}
