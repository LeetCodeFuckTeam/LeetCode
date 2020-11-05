package leetcode.editor.cn;//实现 strStr() 函数。
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。 
//
// 示例 1: 
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
// 
//
// 说明: 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。 
// Related Topics 双指针 字符串 
// 👍 613 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int strStr(String haystack, String needle) {
        //使用RK算法（hashcode）
        if(needle.length()==0||(haystack.equals(needle)))
            return 0;

        //主串的长度
        int m=haystack.length();
        int n=needle.length();
        //计算模式串(子串)的hash值
        int needlecode=hashcode(needle);
        //计算主串中第一个和模式串相等长度的hacode的值
        int haycode=0;
        if(m>n)  //必须加这个条件  否则当haystack的长度本来就小于模式串的话就会报越界异常
         haycode=hashcode(haystack.substring(0,n));
        for (int i = 0; i <m-n+1 ; i++) {
            //首先值要相等  然后字符的顺序相等
            if((haycode==needlecode)&&comparestring(i,haystack,needle)){
                return i;
            }
            //计算下一个模式串长度的hacode值
            if(i<m-n)
                haycode=nexthashcode(haystack,haycode,i,n);
        }

    return  -1;

    }
    static int hashcode(String needle){
        int hacode=0;
        for (int i = 0; i < needle.length(); i++) {
            hacode+=needle.charAt(i)-'a';
        }
        return hacode;
    }
    //在主串中不用每次计算重新计算目标串长度的hacode，可以减去首部增加尾部
    // hacode: 当前hacode的值 index :当前的索引下标 n：子串的长度

    static int nexthashcode(String haystack,int hacode,int index,int n) {

        hacode=hacode-(haystack.charAt(index)-'a')+(haystack.charAt(index+n)-'a');
        return hacode;
    }
    static boolean comparestring(int i,String haystack,String needle){
      String str = haystack.substring(i,i+needle.length());
      return  str.equals(needle);
        }
}
//leetcode submit region end(Prohibit modification and deletion)
