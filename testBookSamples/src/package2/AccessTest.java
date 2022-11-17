package package2;

import com.sun.tools.javac.Main;
//import samples.Main;
import java.awt.List;
import java.util.ArrayList;

public class AccessTest extends samples.Main.A {
    public AccessTest() {
        this.b = 5;
        java.util.List list = new ArrayList<>();
        List awtList = new List();

    }

    @Override
    protected void foo(int a){
        super.foo(1);
    }
}
