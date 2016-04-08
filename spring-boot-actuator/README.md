# spring-boot-starter-actuator 예제

Actuator는 HTTP를 이용하여, 어플리케이션의 모니터링 및 관리를 지원을 한다.

[Complete Guide for Spring Boot Actuator](http://www.javabeat.net/spring-boot-actuator)

## Endpoints
Autuator는 다양한 Endpoints들을 제공하는데 이 Endpoints에 접근하여 현재 서버 또는 서비스의 상태를 모니텅링 할 수 있다.  
이 Endpoints들의  Visualization(시각화)을 통해 실시간 모니터링 환경을 구축할 수도 있다. 

ID | Description | Sensitive Default
--- | --- | ---
`actuator`| Provides a hypermedia-based “discovery page” for the other endpoints. Requires Spring HATEOAS to be on the classpath. | true
`autoconfig`| Displays an auto-configuration report showing all auto-configuration candidates and the reason why they ‘were’ or ‘were not’ applied. | true
`beans`| Displays a complete list of all the Spring beans in your application. | true
`configprops`| Displays a collated list of all @ConfigurationProperties. | true
`docs`| Displays documentation, including example requests and responses, for the Actuator’s endpoints. Requires spring-boot-actuator-docs to be on the classpath. | false
`dump`| Performs a thread dump. | true
`env`| Exposes properties from Spring’s ConfigurableEnvironment. | true
`flyway`| Shows any Flyway database migrations that have been applied. | true
`health`| Shows application health information (when the application is secure, a simple ‘status’ when accessed over an unauthenticated connection or full message details when authenticated). | false
`info`| Displays arbitrary application info. | false
`liquibase`| Shows any Liquibase database migrations that have been applied. | true
`logfile`| Returns the contents of the logfile (if logging.file or logging.path properties have been set). Only available via MVC. Supports the use of the HTTP Range header to retrieve part of the log file’s content. | true
`metrics`| Shows ‘metrics’ information for the current application. | true
`mappings`| Displays a collated list of all @RequestMapping paths. | true
`shutdown`| Allows the application to be gracefully shutdown (not enabled by default). | true
`trace`| Displays trace information (by default the last few HTTP requests). | true

자세한 내용은 다음을 참고한다.
[Part V. Spring Boot Actuator: Production-ready features#45. Endpoints](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html)

## Remote Shell
Spring Boot는 Remote Shell을 통해 서비스 모니터링이 가능하다.
이름에서 보이듯이 SSH 또는 Telnet을 통해 접근하여 Remote Shell이 제공하는 Command를 통해 서버를 모니터링할 수 있다.  
Rempte Shell Command 중 dashboard는 처음보는 순간 정말 놀라움이~~!!

![alt text](http://cfile5.uf.tistory.com/image/257F533755E04F19385FD9 "Remote Shell dashboard")

이렇게 생긴 대시보드가 실시간으로 정보가 제공되고 또 Actuator Endpoints가 제공하는 모든 내용을 Shell로 조회가 가능하다.  
리모트 쉘을 사용하기 위해서는 리모트 쉘을 의존성에 추가하면 된다.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-remote-shell</artifactId>
</dependency>
```

[Remote Shell 기능소개](http://java.ihoney.pe.kr/379)

예제의 리모트 쉘의 접속 정보는 다음과 같다.
* Protocol: SSH Version2
* Hostname/IP: localhost
* Port: 2000
* username: chotire
* password: shell

## Jolokia Protocol
Jolokia는 JSON 기반 JMX Agent라고 한다... 사실 JMX는 사용한 경험이 없어 jolokia라는 게 정확하게 무엇을 하는 지 모르겠지만
Actuator는 jolokia를 사용하여(Optional. Classpath내에 jolokia-core가 존재) JMX 정보를 조회하는 기능을 제공한다.  
jolokia가 JMX 정보를 제공하는 방법은 HTTP URI와 Parameter를 통해 제공한다.   
예제를 구동한 후 jolokia를 통해 서버의 Heap 메모리 사용량을 가져오는 방법이다. 아래 URL을 복사한 후 웹브라우저 주소창에 붙여 넣으면
```
http://localhost:8081/management/jolokia/read/java.lang:type=Memory/HeapMemoryUsage
```
다음과 비슷한 응답 메시지를 볼 수 있다.
```
{"request":{"mbean":"java.lang:type=Memory","attribute":"HeapMemoryUsage","type":"read"},"value":{"init":16777216,"committed":65634304,"max":259522560,"used":30926152},"timestamp":1459409331,"status":200}
```
[Jolokia Protocol](https://jolokia.org/reference/html/protocol.html) 을 참고.

## Spring Boot Admin
Actuator Endpoints, Jolokia Protocol은 요청에 대한 응답으로 JSON형태의 값을 돌려준다. 즉, 데이터의 시각화(Visualization)가 된 상태가 아니므로 그 내용을 읽기가 다소 부담스럽다.  
시각화된 오픈 소스가 혹시 있지는 않을까해서 찾아봤는데 역시나 존재한다. 이름은 [Spring Boot Admin](https://github.com/codecentric/spring-boot-admin) 로 간단한 설정만으로 훌륭한 화면을 볼 수 있다.

![alt text](https://raw.githubusercontent.com/codecentric/spring-boot-admin/master/screenshot-details.png "")

# 정리
예제의 서비스 주소는 다음과 같다. 예제를 구동 한 후 웹브라우저를 통해 접근.
* Root: http://localhost
* Management(Actuator Endpoints 접근): http://localhost:8081/management  
http://localhost:8081/management/autoconfig  
http://localhost:8081/management/beans  
http://localhost:8081/management/configprops  
http://localhost:8081/management/docs  
http://localhost:8081/management/dump  
http://localhost:8081/management/env  
http://localhost:8081/management/health  
http://localhost:8081/management/info  
http://localhost:8081/management/metrics  
http://localhost:8081/management/mappings  
....
* Spring Boot Admin: http://localhost:8080/console

위의 주소 중 Root URL을 제외하고 ID/Passoword를 입력하라는 Dialog가 뜨는데 Spring Security 설정으로 뜨는 것으로 admin/admin 계정으로 로그인하면 된다. 

예제의 resources 안에는 _application.properties와 application.yml 두 개의 파일이 있는데 _application.properties 내용을 그대로 application.yml로 옮긴 것이다. 즉, 예제에는 _application.properties를 사용하지 않고 application.yml이 사용되고 있다.   처음에는 기존에 익숙했던 properties 파일을 이용했는데 다른 예제들의 yml을 보니 properties 파일보다는 훨씬 구조화되어있고 또 들여쓰기를 강제화 하는 등 확실히 properties 보다는 좋다. 앞으로는 properties 보다는 yml을 적극 사용하는 게 좋겠다~

What It Is: [YAML](http://yaml.org/) is a human friendly data serialization standard for all programming languages.

소스내에 약간의 주석이 있으니 참고하세요~
