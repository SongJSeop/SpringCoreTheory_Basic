package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();
        // beanA는  includeFilters이므로 포함 됨.

        // beanB는 excludeFilters이므로 포함되지 않게.
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            // 여기서 type = FilterType.ANNOTATION 은 기본값이라 사실 생략해도 괜찮음.
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)

            /* @Filter(type = ?)의 또 다른 옵션(2가지 이상의 옵션을 동시에 사용 가능)
                ASSIGNABLE_TYPE: 지정한 타입(클래스)과 자식 타입을 인식해서 동작
                 -> (예: BeanA도 빼고싶다면 type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
                ASPECTJ: AspectJ 패턴 사용
                REGEX: 정규 표현식
                CUSTOM: 'TypeFilter'라는 본인만의 인터페이스를 구현해서 처리
             */
    )
    static class ComponentFilterAppConfig {

    }
}
