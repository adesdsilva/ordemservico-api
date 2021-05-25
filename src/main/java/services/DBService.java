package services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunsytem.domain.Cliente;
import com.sunsytem.domain.OrdemServico;
import com.sunsytem.domain.Tecnico;
import com.sunsytem.enums.Prioridade;
import com.sunsytem.enums.Status;

import repositories.ClienteRepository;
import repositories.OSRepository;
import repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OSRepository osRepository;
	
	public void instanciaDB() {
		
		Tecnico t1 = new Tecnico(null, "Andre Azevedo", "144.785.300-84", 
				"(88)98888-8888");
		Cliente c1 = new Cliente(null, "Betina Campos", "598.508.200-80",
				"(88)98888-7777");
		OrdemServico os1 = new OrdemServico(null, null, Prioridade.ALTA, 
				"Teste Create ok", Status.ANDAMENTO, t1, c1);
		
		t1.getLista().add(os1);
		c1.getLista().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
