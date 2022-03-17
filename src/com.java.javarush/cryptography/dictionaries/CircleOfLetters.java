package cryptography.dictionaries;



public class CircleOfLetters {
    private final char[] alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!? ".toCharArray();
    private int normalKey;

    public char[] getAlphabet() {
        return alphabet;
    }

    public int getNormalKey() {
        return normalKey;
    }

    public void setNormalKey(int userKey) {
        this.normalKey = normalizeKey(userKey);
    }

    private int normalizeKey(int userKey) {
        int key;
        if (userKey > alphabet.length) {
            key = Math.abs(userKey % alphabet.length);
        } else key = userKey;
        return key;
    }
}