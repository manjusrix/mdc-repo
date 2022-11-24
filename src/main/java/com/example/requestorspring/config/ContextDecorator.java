package com.example.requestorspring.config;

import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContextDecorator implements TaskDecorator {
  @NotNull
  @Override
  public Runnable decorate(@NotNull Runnable runnable) {
    RequestAttributes requestContext = RequestContextHolder.currentRequestAttributes();
    HttpServletRequest request = ((ServletRequestAttributes)requestContext).getRequest();
    Map<String, String> contextMap = new HashMap<>();
    for (String header: Collections.list(request.getHeaderNames())) {
      contextMap.put(header, request.getHeader(header));
    }
    return () -> {
      try {
        RequestContextHolder.setRequestAttributes(requestContext, true);
        MDC.setContextMap(contextMap);
        runnable.run();
      } finally {
        MDC.clear();
        RequestContextHolder.resetRequestAttributes();
      }
    };
  }
}
