package pl.marceen.learningjavaee.mockito.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.learningjavaee.mockito.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Marcin Zaremba
 */
public class CustomerDao {
    private final static Logger logger = LoggerFactory.getLogger(CustomerDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void save(String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName);
        entityManager.persist(customer);
        entityManager.flush();

        logger.info("Saved customer with id {}", customer.getId());
    }

}
