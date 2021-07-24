package fwf;


public class NMS {


    public static void main(String args[]){
        int[] bills = {5,5,5,10,20};
        int b = lemonadeChange(bills);
        System.out.println(b);


    }
    public static int lemonadeChange(int[] bills) {
        int five = 2, ten = 0;
        int count = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
                count++;
            } else if (bill == 10) {
                if (five == 0) {
                    break;
                }
                five--;
                ten++;
                count++;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                    count++;
                } else if (five >= 3) {
                    five -= 3;
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }

}
