package pl.marceen.learningjavaee.mockito.control;

import pl.marceen.learningjavaee.mockito.entity.Customer;
import pl.marceen.learningjavaee.mockito.entity.Event;
import pl.marceen.learningjavaee.mockito.entity.EventRecorder;

import java.util.List;

/**
 * @author Marcin Zaremba
 */
public class MassUserRegistration
{
    private final EventRecorder eventRecorder;
    private final CustomerDao customerDao;

    public MassUserRegistration(final EventRecorder eventRecorder, final CustomerDao customerDao) {
        this.eventRecorder = eventRecorder;
        this.customerDao = customerDao;
    }

    public void massRegister(List<Customer> rawCustomerNames) {
        for (Customer customer : rawCustomerNames) {
            register(customer.getFirstName(), customer.getLastName());
        }
    }

    private void register(String firstName, String lastName) {
        Customer customer = customerDao.save(firstName, lastName);

        Event event = new Event();
        event.setTimestamp(customer.getSince());
        event.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
        eventRecorder.recordEvent(event);
    }
}
