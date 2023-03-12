package InnerNestedClasses;


public class DotNewExtended extends DotNew {
    class Counter extends DotNew.Counter {
        @Override
        public void increment() {
            value += 2;
        }

        public Counter(int value) {
            super(value);
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
