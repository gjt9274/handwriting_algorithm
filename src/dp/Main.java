package dp;
import com.sun.deploy.perf.DefaultPerfHelper;

import java.util.*;

public class Main{

    public static void main(String[] args) {
        System.out.println(sub("1031","102"));
        System.out.println(sub("0", "100"));
        System.out.println(sub("10","100"));
    }
    public static String sub(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 < len2) {
            return "-" + sub(s2, s1);
        }else if (len1 == len2) {
            if (s1.compareTo(s2) < 0) {
                return "-" + sub(s2, s1);
            }
        }
        int i = len1 - 1, j = len2 -1;
        StringBuilder res = new StringBuilder();
        int r = 0, diff;
        while (j >= 0) {
            int a = Integer.parseInt(s1.substring(i, i + 1));
            int b = Integer.parseInt(s2.substring(j, j + 1));
            diff = a - b - r;
            if (diff >= 0) {
                res.append(diff);
                r = 0;
            } else if (diff != -10) {
                res.append(10 + diff);
                r = 1;
            }
            i--;
            j--;
        }
        while (i >= 0) {
            int a = Integer.parseInt(s1.substring(i, i + 1));
            diff = a - r;
            if (diff > 0) {
                res.append(diff);
                r = 0;
            }else if (diff < 0) {
                res.append(10 + diff);
                r = 1;
            }
            i--;
        }
        return res.reverse().toString();
    }

}