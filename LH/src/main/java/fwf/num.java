package fwf;

public class num {
    static int i = 0;
    public static int devide(int num1,int num2) {
        try {
            i = num1/num2;
        }catch (Exception e) {
            return ++i;
        }
        finally {
            return ++i;
        }
    }
    public static void main(String args[]){
        System.out.println(devide(5,1));
        }
}
