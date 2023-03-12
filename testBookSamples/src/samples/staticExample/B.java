package samples.staticExample;

public class B extends A {
    @Override
    public void method2() {
        System.out.println("Override method 2");
    }

   class StaticSuper {
        public static String staticGet() {
            return "Базовая версия staticGet()";
        }

        public String dynamicGet() {
            return "Базовая версия dynamicGet()";
        }

    }

    class StaticSub extends StaticSuper {
        public static String staticGet() {
            return "Производная версия staticGet()";
        }

        public String dynamicGet() {
            return "Производная версия dynamicGet()";
        }
    }

    public void testExample() {
        B.StaticSuper sup = new B.StaticSub(); // Восходящее преобразование System.out.println(sup.staticGet());
        System.out.println(StaticSuper.staticGet());
        System.out.println(sup.dynamicGet());
    }

    public static void main(String[] args) {

        A a = new A();
        A b = new B();


        a.method1();
        b.method1();
        ((B) b).testExample();
//        B asd = new B();
//        asd.testExample();

    }
}

