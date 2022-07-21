package leetcode.editor.cn;

import java.util.*;


public class Test5 {
    static String[] sourceStrArray = {"opt","ko","max","ok","top"};
    static HashMap<String, ArrayList<String>> strArr = new HashMap<String, ArrayList<String>>();
    public static void main(String args[]) {
        ArrayList<String> ar = new ArrayList<String>();
        for (String s : sourceStrArray) {
            ar.add(s);
        }
        groupArr(ar);
        showArr(strArr);


    }



    public static void groupArr(ArrayList<String> arr) {
        HashMap<String, String> ha = new HashMap<String, String>();

        for (int i = 0; i < arr.size(); i++) {
            String stringByOrder = getStringByOrder(arr.get(i).toCharArray());
            if (ha.containsKey(stringByOrder)) {
                ArrayList<String> arrayList = strArr.get(stringByOrder);
                arrayList.add(arr.get(i));
                strArr.put(stringByOrder, arrayList);
            } else {
                ha.put(stringByOrder, arr.get(i));
                ArrayList<String> ar1 = new ArrayList<String>();
                ar1.add(arr.get(i));
                strArr.put(stringByOrder, ar1);
            }

        }
    }

    public static String getStringByOrder(char[] oldChar) {
        Arrays.sort(oldChar);
        return String.valueOf(oldChar);
    }
    private static void showArr(HashMap<String, ArrayList<String>> strArr2) {
        for (Map.Entry<String, ArrayList<String>> entry : strArr2.entrySet()) {
            for (int i = 0; i < entry.getValue().size() - 1; i++) {
                System.out.print(entry.getValue().get(i) + "|");
            }
            System.out.println(entry.getValue().get(entry.getValue().size()-1));

        }

    }
}



