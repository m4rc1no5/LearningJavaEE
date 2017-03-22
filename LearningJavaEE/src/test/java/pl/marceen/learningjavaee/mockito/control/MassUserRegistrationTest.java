package pl.marceen.learningjavaee.mockito.control;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.marceen.learningjavaee.mockito.entity.Customer;
import pl.marceen.learningjavaee.mockito.entity.Event;
import pl.marceen.learningjavaee.mockito.entity.EventRecorder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Marcin Zaremba
 */
public class MassUserRegistrationTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private EventRecorder eventRecorder;

    @Mock
    private CustomerDao customerDao;

    private MassUserRegistration sut;

    private List<Customer> sampleCustomers;

    @Before
    public void setUp() throws Exception {
        sampleCustomers = new ArrayList<>();

        when(customerDao.save(anyString(), anyString())).thenAnswer(invocation -> {
            String firstName = (String) invocation.getArguments()[0];
            String lastName = (String) invocation.getArguments()[1];

            Customer customer = new Customer(firstName, lastName);
            customer.setFullName(firstName + " " + lastName);
            customer.setSince(LocalDate.now().toString());

            return customer;
        });

        sut = new MassUserRegistration(eventRecorder, customerDao);
    }

    @Test
    public void massRegister() throws Exception {
        sampleCustomers.add(new Customer("Susan", "Ivanova"));
        sampleCustomers.add(new Customer("Lyta", "Alexander"));
        sampleCustomers.add(new Customer("Vir", "Cotto"));
        sampleCustomers.add(new Customer("Stephen", "Frankling"));

        sut.massRegister(sampleCustomers);

        ArgumentCaptor<Event> myCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventRecorder, times(sampleCustomers.size())).recordEvent(myCaptor.capture());

        List<Event> eventsThatWereSent = myCaptor.getAllValues();
        assertThat(sampleCustomers.size()).isEqualTo(eventsThatWereSent.size());
        for (int i = 0; i < eventsThatWereSent.size(); i++) {
            Event event = eventsThatWereSent.get(i);
            assertThat(event.getTimestamp()).isNotNull();
            assertThat(sampleCustomers.get(i).getFirstName() + " " + sampleCustomers.get(i).getLastName()).isEqualTo(event.getCustomerName());
        }

    }


}