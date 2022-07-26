package sg.edu.nus.iss.workshop14.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop14.Model.Contact;


@Service
public class ContactsRedis implements ContactsRepo {
    private static final Logger logger = LoggerFactory.getLogger(ContactsRedis.class);

    //Central class to interact with Redis data. To serialize object to stream, and deserealize stream to object
    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    //to override ContactsRepo?
    @Override
    public void save(final Contact ctc) {
        //To access value operations (ID number)
        redisTemplate.opsForHash().put(ctc.getId(), "name", ctc.getName());
        redisTemplate.opsForHash().put(ctc.getId(), "email", ctc.getEmail());
        redisTemplate.opsForHash().put(ctc.getId(), "phoneNumber", ctc.getPhoneNumber());
    }

    @Override
    public Contact findById(final String contactId) {
        String name = (String) redisTemplate.opsForHash().get(contactId, "name");
        String email = (String) redisTemplate.opsForHash().get(contactId, "email");
        Integer phoneNumber = (Integer) redisTemplate.opsForHash().get(contactId, "phoneNumber");
        logger.info(">>> name " + name);
        logger.info(">>> email " + email);
        logger.info(">>> phoneNumber " + phoneNumber);

        Contact ct = new Contact();
        ct.setId(contactId);
        ct.setName(name);
        ct.setEmail(email);
        ct.setPhoneNumber(phoneNumber.intValue());

        return ct;
    }
}