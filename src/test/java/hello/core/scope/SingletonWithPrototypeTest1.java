package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static hello.core.scope.PrototypeTest.*;
import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFine() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
        // 프로토타입 빈은 호출될 때 각각의 새로운 인스턴스가 생성됨. 그러므로 count가 각각 addCount()에 의해 1이 됨.
    }

    @Test
    void SingletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);
        /*
        싱글톤빈 안에 프로토타입빈을 주입하면 싱글톤이 생성될 때 프로토타입 빈이 생성되고 주입되며 끝남.
        이 싱글톤빈은 생성된 프로토타입빈을 계속 가져가므로 logic()에 의해 addCount()가 일어나도 늘어나는 count는 항상 같은 것.
        이 점이 싱글톤빈과 프로토타입빈을 같이 사용할 때의 문제점임.
        우리가 원하는 점은 항상 count 인자가 새로 생성되어 사용될 것을 원한다.(프로토타입빈은 사용시마다 새로 생성할 것을 원하고 지정하므로)
         */
    }

    @Test
    void SingletonClientUsePrototypeProvider() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean2.class, PrototypeBean.class);
        ClientBean2 clientBean1 = ac.getBean(ClientBean2.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
        /*
        프로바이더를 사용하여 문제 해결. 각각 다른 PrototypeBean이 생성되는 모습.(시스템로그에 init메서드가 따로 실행됨)
         */
    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.count;
            return count;
        }
    }

    @Scope("singleton")
    static class ClientBean2 {
//        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;
//        /*
//        Provider를 사용하여 주입. DL(Dependency Lookup). 의존관계 주입(DI)이 아닌 의존관계 탐색.
//        ObjectProvider 대신 ObjectFactory를 사용해도 작동됨. Provider가 더 많은 편의기능 제공.
//        하지만 스프링에 의존한다는 단점이 있음.
//        */
//
//        @Autowired
//        ClientBean2(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
//            this.prototypeBeanProvider = prototypeBeanProvider;
//        }

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
        /*
        javax의 Provider사용. 자바 표준. 지금 딱 필요한 DL정도의 기능만 제공.
        스프링이 아닌 다른 컨테이너에서도 사용 가능.
         */

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.count;
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
