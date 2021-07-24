package fwf;

public class Gap
{
    public static void main(String args[]){

        int[] e = {1,2,3,4,5};
        int[] c = {3,4,5,1,2};
        int i = canCompleteCircuit(e, c);
        System.out.println(i);

    }
    public static int canCompleteCircuit(int[] e, int[] c) {
        int i,j;
        for(i=0;i<e.length;i++){
            int[] circle=new int[e.length];
            int temp=i;
            int rest=0;
            for(j=0;j<e.length;j++){
                if(temp>=e.length)
                    temp=0;
                circle[j]=temp;
                temp++;
                rest=rest+e[circle[j]]-c[circle[j]];
                if(rest<0){
                    break;
                }
            }
            if(j==e.length){
                return i;
            }
        }
        return -1;
    }
}
