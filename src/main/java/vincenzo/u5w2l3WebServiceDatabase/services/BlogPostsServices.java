package vincenzo.u5w2l3WebServiceDatabase.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vincenzo.u5w2l3WebServiceDatabase.Repositories.AutoreRepository;
import vincenzo.u5w2l3WebServiceDatabase.Repositories.BlogPostsRepository;
import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;
import vincenzo.u5w2l3WebServiceDatabase.entities.Blog;
import vincenzo.u5w2l3WebServiceDatabase.exceptions.NotFoundException;
import vincenzo.u5w2l3WebServiceDatabase.payloads.AutoreRequestPayload;
import vincenzo.u5w2l3WebServiceDatabase.payloads.BlogPostsPayload;
import vincenzo.u5w2l3WebServiceDatabase.payloads.BlogPostsResponsePayload;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlogPostsServices {

    private final AutoreServices autoreRepository;
    private final BlogPostsRepository blogPostsRepository;

    public BlogPostsServices(AutoreServices autoreRepository, BlogPostsRepository blogPostsRepository) {
        this.autoreRepository = autoreRepository;
        this.blogPostsRepository = blogPostsRepository;
    }


    public Blog createBlog(BlogPostsPayload body) {
        Autore autore = autoreRepository.findById(body.autoreId());
        Blog blogFromDb = new Blog(body.categoria(), body.titolo(), body.contenuto(), body.tempoDiLettura(),
                autore);
        return blogPostsRepository.save(blogFromDb);
    }

    public Page<Blog> getAll(int page, int size) {
        if (size > 30) size = 20;
        if (size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size);
        return this.blogPostsRepository.findAll(pageable);
    }

    public Blog findById(UUID blogPostId) {
        return this.blogPostsRepository.findById(blogPostId)
                .orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public Blog findByIdAndUpdate(UUID blogPostId, BlogPostsPayload payload) {
        Blog found = this.findById(blogPostId);

        found.setCategoria(payload.categoria());
        found.setTitolo(payload.titolo());
        found.setContenuto(payload.contenuto());
        found.setTempoDiLettura(payload.tempoDiLettura());

        return this.blogPostsRepository.save(found);
    }

    public void findByIdAndDelete(UUID blogPostId) {
        Blog found = this.findById(blogPostId);
        this.blogPostsRepository.delete(found);
    }
}
