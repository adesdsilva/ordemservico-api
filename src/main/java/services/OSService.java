package services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunsytem.domain.Cliente;
import com.sunsytem.domain.OrdemServico;
import com.sunsytem.domain.Tecnico;
import com.sunsytem.enums.Prioridade;
import com.sunsytem.enums.Status;

import dtos.OrdemServicoDTO;
import exceptions.ObjectNotFoundException;
import repositories.OSRepository;

@Service
public class OSService {

	@Autowired
	private OSRepository osRepository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public OrdemServico findById(Integer id) {
		Optional<OrdemServico> obj = osRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto" + "não encontrado! Id: " + id + ", Tipo: " + OrdemServico.class.getName()));
	}

	public List<OrdemServico> findAll() {
		return osRepository.findAll();
	}

	public OrdemServico create(@Valid OrdemServicoDTO objDTO) {
		return fromDTO(objDTO);
	}

	private OrdemServico fromDTO(OrdemServicoDTO objDTO) {
		OrdemServico newObj = new OrdemServico();
		newObj.setId(objDTO.getId());
		newObj.setObservacoes(objDTO.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
		newObj.setStatus(Status.toEnum(objDTO.getStatus()));

		Tecnico t = tecnicoService.findById(objDTO.getTecnico());
		Cliente c = clienteService.findById(objDTO.getCliente());

		newObj.setTecnico(t);
		newObj.setCliente(c);
		
		if(newObj.getStatus().getCodigo().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		return osRepository.save(newObj);
	}

	public OrdemServico update(@Valid OrdemServicoDTO objDTO) {
		findById(objDTO.getId());
		return fromDTO(objDTO);
	}

}
