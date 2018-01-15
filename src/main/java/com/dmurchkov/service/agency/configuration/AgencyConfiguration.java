package com.dmurchkov.service.agency.configuration;

import com.dmurchkov.service.agency.AgencyService;
import com.dmurchkov.service.agency.Storage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgencyConfiguration {

    @Bean
    public Storage storage() {
        return new Storage();
    }

    @Bean
    public AgencyService agencyService() {
        return new AgencyService(storage());
    }
}