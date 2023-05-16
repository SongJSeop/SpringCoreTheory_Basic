package hello.core.lifecycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 스프링 빈 생명주기 콜백 지원 방법 3. 애노테이션 지원(@PostConstruct, @PreDestroy)
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    /* 빈 생명주기 콜백 지원 방법 3. 애노테이션 지원(@PostConstruct, @PreDestroy)
    최신 스프링에서 가장 권장하는 방법. 애노테이션만 붙이면 끝이므로 매우 편리.
    애노테이션의 패키지는 javax.annotation.PostConstruct.. 즉 자바 표준. 스프링 외의 다른 컨테이너에서도 동작한다.
    컴포넌트 스캔과 잘 어울린다.

    단점은 외부 라이브러리에는 적용할 수 없다는 것. 외부 라이브러리를 초기화, 종료 해야 하면 2번 방법을 사용하자.
     */
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");

    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
