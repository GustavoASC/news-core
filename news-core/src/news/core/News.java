/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.util.Date;

/**
 * Notícia
 */
public class News {

    /* Título da notícia */
    private String title;
    /* Conteúdo da notícia */
    private String content;
    /* Data de publicação */
    private Date publicationDate;
    /* Usuário que publicou a notícia */
    private User publisher;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

}
