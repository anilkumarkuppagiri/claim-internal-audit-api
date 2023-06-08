package com.vsp.api.claiminternalauditapi;

import com.vsp.api.core.AuthScopes;
import com.vsp.oauth.ApiPublic;
import com.vspglobal.cloud.VspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EnableDiscoveryClient
@VspService
@RestController
@RequestMapping(path = "/hello")
@AuthScopes(GET = "read:gb.hello", POST = "write:gb.hello") // You can require auth scopes by HTTP verb
public class HelloWorldResourceImpl {

    // Autowiring the AppConfig bean with values loaded from YAML files or Consul
    @Autowired
    private AppConfig appConfig;

    private final Logger LOG = LoggerFactory.getLogger(HelloWorldResourceImpl.class);

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getHello() {
        String response = "//TODO: implement method getHello";
        LOG.info(response);
        return response;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
        public String createHello(@RequestBody String message) {
        String response = "//TODO: implement method createHello";
        LOG.info(response);
        return response;
    }

    @AuthScopes("write:gb.hello") // You can require auth scopes per method
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public String updateHello(@PathVariable(name = "id") Long id, @RequestBody String message) {
        String response = "//TODO: implement method updateHello";
        LOG.info(response);
        return response;
    }

    @ApiPublic // This method does NOT require any auth scopes
    @RequestMapping(path = "/public", method = RequestMethod.GET)
    public String publicHello() {
        String response = "//TODO: implement method publicHello";
        LOG.info(response);
        return response;
    }

}
