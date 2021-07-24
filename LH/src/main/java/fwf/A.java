package fwf;

public class A {
    public A foo() {
        System.out.println("a.foo");
        return this;
    }

    public static void main(String args[]){
        C c = new C();

        }
}
class B extends A{
    public A foo() {
        System.out.println("b.foo");
        return this;
    }
}
class C extends B{
//    public A foo(B b) {
//        System.out.println("c.foo");
//        return b;
//    }


//    public A foo() {
//
//    }
}
