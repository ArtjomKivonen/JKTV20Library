/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Book;
import entity.History;
import entity.Reader;
import java.util.List;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author Melnikov
 */
public class HistoryFacade extends AbstractFacade<History>{
    
    private EntityManager em;
    
    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public History findHistoryByGivenBook(Book book) {
        return (History) getEntityManager().createQuery(
                "SELECT history FROM History history WHERE history.book = :book AND history.returnDate = null")
                .setParameter("book", book)
                .getSingleResult();
    }

    public List<History> findHistoryWithGivenBooks() {
        return getEntityManager().createQuery(
                "SELECT h FROM History h WHERE h.returnDate = null"
        ).getResultList();
    }

    public List<History> findAll(Reader reader) {
        return getEntityManager().createQuery
                ("SELECT history FROM History h WHERE history.reader = :reader AND history.returnDate = null")
                .setParameter("reader", reader).getResultList();
    }
}
