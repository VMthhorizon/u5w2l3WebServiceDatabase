package vincenzo.u5w2l3WebServiceDatabase.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;

import java.util.UUID;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, UUID> {
}
