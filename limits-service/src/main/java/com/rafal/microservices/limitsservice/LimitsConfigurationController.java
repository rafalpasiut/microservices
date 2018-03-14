package com.rafal.microservices.limitsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rafal.microservices.limitsservice.bean.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    private Configuration configuration;

    @Autowired
    public LimitsConfigurationController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public LimitsConfiguration retreiveLimitsFromConfiguration(){
        return new LimitsConfiguration(configuration.getMaximum(),configuration.getMinimum());
    }

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "getDefaultConfiguration")
    public LimitsConfiguration retreiveConfiguration(){
        throw new RuntimeException();
    }

    private LimitsConfiguration getDefaultConfiguration(){
        return new LimitsConfiguration(0,0);
    }
}
