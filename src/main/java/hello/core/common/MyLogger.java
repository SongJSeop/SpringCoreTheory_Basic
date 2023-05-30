package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
/*
인터페이스가 아닌 클래스면 TARGET_CLASS, 인터페이스면 INTERFACES.
가짜 프록시 클래스를 만들어두고 HTTP request와 상관 없이 가짜 프록시 클래스를 미리 다른 빈에 주입해 둘 수 있다.
Provider를 사용하지 않아도 됨. 실제 요청이 올때까지 가짜 프록시 클래스가 존재하다가 요청이 오면 진짜 빈을 요청하는 위임 로직이 들어있음.
싱글톤처럼 보이지만 다르게 동작함. 꼭 필요한 곳에서 최소화해서 사용해야함. 무분별하게 사용시 유지보수가 힘들어짐.
*/
public class MyLogger {

    private  String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
