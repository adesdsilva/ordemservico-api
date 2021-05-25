package resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dtos.OrdemServicoDTO;
import services.OSService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OrdemServicoResource {

	@Autowired
	private OSService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Integer id) {
		OrdemServicoDTO obj = new OrdemServicoDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> findAll() {
		List<OrdemServicoDTO> listaDTO = service.findAll().stream().map(obj -> new OrdemServicoDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDTO);

		// List<OrdemServico> lista = service.findAll();
		// List<OrdemServicoDTO> listaDTO = new ArrayList<>();
		// for(OrdemServico obj : lista) {
		// listaDTO.add(new OrdemServicoDTO(obj));
		// }
	}

	@PostMapping
	public ResponseEntity<OrdemServicoDTO> create(@Valid @RequestBody OrdemServicoDTO objDTO) {
		objDTO = new OrdemServicoDTO(service.create(objDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// @PutMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> update(/* @PathVariable Integer id, */
			@Valid @RequestBody OrdemServicoDTO objDTO) {
		objDTO = new OrdemServicoDTO(service.update(objDTO));
		return ResponseEntity.ok().body(objDTO);
	}

	/*
	 * @DeleteMapping(value = "/{id}") public ResponseEntity<OrdemServicoDTO>
	 * delete(@PathVariable Integer id) { service.delete(id); return
	 * ResponseEntity.noContent().build(); }
	 */
}
