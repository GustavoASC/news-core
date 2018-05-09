/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Tópico de notícia
 */
public class Topic implements Serializable {

    /* Número máximo de notícias que podem ser associadas a este tópico */
    private static final int MAXIMUM_NEWS = 10;
    /* Nome do tópico */
    private String name;
    /* Notícias associadas a este tópico */
    private final List<News> news;

    /**
     * Cria um tópico de notícia
     */
    public Topic() {
        this.news = new LinkedList<>();
    }
    
    public Topic(String name) {
        this.news = new LinkedList<>();
        this.name = name;
    }
    

    /**
     * Retorna as notícias associadas a este tópico
     *
     * @return lista com as notícias associadas
     */
    public List<News> getNews() {
        return news;
    }

    /**
     * Adiciona uma notícia ao tópico
     *
     * @param news notícia que será adicionada
     */
    public void addNews(News news) {
        // TODO: identificar se estourou o limite de notícias associadas
        this.news.add(news);
    }

    @Override
    public String toString() {
        return "Topic{" + "name=" + name + ", associatedNews=" + news + '}';
    }

    public String getName() {
        return name;
    }

}
