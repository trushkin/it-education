package LambdaExamples;

public class LambdaApp {
    private static int a = 10;
    private static int b = 1;

    interface NoArguments {
        void method();

        default void method2() {
        }

    }

     abstract class NoArgumentsClass {
        abstract void method();
        void method2() {
        }

    }

    interface OneArgument {
        int squareNum(int num);
    }

    interface TwoArguments {
        double division(double x, double y);
    }

    public static void main(String[] args) {
        NoArguments noArguments = () -> System.out.println("I am a method with no arguments");


//        NoArguments noArguments = new NoArguments() {
//            @Override
//            public void method() {
//                System.out.println("I am a method with no arguments");
//            }
//        };

        noArguments.method();

        OneArgument oneArgument = (int num) -> num * num;
        System.out.println(oneArgument.squareNum(11));
//        int s = 10;
        TwoArguments twoArguments = (x, y) -> {
            if (y == 0) {
                return 0;
            } else {
//                s++;
                return x / y;
            }
        };
        System.out.println(twoArguments.division(2, 9));

        noArguments = () -> System.out.println(a + b);
        noArguments.method();

    }
}
