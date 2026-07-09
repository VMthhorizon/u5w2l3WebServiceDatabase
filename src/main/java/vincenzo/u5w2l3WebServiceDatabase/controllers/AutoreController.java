package vincenzo.u5w2l3WebServiceDatabase.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;
import vincenzo.u5w2l3WebServiceDatabase.exceptions.ValidationException;
import vincenzo.u5w2l3WebServiceDatabase.payloads.AutoreRequestPayload;
import vincenzo.u5w2l3WebServiceDatabase.payloads.AutoreResponsePayload;
import vincenzo.u5w2l3WebServiceDatabase.services.AutoreServices;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autori")
public class AutoreController {

    private final AutoreServices autoreServices;

    public AutoreController(AutoreServices autoreServices) {
        this.autoreServices = autoreServices;
    }

    @GetMapping
    public Page<Autore> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        return this.autoreServices.getAll(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutoreResponsePayload createAutore(@RequestBody @Validated AutoreRequestPayload body,
                                              BindingResult validationResult
    ) {
        if (validationResult.hasErrors()) {
            validationResult.getFieldErrors()
                    .forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));

            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException(errorsList);
        }

        Autore autore = this.autoreServices.createAutore(body);
        return new AutoreResponsePayload(autore.getId(), autore.getNome(), autore.getCognome(), autore.getEmail(),
                autore.getDataDiNascita(), autore.getAvatar());
    }

    @GetMapping("/{autoreId}")
    @ResponseStatus(HttpStatus.FOUND)
    public Autore findById(@PathVariable UUID autoreId) {
        return this.autoreServices.findById(autoreId);
    }

    @PutMapping("/{autoreId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Autore UpdateById(@RequestBody @Validated AutoreRequestPayload body, BindingResult validationResult,
                             @PathVariable UUID autoreId) {
        if (validationResult.hasErrors()) {
            validationResult.getFieldErrors()
                    .forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));

            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException(errorsList);
        }
        return this.autoreServices.findByIdAndUpdate(autoreId, body);
    }

    @DeleteMapping("/{autoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAutore(@PathVariable UUID autoreId) {
        this.autoreServices.findByIdAndDelete(autoreId);
    }

}
