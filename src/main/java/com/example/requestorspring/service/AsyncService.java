package com.example.requestorspring.service;

import com.example.requestorspring.client.MicroserviceClient;
import com.example.requestorspring.model.MicroserviceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

@Component
@Slf4j
public class AsyncService {
  @Autowired
  MicroserviceClient client;

  @Autowired
  Executor taskExecutor;
  public void getMicroservice(String microservice) throws InterruptedException {
    taskExecutor.execute(() -> {
      MicroserviceResponse response = client.getMicroservice(microservice);
      log.info(response.toString());
    });
  }

//  Note: This does not work with async header, requires MDC!
//  @Async(value = "taskExecutor")
//  public Future<MicroserviceResponse> getMicroservice(String microservice) throws InterruptedException {
//    Thread.sleep(1000);
//    MicroserviceResponse response = client.getMicroservice(microservice);
//    log.info(response.toString());
//    return CompletableFuture.completedFuture(response);
//  }
}
