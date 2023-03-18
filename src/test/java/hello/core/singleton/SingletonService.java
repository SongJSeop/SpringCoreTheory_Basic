package hello.core.singleton;

public class SingletonService {

    // static 클래스 변수로 하나의 instance만 생성되게 함.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자로 외부에서 생성하지 못하도록. 오직 getInstance 메소드를 통해서만 인스턴스를 꺼낼 수 있음.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
