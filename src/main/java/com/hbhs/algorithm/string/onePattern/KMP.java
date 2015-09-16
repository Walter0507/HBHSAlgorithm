package com.hbhs.algorithm.string.onePattern;


/**
 * KMP算法<BR>
 * 思想：每当一趟匹配过程中出现字符比较不等，不需要回溯i指针，  
 * 而是利用已经得到的“部分匹配”的结果将模式向右“滑动”尽可能远  
 * 的一段距离后，继续进行比较。 <BR>
 *  
 * 时间复杂度O(n+m) 
 * @author walter.xu
 *
 */
public class KMP {
	public static void main(String[] args) {
		System.out.println(kmp("ababcababdef", "ababd"));
	}
	
	/**
     * kmp主算法,算法如下:
     * run kmpInit(p)
     * q<-0
     * For i<-0 to text.length Do
     *     while q>0 and p[q]!=text[i] Do q<-pi[q-1]
     *     If p[q]=text[i] Do q<-q+1
     *     if q=m Then
     *         write "在i-m+1处出现模式串"
     *         q<-pi[q-1]
     *     End if
     * End For
     * 算法时间复杂度为O(text.length+p.length)
     * 
     * @param text
     * @param p
     */
    public static int kmp(String text, String p){
        int n = text.length(), m = p.length();
        int q = 0;
        int[] pi = kmpInit(p);
        for(int i = 0; i < n; ++i){
                while(q > 0 && p.charAt(q) != text.charAt(i))q = pi[q-1];
                if(p.charAt(q) == text.charAt(i))++q;
            if(q == m){
//                System.out.println(i-m+1);
//                q = pi[q-1];
            	return i-m+1;
            }
        }
        return -1;
    }
	
	/**
     * 对模式串p进行预处理,计算其前缀函数,算法如下:
     * k<-0
     * pi[0]<-0
     * For i<-1 to p.length do
     *     while k>0 and p[k]!=p[i] Do k<-pi[k-1];
     *     If P[k]=p[i] Then k<-k+1
     *     pi[i]<-k
     * End For
     * 算法时间复杂度为O(p.length)
     * 
     * @param p
     * @return int[]
     */
    public static int[] kmpInit(String p){
        int[] pi = new int[p.length()];
        pi[0] = 0;
        int k = 0;
        for(int i = 1; i < p.length(); ++i){
            pi[i] = 0;
            while(k > 0 && p.charAt(k) != p.charAt(i))
                k = pi[k-1];
            if(p.charAt(k) == p.charAt(i))++k;
            pi[i] = k;
        }
        return pi;
    }
}
