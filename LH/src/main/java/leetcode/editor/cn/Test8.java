package leetcode.editor.cn;

import java.util.Scanner;

public class Test8
{

    public static void main(String args[]){
        int T;
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        int[][] a = new int[101][4];
        int[] coun = new int[101];
        for(int i=0; i<T; i++)
            for(int j=0; j<4; j++)
                a[i][j] = scanner.nextInt();
        for(int i=0; i<T; i++)
            coun[i]=0;

        for(int i=0; i<T; i++)
        {
            coun[i]+=a[i][3];
            if(a[i][1]%2==0)
            {
                coun[i]+=a[i][1]/2;

                if(a[i][2]>=a[i][0])
                    coun[i]+=a[i][0];
                else
                {
                    coun[i]+=a[i][2];
                    coun[i]+=(a[i][0]-a[i][2])/4;
                }
            }
            else
            {
                coun[i]+=a[i][1]/2;

                if(a[i][2]>=a[i][0])
                    coun[i]+=a[i][0];
                else
                {
                    coun[i]+=a[i][2];
                    int t=a[i][0]-a[i][2];
                    if(t>=2)
                    {
                        coun[i]++;
                        t=t-2;
                        coun[i]+=t/4;
                    }
                }
            }
            System.out.println(coun[i]);

        }

        }

}

