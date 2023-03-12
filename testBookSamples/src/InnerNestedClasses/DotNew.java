package InnerNestedClasses;

public class DotNew {
    interface Dog {
        void run();

    }

    class Counter {
        // Immutable object example
//        final int value;
//
//        public Counter increment() {
//            return new Counter(value + 1);
//        }

        protected int value;

        public void increment() {
            value++;
        }

        public Counter(int value) {
            this.value = value;
        }
    }

    public class Inner {

    }

    void foo() {
    }


    public static void main(String[] args) {
//        DotNew dn = new DotNew();
//       // final int b = 10;
//        final Integer b1 = 11;
//        final DotNew.Counter counter = dn.new Counter(b);
//        Dog d = new Dog() {
//            @Override
//            public void run() {
//                System.out.println(counter.value);
//            }
//        };
//        d = () -> System.out.println(counter.value);
//
//        counter.increment();
//        d.run();
//        DotNew.Inner dni = dn.new Inner();
//        DotNewExtended dotNewExtended = new DotNewExtended();
//        DotNewExtended.Counter counter2 = dotNewExtended.new Counter(b);
//        d = () -> System.out.println(counter2.getValue());
//        counter2.increment();
//        d.run();
//
        boolean a = true;
        boolean b = false;
        b &= true; b = b && true;
        boolean c = true;
        //какой порядок выполнения логических операций
        if (a && b || c && !b){
            System.out.println("hello");
        }
        if ((2 + 3 == 1) && ((3 == 3) || (5 == 5))){
            System.out.println("hello");
        }




    }
}
