package leetcode.editor.cn;

public class Test9 {

    public static void main(String args[]){
        int res = differentBetTwStr("ABABBB");
        System.out.println(res);

    }


        public static int differentBetTwStr(String s) {
            int n=s.length();
            int a [] =new int [n];
            int b [] =new int [n];
            int i=0;
            if(s.charAt(i)!='A'){
                a[i]++;
            }else{
                b[i]++;
            }
            for(i=1;i<n;i++){
                a[i]+=a[i-1];
                b[i]+=b[i-1];
                if(i%2==0){
                    if(s.charAt(i)!='A'){
                        a[i]++;
                    }else{
                        b[i]++;
                    }
                }else{
                    if(s.charAt(i)!='B'){
                        a[i]++;
                    }else{
                        b[i]++;
                    }
                }
            }
            int ans=Math.min(a[n-1],b[n-1]);
            if(n%2==0)
                return ans;
            int temp=0;
            for(i=0;i<n-1;i++){
                temp=a[i];
                temp+=b[n-1]-b[i];
                ans=Math.min(ans,temp);
                temp=b[i];
                temp+=a[n-1]-a[i];
                ans=Math.min(ans,temp);
            }
            return ans;
        }

}
