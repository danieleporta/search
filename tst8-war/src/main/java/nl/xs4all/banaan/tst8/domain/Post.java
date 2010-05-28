package nl.xs4all.banaan.tst8.domain;

/** example domain object, intended to exercise hibernate */
public class Post {
    private Long id;
    private String subject;
    private String content;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
}
