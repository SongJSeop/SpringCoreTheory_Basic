package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");  // @Setter 효과

        String name = helloLombok.getName();  // @Getter 효과
        System.out.println("name = " + name);
        System.out.println(helloLombok);  // @ToString 효과
    }
}
