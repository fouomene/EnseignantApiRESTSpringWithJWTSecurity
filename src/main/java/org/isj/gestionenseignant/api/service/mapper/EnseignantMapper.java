/**
 *
 */
package org.isj.gestionenseignant.api.service.mapper;


import org.isj.gestionenseignant.api.domaine.dto.EnseignantDto;
import org.isj.gestionenseignant.api.domaine.entities.Enseignant;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author fouomene
 *
 */
@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy =  NullValueCheckStrategy.ALWAYS)
public interface EnseignantMapper {

	Enseignant toEntity(EnseignantDto enseignantDto);

	EnseignantDto toDto(Enseignant entity);


}
