package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 원래의 AppConfig를 스캔하는 것을 방지하기 위해 Configuration 클래스들은 스캔에서 제외.
        // 실무에서는 이렇게 제외하는 경우는 거의 없지만 기본 예제 코드를 유지하기 위해 제외해주는 것.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}