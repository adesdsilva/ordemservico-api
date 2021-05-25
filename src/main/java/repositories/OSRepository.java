package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunsytem.domain.OrdemServico;

@Repository
public interface OSRepository extends JpaRepository<OrdemServico, Integer> {

}
