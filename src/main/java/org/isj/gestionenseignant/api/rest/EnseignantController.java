package org.isj.gestionenseignant.api.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.isj.gestionenseignant.api.domaine.dto.EnseignantDto;
import org.isj.gestionenseignant.api.domaine.entities.Enseignant;
import org.isj.gestionenseignant.api.service.IService;
import org.isj.gestionenseignant.api.service.mapper.EnseignantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/gestionenseignant/api")
@Api(tags = "enseignants")
public class EnseignantController {

	@Autowired
	private IService service;

	@Autowired
	private EnseignantMapper enseignantMapper;

	@RequestMapping(value="/enseignants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EnseignantDto> getAllEnseignants() {

		final List<Enseignant> enseignants = service.getAllEnseignantService();

		final List<EnseignantDto> dtos= enseignants.stream().map(enseignantMapper::toDto).collect(Collectors.toList());

		return dtos;
	}

	@RequestMapping(value = "/creer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public EnseignantDto saveEnseignant(@RequestBody EnseignantDto enseignantDto) {

		Enseignant enseignant = enseignantMapper.toEntity(enseignantDto);
		enseignant= service.saveEnseignantService(enseignant);

		return enseignantMapper.toDto(enseignant);
	}

	@RequestMapping(value = "/enseignant/{telephone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	public EnseignantDto getEnseignantByTelephone(@PathVariable String telephone) {

		final Enseignant enseignant= service.findEnseignantByTelephone(telephone);

		return enseignantMapper.toDto(enseignant);
	}

}
