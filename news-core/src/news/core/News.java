/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Notícia
 */
public class News implements Serializable {

    /* Título da notícia */
    private String title;
    /* Conteúdo da notícia */
    private String content;
    /* Data de publicação */
    private Date publicationDate;
    /* Usuário que publicou a notícia */
    private User publisher;

    /**
     * Cria uma notícia
     */
    public News() {
        this("");
    }

    /**
     * Cria notícia com o título especificado
     *
     * @param title título da notíca
     */
    public News(String title) {
        this(title, null, "");
    }
    
    /**
     * Cria notícia com o título especificado, data e usuário especificados
     *
     * @param title título da notíca
     * @param publicationDate data de publicação
     * @param publisher usuário que publicou a notícia
     */
    public News(String title, Date publicationDate, String publisher) {
        this(title, publicationDate, new User(publisher));
    }

    /**
     * Cria notícia com o título especificado, data e usuário especificados
     *
     * @param title título da notíca
     * @param publicationDate data de publicação
     * @param publisher usuário que publicou a notícia
     */
    public News(String title, Date publicationDate, User publisher) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
    }

    /**
     * Retorna o título da notícia
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Define o título da notícia
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retorna o conteúdo da notícia
     *
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * Define o conteúdo da notícia
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retorna a data de publicação
     *
     * @return Date
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * Define a data de publicação
     *
     * @param publicationDate
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Retorna o usuário que publicou a notícia
     *
     * @return User
     */
    public User getPublisher() {
        return publisher;
    }

    /**
     * Define o usuário que publicou a notícia
     *
     * @param publisher nome do usuário que publicoua notícia
     */
    public void setPublisher(String publisher) {
        this.publisher = new User(publisher);
    }

    /**
     * Define o usuário que publicou a notícia
     *
     * @param publisher
     */
    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "News{" + "title=" + title + ", content=" + content + ", publicationDate=" + publicationDate + ", publisher=" + publisher + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.content);
        hash = 53 * hash + Objects.hashCode(this.publicationDate);
        hash = 53 * hash + Objects.hashCode(this.publisher);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final News other = (News) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.publicationDate, other.publicationDate)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        return true;
    }

}
