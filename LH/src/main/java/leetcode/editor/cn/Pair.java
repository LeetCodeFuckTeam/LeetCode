package leetcode.editor.cn;

public class Pair<T> {
    private T c1;
    private T c2;

    public Pair(T c1, T c2) {
        this.c1 = c1;
        this.c2 = c2;
    }


    public static <T> Pair<T> makePair(Class<T> t1) throws IllegalAccessException, InstantiationException {

        return new Pair(t1.newInstance(),t1.newInstance());
    }
    public static void main(String args[]) throws InstantiationException, IllegalAccessException {
        Pair<String> stringPair = Pair.makePair(String.class);

    }
}
