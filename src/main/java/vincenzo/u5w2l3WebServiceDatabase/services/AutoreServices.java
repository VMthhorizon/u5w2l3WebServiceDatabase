package vincenzo.u5w2l3WebServiceDatabase.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vincenzo.u5w2l3WebServiceDatabase.Repositories.AutoreRepository;
import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;
import vincenzo.u5w2l3WebServiceDatabase.exceptions.BadRequestException;
import vincenzo.u5w2l3WebServiceDatabase.exceptions.NotFoundException;
import vincenzo.u5w2l3WebServiceDatabase.payloads.AutoreRequestPayload;
import vincenzo.u5w2l3WebServiceDatabase.payloads.AutoreResponsePayload;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AutoreServices {

    private final AutoreRepository autoreRepository;

    public AutoreServices(AutoreRepository autoreRepository) {
        this.autoreRepository = autoreRepository;
    }


    public Autore createAutore(AutoreRequestPayload body) {
        Autore autoreFromDb = new Autore(body.getNome(), body.getCognome(), body.getEmail(), body.getDataDiNascita());
        return autoreRepository.save(autoreFromDb);
    }

    public Page<Autore> getAll(int page, int size) {
        if (size > 30) size = 50;
        if (size < 0) size = 10;
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

        found.setNome(payload.getNome());
        found.setCognome(payload.getCognome());
        found.setEmail(payload.getEmail());
        found.setDataDiNascita(payload.getDataDiNascita());

        return this.autoreRepository.save(found);
    }

    public void findByIdAndDelete(UUID autoreId) {
        Autore found = this.findById(autoreId);
        this.autoreRepository.delete(found);
    }
}
