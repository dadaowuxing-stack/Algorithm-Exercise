
import com.fengshuoliu.BruteForce02;
import com.fengshuoliu.KMP;
import com.fengshuoliu.tools.Asserts;

public class Main {
    public static void main(String[] args) {
        Asserts.test(BruteForce02.indexOf("Hello World", "or") == 7);
        Asserts.test(BruteForce02.indexOf("Hello World", "d") == 10);
        Asserts.test(BruteForce02.indexOf("Hello World", "lds") == -1);

        Asserts.test(KMP.indexOf("Hello World", "or") == 7);
        Asserts.test(KMP.indexOf("Hello World", "d") == 10);
        Asserts.test(KMP.indexOf("Hello World", "lds") == -1);
    }
}