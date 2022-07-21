package leetcode.editor.cn;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test2
{

    public static void main(String args[]) throws ParseException {
        String dates = "2019-1-10,,,2019-3-10,2018-7-31";
        String[] split = dates.split(",");
        List<Date> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if(!split[i].equals("")){
                Date date = simpleDateFormat.parse(split[i]);
                list.add(date);
            }else {
                list.add(null);
            }
        }
        //空值占比
        Double countNull = 0.0;
        for (int i = 0; i < list.size(); i++) {
            Date date = list.get(i);
            if(date == null) {
                countNull++;
            }
        }
        System.out.println(list.size());
        System.out.println(countNull/(list.size()));
        
        list.sort(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                if (o1 == null || o2 == null) return o1 == null ? 1 : -1;;
                return o1.compareTo(o2);
            }
        });
        for (int i = 0; i < list.size(); i++) {
            Date date = list.get(i);
            if(date != null) System.out.println(date.toString());
            else System.out.println("");
//            if(date != null) {
//                Calendar cld = Calendar.getInstance();
//                cld.set(Calendar.YEAR,date.getYear());
//                cld.set(Calendar.MONTH,date.getMonth());
//                cld.set(Calendar.DATE,date.getDay());
//                cld.add(Calendar.DATE,-90);
//                Date time = cld.getTime();
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String format = simpleDateFormat.format(date);
//                System.out.println(format);
//
//            }
             }
    }
}
