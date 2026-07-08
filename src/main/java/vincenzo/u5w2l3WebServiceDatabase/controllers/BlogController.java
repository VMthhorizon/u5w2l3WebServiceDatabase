package vincenzo.u5w2l3WebServiceDatabase.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;
import vincenzo.u5w2l3WebServiceDatabase.entities.Blog;
import vincenzo.u5w2l3WebServiceDatabase.payloads.AutoreRequestPayload;
import vincenzo.u5w2l3WebServiceDatabase.payloads.AutoreResponsePayload;
import vincenzo.u5w2l3WebServiceDatabase.payloads.BlogPostsPayload;
import vincenzo.u5w2l3WebServiceDatabase.payloads.BlogPostsResponsePayload;
import vincenzo.u5w2l3WebServiceDatabase.services.AutoreServices;
import vincenzo.u5w2l3WebServiceDatabase.services.BlogPostsServices;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class BlogController {

    private final BlogPostsServices blogPostsServices;

    public BlogController(BlogPostsServices blogPostsServices) {
        this.blogPostsServices = blogPostsServices;
    }


    @GetMapping
    public Page<Blog> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        return this.blogPostsServices.getAll(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPostsResponsePayload createBlog(@RequestBody BlogPostsPayload body) {
        Blog blogPost = this.blogPostsServices.createBlog(body);
        return new BlogPostsResponsePayload(blogPost.getCategoria(), blogPost.getCategoria(), blogPost.getContenuto(),
                blogPost.getTempoDiLettura(), blogPost.getId());
    }

    @GetMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.FOUND)
    public Blog findById(@PathVariable UUID blogPostId) {
        return this.blogPostsServices.findById(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Blog UpdateById(@RequestBody BlogPostsPayload body, @PathVariable UUID blogPostId) {
        return this.blogPostsServices.findByIdAndUpdate(blogPostId, body);
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAutore(@PathVariable UUID blogPostId) {
        this.blogPostsServices.findByIdAndDelete(blogPostId);
    }
}
