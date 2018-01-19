package com.dmurchkov.service.agency.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
public class AgencyServiceAspect {

    @Before(value = "execution(* com.dmurchkov.service.agency.AgencyService.submitAdd (long, long, java.lang.String)) " +
            "&& args(authorId, apartmentId, description)", argNames = "authorId,apartmentId,description")
    public void beforeSubmitAdd(long authorId, long apartmentId, String description) {
        log.info("Submit add with parameters: authorId: {}, apartmentId: {}, description: {}",
                authorId, apartmentId, description);
    }
}