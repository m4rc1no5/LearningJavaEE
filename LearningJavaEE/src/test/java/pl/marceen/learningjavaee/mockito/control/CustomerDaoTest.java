package pl.marceen.learningjavaee.mockito.control;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.marceen.learningjavaee.mockito.entity.Customer;

import javax.persistence.EntityManager;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

/**
 * @author Marcin Zaremba
 */
public class CustomerDaoTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CustomerDao sut;

    @Test
    public void save() throws Exception {
        doAnswer(invocationOnMock -> {
            Customer customer = (Customer) invocationOnMock.getArguments()[0];
            customer.setId(123L);
            return null;
        }).when(entityManager).persist(any(Customer.class));

        sut.save("Susan", "Vega");
    }
}