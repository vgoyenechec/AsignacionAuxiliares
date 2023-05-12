package ReggioAmelia.AsignacionAuxiliares.mapper;

import ReggioAmelia.AsignacionAuxiliares.dto.TurnoAsignadoDTO;
import ReggioAmelia.AsignacionAuxiliares.modelo.TurnoAsignado;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TurnoAsignadoMapper {
    TurnoAsignadoMapper INSTANCE = Mappers.getMapper(TurnoAsignadoMapper.class);

    TurnoAsignadoDTO toDto(TurnoAsignado source);

    TurnoAsignado toEntity(TurnoAsignadoDTO source);
}
