package vincenzo.u5w2l3WebServiceDatabase.services;

import org.springframework.stereotype.Service;
import vincenzo.u5w2l3WebServiceDatabase.entities.Blog;
import vincenzo.u5w2l3WebServiceDatabase.payloads.BlogPostsPayload;
import vincenzo.u5w2l3WebServiceDatabase.payloads.BlogPostsResponsePayload;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlogPostsServices {

    private List<Blog> blogPostsFromDb = new ArrayList<>();

    public List<Blog> findAll() {
        return this.blogPostsFromDb;
    }

    public BlogPostsResponsePayload createBlogPosts(BlogPostsPayload body) {
        Blog newBlog = new Blog(body.getCategoria(), body.getTitolo(), body.getContenuto(), body.getTempoDiLettura(),
                body.getAutore());
        blogPostsFromDb.add(newBlog);
        System.out.println("Il post con titolo" + newBlog.getTitolo() + " é stato creato");
        return new BlogPostsResponsePayload(newBlog.getId(), newBlog.getCategoria(), newBlog.getTitolo(),
                newBlog.getContenuto(), newBlog.getTempoDiLettura(), newBlog.getCover(), newBlog.getAutore());
    }

    public Blog findById(UUID blogPostId) {
        Blog found = null;

        for (Blog blog : this.blogPostsFromDb) {
            if (blog.getId() == blogPostId) found = blog;
        }

        if (found == null) throw new RuntimeException("Blog non trovato");
        return found;
    }

    public BlogPostsResponsePayload modifyBlogPostById(BlogPostsPayload body, UUID blogPostsId) {
        Blog found = null;

        for (Blog blog : this.blogPostsFromDb) {
            if (blog.getId() == blogPostsId) {
                found = blog;
                found.setTitolo(body.getTitolo());
                found.setCategoria(body.getCategoria());
                found.setTempoDiLettura(body.getTempoDiLettura());

                break;
            }
        }
        if (found == null) throw new RuntimeException("Blog non trovato");

        return new BlogPostsResponsePayload(found.getId(), found.getCategoria(),
                found.getTitolo(), found.getContenuto(), found.getTempoDiLettura(), found.getCover(),
                found.getAutore());
    }

    public void deleteBlogById(UUID blogPostId) {
        Blog found = findById(blogPostId);

        if (found == null) throw new RuntimeException("Nessun blog trovato");
    }
}
