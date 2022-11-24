package com.example.requestorspring.client;

import com.example.requestorspring.model.MicroserviceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    value = "request",
    url = "http://a55c48d1f33434835868f5a8e651e8f6-074ccb75ffa5c34c.elb.ap-southeast-1.amazonaws.com"
)
public interface MicroserviceClient {
  @GetMapping("/{microservice}")
  MicroserviceResponse getMicroservice(@PathVariable String microservice);

  @GetMapping("/{microservice}")
  MicroserviceResponse getMicroserviceAsync(@PathVariable String microservice);
}


