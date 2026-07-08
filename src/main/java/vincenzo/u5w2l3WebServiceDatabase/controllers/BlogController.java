package vincenzo.u5w2l3WebServiceDatabase.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vincenzo.u5w2l3WebServiceDatabase.entities.Blog;
import vincenzo.u5w2l3WebServiceDatabase.payloads.BlogPostsPayload;
import vincenzo.u5w2l3WebServiceDatabase.payloads.BlogPostsResponsePayload;
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
    public List<Blog> findAll() {
        return this.blogPostsServices.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPostsResponsePayload createBlogPosts(@RequestBody BlogPostsPayload body) {
        return this.blogPostsServices.createBlogPosts(body);
    }

    @GetMapping("/{blogPostId}")
    public Blog findById(@PathVariable UUID blogPostId) {
        return this.blogPostsServices.findById(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    public BlogPostsResponsePayload modifyBlogPosts(@RequestBody BlogPostsPayload body, @PathVariable UUID blogPostId) {
        return this.blogPostsServices.modifyBlogPostById(body, blogPostId);
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable UUID blogPostId) {
        this.blogPostsServices.deleteBlogById(blogPostId);
    }

}
