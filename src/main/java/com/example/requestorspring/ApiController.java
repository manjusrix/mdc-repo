package com.example.requestorspring;
import com.example.requestorspring.client.MicroserviceClient;
import com.example.requestorspring.model.MicroserviceResponse;
import com.example.requestorspring.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/request"))
@Slf4j
public class ApiController {

  @Autowired
  private MicroserviceClient microserviceClient;

  @Autowired
  private AsyncService service;

  @GetMapping("/{microservice}")
  MicroserviceResponse getMicroservice(@PathVariable String microservice) {
    MicroserviceResponse response = microserviceClient.getMicroservice(microservice);
        return response;
  }

  @GetMapping("/async/{microservice}")
  ResponseEntity<?> getAsyncMicroservice(@PathVariable String microservice) {
    try {
      service.getMicroservice(microservice);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
