package cryptography.dictionaries;

import java.util.HashMap;
import java.util.Map;

public class OuterCircle {
    private OuterCircle() {}
    public static Map<Integer, Character> lettersInCircle = init();

    private static Map<Integer, Character> init (){

        return new HashMap<Integer, Character>();
    }

    public static Map<Integer, Character> getLettersInCircle() {
        return lettersInCircle;
    }
}
