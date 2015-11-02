package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

/**
 * Clase-entidad encargada de representar cada entrada en el blog.
 *
 * @author arturo
 */
@Entity
public class Post extends Model {

    public String title;
    public Date postedAt;

    @Lob
    public String content;

    @ManyToOne
    public User author;
    
    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<Comment> comments;
    
    /**
     * Método constructor para la clase {@link Post}
     * 
     * @param author
     * @param title
     * @param content 
     */
    public Post(User author, String title, String content) {
        this.comments = new ArrayList<Comment>();
        this.author = author;
        this.title = title;
        this.content = content;
        this.postedAt = new Date();
    }
    
    /**
     * Método para agregar comentarios a un post
     * @param author
     * @param content
     * @return 
     */
    public Post addComment(String author, String content) {
        Comment newComment = new Comment(this, author, content).save();
        this.comments.add(newComment);
        this.save();
        return this;
    }
}
