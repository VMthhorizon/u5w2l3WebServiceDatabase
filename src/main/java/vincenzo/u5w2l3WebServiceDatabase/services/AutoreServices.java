package vincenzo.u5w2l3WebServiceDatabase.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vincenzo.u5w2l3WebServiceDatabase.Repositories.AutoreRepository;
import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;
import vincenzo.u5w2l3WebServiceDatabase.exceptions.NotFoundException;
import vincenzo.u5w2l3WebServiceDatabase.payloads.AutoreRequestPayload;

import java.util.UUID;

@Service
public class AutoreServices {

    private final AutoreRepository autoreRepository;

    public AutoreServices(AutoreRepository autoreRepository) {
        this.autoreRepository = autoreRepository;
    }


    public Autore createAutore(AutoreRequestPayload body) {
        Autore autoreFromDb = new Autore(body.nome(), body.cognome(), body.email(), body.dataDiNascita());
        return autoreRepository.save(autoreFromDb);
    }

    public Page<Autore> getAll(int page, int size) {
        if (size > 30) size = 1;
        if (size < 0) size = 3;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size);
        return this.autoreRepository.findAll(pageable);
    }

    public Autore findById(UUID autoreId) {
        return this.autoreRepository.findById(autoreId)
                .orElseThrow(() -> new NotFoundException(autoreId));
    }

    public Autore findByIdAndUpdate(UUID autoreId, AutoreRequestPayload payload) {
        Autore found = this.findById(autoreId);

        found.setNome(payload.nome());
        found.setCognome(payload.cognome());
        found.setEmail(payload.email());
        found.setDataDiNascita(payload.dataDiNascita());

        return this.autoreRepository.save(found);
    }

    public void findByIdAndDelete(UUID autoreId) {
        Autore found = this.findById(autoreId);
        this.autoreRepository.delete(found);
    }
}
