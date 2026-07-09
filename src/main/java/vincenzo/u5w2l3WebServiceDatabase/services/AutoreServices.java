package vincenzo.u5w2l3WebServiceDatabase.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vincenzo.u5w2l3WebServiceDatabase.Repositories.AutoreRepository;
import vincenzo.u5w2l3WebServiceDatabase.config.CloudinaryConfig;
import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;
import vincenzo.u5w2l3WebServiceDatabase.exceptions.BadRequestException;
import vincenzo.u5w2l3WebServiceDatabase.exceptions.NotFoundException;
import vincenzo.u5w2l3WebServiceDatabase.payloads.AutoreRequestPayload;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class AutoreServices {

    private final AutoreRepository autoreRepository;
    private final Cloudinary fileUploader;

    public AutoreServices(AutoreRepository autoreRepository, Cloudinary fileUploader) {
        this.autoreRepository = autoreRepository;
        this.fileUploader = fileUploader;
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

    public void updateAvatar(UUID autoreId, MultipartFile file) {
        if (file.getSize() >= 10485760) throw new BadRequestException("File size can't be more than 10MB");
        if (!(Objects.equals(file.getContentType(), "image/jpeg") || Objects.equals(file.getContentType(),
                "image/gif") || Objects.equals(file.getContentType(), "image/png") || Objects.equals(
                file.getContentType(), "image/webp")))
            throw new BadRequestException("File must be an img");

        Autore autoreFromDb = findById(autoreId);

        try {
            Map result = fileUploader.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            autoreFromDb.setAvatar(url);
            this.autoreRepository.save(autoreFromDb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void findByIdAndDelete(UUID autoreId) {
        Autore found = this.findById(autoreId);
        this.autoreRepository.delete(found);
    }
}
