package com.dmurchkov.service.agency.configuration;

import com.dmurchkov.service.agency.AgencyService;
import com.dmurchkov.service.agency.aspect.AgencyServiceAspect;
import com.dmurchkov.service.agency.persistence.Storage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgencyConfiguration {

    @Bean
    public Storage storage() {
        return new Storage();
    }

    @Bean
    public AgencyService agencyService(Storage storage) {
        return new AgencyService(storage);
    }

    @Bean
    public AgencyServiceAspect agencyServiceAspect() {
        return new AgencyServiceAspect();
    }
}