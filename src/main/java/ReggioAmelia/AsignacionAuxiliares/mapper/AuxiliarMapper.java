package ReggioAmelia.AsignacionAuxiliares.mapper;

import ReggioAmelia.AsignacionAuxiliares.dto.AuxiliarDTO;
import ReggioAmelia.AsignacionAuxiliares.modelo.Auxiliar;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuxiliarMapper {
    AuxiliarMapper INSTANCE = Mappers.getMapper(AuxiliarMapper.class);

    AuxiliarDTO toDto(Auxiliar source);

    Auxiliar toEntity(AuxiliarDTO source);
}
