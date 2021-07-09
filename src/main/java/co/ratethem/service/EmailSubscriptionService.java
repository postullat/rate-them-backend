package co.ratethem.service;

import co.ratethem.controller.rest_exception_handler.exception.EmptyValueException;
import co.ratethem.controller.rest_exception_handler.exception.InvalidValueException;
import co.ratethem.entity.EmailSubscription;
import co.ratethem.repository.EmailSubscriptionRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Pattern;

@Service
@Log4j2
public class EmailSubscriptionService {

    @Autowired
    private EmailSubscriptionRepository emailSubscriptionRepository;

    public void addEmailSubscription(String email) throws EmptyValueException, InvalidValueException {

        //validate email
        if(StringUtils.isBlank(email)){
            throw new EmptyValueException("Email value can't be null or empty");
        }

        /*if(!isEmailValid(email)) {
            throw new InvalidValueException("Email is not valid");
        }*/

        EmailSubscription emailSubscription = new EmailSubscription();
        emailSubscription.setEmail(email);
        emailSubscription.setActive(true);
        emailSubscription.setCreated(new Date());

        emailSubscriptionRepository.save(emailSubscription);

        log.debug("Email subscription success");

    }

    public static boolean isEmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9]+@(?:[a-zA-Z0-9]+\\.)+[A-Za-z]+$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
