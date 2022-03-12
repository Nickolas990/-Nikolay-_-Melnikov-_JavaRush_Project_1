package cryptography.dictionaries;



public class CircleOfLetters {
    private CircleOfLetters() {}


    public static char[] init (){
        char[] circle = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,””:-!? ".toCharArray();
        return circle;
    }

}
