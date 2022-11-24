package com.example.requestorspring.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

@Component
@Slf4j
public class HeaderInterceptor implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate requestTemplate) {
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (requestAttributes == null) {
      return;
    }
    requestTemplate.header("MIGRATION_ID".toLowerCase(), requestAttributes.getRequest().getHeader("MIGRATION_ID"));
    // For async
    Map<String, String> contextMap = MDC.getCopyOfContextMap();
    if (contextMap != null) {
      if(contextMap.size() > 0) {
        requestTemplate.header("MIGRATION_ID".toLowerCase(), contextMap.get("MIGRATION_ID".toLowerCase()));
      }
    }
  }
}
