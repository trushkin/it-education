package samples;

import java.util.Random;

class MyClass {
    public String string;

    {
        this.string = "2";
    }

    MyClass() {
        this.string = "1";
    }
    protected class A1{}

}

class Flower {
    int petalCount = 0;
    String s = "initial value";

    Flower(int petals) {
        petalCount = petals;
        System.out.println("Constructor int, petalCount = "
                + petalCount);
    }

    Flower(String ss) {
        System.out.println("Constructor String, s = " + ss);
        s = ss;
    }

    Flower(String s, int petals) {
        this(petals);
//! this(s); // Вызов другого конструктора запрещен!
        this.s = s;
// Другое использование "this" print("ApryMeHTbi String и int")j
    }

    Flower() {
        this("hi", 47);
        System.out.println("Default constructor");
    }

    void printPetalCount() {
//! this(ll); // Разрешается только в конструкторах!
        System.out.println("petalCount = " + petalCount + " s = " + s);
    }

}

public class Main {
    protected static class A{
        public A() {
        }
    }
    static class B{}
    protected int b = 10;
    protected void foo(int a){}
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        System.out.println(myClass.string);
        Flower x = new Flower();
        x.printPetalCount();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            System.out.println("37544" + random.nextInt(100000, 1000000));
        }
    }

}
