/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 0145022
 */
public interface Server {

    /**
     * Adiciona um tópico
     *
     * @param topic tópico a ser adicionado
     */
    public void addTopic(Topic topic);

    /**
     * Adiciona uma notícia
     *
     * @param news notícia a ser adicionada
     * @param topic tópico ao qual a notícia está associada
     */
    public void addNews(News news, Topic topic);

    /**
     * Retorna lista com todos os tópicos existentes
     *
     * @return lista com todos os tópicos existentes
     */
    public List<Topic> retrieveAvailableTopics();

    /**
     * Retorna a lista de notícias publicada pelo usuário especificado
     *
     * @param user usuário para filtro
     * @return lista de notícias
     */
    public List<News> retrievePublishedNews(User user);

    /**
     * Solicita as notícias do tópico {@code topic} dentro do intervalo de datas
     * especificado.
     *
     * @param topic tópíco para retornar as notícias
     * @param initialDate data inicial para filtrar as notícias
     * @param finalDate data final para filtrar as notícias
     * @return lista de notícias de acordo com os filtros especificados. Se não
     * encontrar nenhuma notícia então retorna uma lista vazia.
     */
    public List<News> retrieveNews(Topic topic, Date initialDate, Date finalDate);

    /**
     * Solicita a última notícia de um determinado tópico
     *
     * @param topic
     * @return notícia encontrada ou {@code null} se não encontrar nenhuma
     */
    public News retrieveLastNews(Topic topic);

    /**
     * Inscreve o usuário em um tópico
     *
     * @param user usuário
     * @param topic tópico onde o usuário será inscrito
     */
    public void subscribe(User user, Topic topic);

    /**
     * Adiciona um usuário
     *
     * @param user
     */
    public void addUser(User user);

}
