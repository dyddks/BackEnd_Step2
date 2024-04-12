public class Main {
    public static void main(String[] args) {
        int n1 = 1;
        int n2 = 2;
        int n3 = 1;
        test t = new test();
        // System.identityHashCode(변수명) => 실제 값이 저장되어있는 물리적인 메모리 주소값을 확인
        System.out.println(System.identityHashCode(n1));
        System.out.println(System.identityHashCode(n2));
        System.out.println(System.identityHashCode(n3));
        System.out.println(System.identityHashCode(t.a));
    }
}

class test{
    public int a = 1;
}