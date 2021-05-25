package services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunsytem.domain.Pessoa;
import com.sunsytem.domain.Tecnico;

import dtos.TecnicoDTO;
import exceptions.ObjectNotFoundException;
import repositories.PessoaRepository;
import repositories.TecnicoRepository;
import exceptions.DataIntegratyViolationException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException
				("Objeto não localizado! Id:" + id + ", Tipo: " 
						+ Tecnico.class.getName()));
	}
	
	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCpf(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado no DB!");
		}
		return repository.save(new Tecnico(null, objDTO.getNome(), 
				objDTO.getCpf(), objDTO.getTelefone()));
	}

	private Pessoa findByCpf(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);
		if(findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado no DB!");
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getLista().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui OS, não pode ser excluído!");
		}
		repository.deleteById(id);
	}	
}
