package sg.edu.nus.iss.workshop14.Service;

import sg.edu.nus.iss.workshop14.Model.Contact;

public interface ContactsRepo {
    public void save(final Contact ctc);

    //This is a abstract method (A method without a body)
    //for future expansion plans
    public Contact findById(final String contactId);
}
