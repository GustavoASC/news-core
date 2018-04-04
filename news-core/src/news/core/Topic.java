/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.util.LinkedList;
import java.util.List;

/**
 * Tópico de notícia
 */
public class Topic {

    /* Número máximo de notícias que podem ser associadas a este tópico */
    private static final int MAXIMUM_NEWS = 10;
    /* Nome do tópico */
    private String name;
    /* Notícias associadas a este tópico */
    private final List<News> associatedNews;

    public Topic() {
        this.associatedNews = new LinkedList<>();
    }

    public List<News> getAssociatedNews() {
        return associatedNews;
    }

    public void addNews(News news) {
        // TODO: identificar se estourou o limite de notícias associadas
        associatedNews.add(news);
    }

}
