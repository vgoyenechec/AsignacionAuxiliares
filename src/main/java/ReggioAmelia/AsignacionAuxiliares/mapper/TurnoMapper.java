package ReggioAmelia.AsignacionAuxiliares.mapper;

import ReggioAmelia.AsignacionAuxiliares.dto.TurnoDTO;
import ReggioAmelia.AsignacionAuxiliares.modelo.Turno;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TurnoMapper {
    TurnoMapper INSTANCE = Mappers.getMapper(TurnoMapper.class);

    TurnoDTO toDto(Turno source);

    Turno toEntity(TurnoDTO source);

    List<Turno> dtosToEntities(List<TurnoDTO> dtos);

}
