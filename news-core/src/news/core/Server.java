/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.core;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Interface representando um servidor de notícias
 *
 * @author 0145022
 */
public interface Server extends Remote {

    /**
     * Adiciona um tópico
     *
     * @param topic tópico a ser adicionado
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void addTopic(Topic topic) throws RemoteException;

    /**
     * Adiciona uma notícia
     *
     * @param news notícia a ser adicionada
     * @param topic tópico ao qual a notícia está associada
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void addNews(News news, Topic topic) throws RemoteException;

    /**
     * Retorna lista com todos os tópicos existentes
     *
     * @return lista com todos os tópicos existentes
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public List<Topic> retrieveAvailableTopics() throws RemoteException;

    /**
     * Retorna a lista de notícias publicada pelo usuário especificado
     *
     * @param user usuário para filtro
     * @return lista de notícias
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public List<News> retrievePublishedNews(User user) throws RemoteException;

    /**
     * Solicita as notícias do tópico {@code topic} dentro do intervalo de datas especificado.
     *
     * @param topic tópíco para retornar as notícias
     * @param initialDate data inicial para filtrar as notícias
     * @param finalDate data final para filtrar as notícias
     * @return lista de notícias de acordo com os filtros especificados. Se não encontrar nenhuma notícia então retorna uma lista vazia.
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public List<News> retrieveNews(Topic topic, Date initialDate, Date finalDate) throws RemoteException;

    /**
     * Solicita a última notícia de um determinado tópico
     *
     * @param topic
     * @return notícia encontrada ou {@code null} se não encontrar nenhuma
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public News retrieveLastNews(Topic topic) throws RemoteException;

    /**
     * Inscreve o usuário em um tópico
     *
     * @param user usuário
     * @param topic tópico onde o usuário será inscrito
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void subscribe(User user, Topic topic) throws RemoteException;

    /**
     * Adiciona um usuário
     *
     * @param user
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void addUser(User user) throws RemoteException;

}
