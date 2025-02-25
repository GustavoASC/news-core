/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import news.core.News;
import news.core.Topic;
import news.core.User;

/**
 * Interface representando um servidor de notícias
 *
 * @author 0145022
 */
public interface NewsServer extends Remote {

    /**
     * Adiciona um tópico
     *
     * @param username
     * @param topic tópico a ser adicionado
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void addTopic(String username, Topic topic) throws RemoteException, Exception;

    /**
     * Adiciona uma notícia
     *
     * @param username
     * @param news notícia a ser adicionada
     * @param topic tópico ao qual a notícia está associada
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void addNews(String username, News news, Topic topic) throws RemoteException, Exception;

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
    public List<News> retrieveNews(String topic, Date initialDate, Date finalDate) throws RemoteException;

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
     * @param username nome do usuário
     * @param topic tópico onde o usuário será inscrito
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void subscribe(String username, Topic topic) throws RemoteException, Exception;

    /**
     * Adiciona um usuário
     *
     * @param name nome do usuário
     * @param senha senha do usuário
     * @param finalidade escritor/leitor
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void addUser(String name, char[] senha, boolean finalidade) throws RemoteException;

    /**
     * Efetua o logon do usuário no servidor de notícias
     *
     * @param username nome do usuário que está fazendo logon
     * @param ip IP de origam do usuário
     * @param port porta de origam do usuário
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public void addLoggedUser(String username, String ip, int port) throws RemoteException;

    /**
     * Valida o usuário e senha
     *
     * @param userName
     * @param userPassword
     * @return usuário
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public User validateLoginUser(String userName, char[] userPassword) throws RemoteException, Exception;

    /**
     * Busca o usuário a partir do nome de usuário
     *
     * @param username
     * @return usuário
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public User getUserByName(String username) throws RemoteException;

    /**
     * Busca o tópico a partir do nome
     *
     * @param name
     * @return topico
     * @throws RemoteException se ocorrer algum erro durante a comunicação RMI
     */
    public Topic getTopicByName(String name) throws RemoteException;

    /**
     * Retorna a lista de tópicos disponíveis
     *
     * @return Lista de tópicos disponíveis
     * @throws RemoteException
     */
    public List<Topic> getTopics() throws RemoteException;

    /**
     * Retorna se o usuário está logado
     *
     * @param username nome do usuário para teste
     * @throws java.rmi.RemoteException
     */
    public void userIsLogged(String username) throws RemoteException, Exception;

    /**
     * Remove o usuário da lista de logados
     * @param username
     * @throws RemoteException
     */
    public void removeLoggedUser(String username) throws RemoteException;

}
