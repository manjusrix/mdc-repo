# How does it work

## Feign Client
Feign is a declarative web service client. It makes writing web service clients easier. 
To use Feign create an interface and annotate it. It has pluggable annotation support including Feign annotations and JAX-RS annotations. 
Feign also supports pluggable encoders and decoders. Spring Cloud adds support for Spring MVC annotations and for using the same HttpMessageConverters used by default in Spring Web. 
Spring Cloud integrates Ribbon and Eureka to provide a load balanced http client when using Feign.

## Feign Configuration
Feign provides out of the box configurations via beans that you can include in your feign annotation
or use feign provided interceptors. 

It's worth noting that the beans are split into request scope and singleton scope.
While most configurations are singleton scoped, RequestInterceptors are not.

### Feign provided Beans
Feign provides the following beans by default.
- Decoder feignDecoder: ResponseEntityDecoder (which wraps a SpringDecoder)
- Encoder feignEncoder: SpringEncoder
- Logger feignLogger: Slf4jLogger
- Contract feignContract: SpringMvcContract
- Feign.Builder feignBuilder: HystrixFeign.Builder 
- Client feignClient: if Ribbon is enabled it is a LoadBalancerFeignClient, otherwise the default feign client is used.

### User provided Beans
Feign does not provide the following beans but scans for it if the bean exists.

- Logger.Level
- Retryer
- ErrorDecoder
- Request.Options
- Collection<RequestInterceptor>
- SetterFactory