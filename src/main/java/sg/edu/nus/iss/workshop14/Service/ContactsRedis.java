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
        //ANNOTAE THIS! To access value operations
        redisTemplate.opsForValue().set(ctc.getId(), ctc);
    }

    @Override
    public Contact findById(final String contactId) {
        Contact result = (Contact) redisTemplate.opsForValue().get(contactId);
        logger.info(">>> " + result.getEmail());
        return result;
    }
}