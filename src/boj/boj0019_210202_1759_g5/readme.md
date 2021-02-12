## 문제 정의

1. 암호의 글자 수 L, 암호가 될 가능성이 있는 글자수 C 가 주어진다 (3 ≤ L ≤ C ≤ 15)
2. 다음열에 암호가 될 수 있는 문자 C 개가 공백을 기준으로 주어진다.
3. 암호가 될 수 있는 모든 경우의 암호를 출력한다. 이 때 암호는 알파벳 내림차순으로 정렬되어 있다.

## 설계

1. 알파벳을 입력 받은 후, 알파벳 내림차순으로 암호가 될 수 있는 알파벳의 배열을 정렬한다.
2. 중복되지 않는 조합이므로 재귀를 통해 c C l의 조합을 뽑는다.
3. 이 때 만들어진 조합이 자음 2개와 모음 1개를 포함하는지 확인한다.
4. 조건에 해당하는 조합을 출력한다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1759 {

    private static int L;
    private static int C;
    private static char[] srcArr;
    private static char[] resArr;

    private static final char[] mo = { 'a', 'e', 'i', 'o', 'u'};
    private static final char[] ja = {
            'b', 'c', 'd', 'f', 'g',
            'h', 'j', 'k', 'l', 'm',
            'n', 'p', 'q', 'r', 's',
            't', 'v', 'w', 'x', 'y',
            'z'};
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static boolean contains(char[] obj1, char[] obj2, int cnt){
        int res = 0;
        for(int i = 0; i < obj1.length; i++){
            for(int j = 0 ; j < obj2.length; j++){
                if(obj1[i] == obj2[j]){
                    if(++res == cnt )   return true;
                }
            }
        }
        return false;
    }

    private static void f(int idx, int idxStart){
        if(idx == L){
            if(contains(mo, resArr, 1) && contains(ja, resArr, 2))
                sb.append(resArr).append("\n");
            return ;
        }

        for(int i = idxStart ; i < C ; i++){
            resArr[idx] = srcArr[i];
            f(idx + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        srcArr = new char[C];
        resArr = new char[L];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < C ; i++ ){
            srcArr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(srcArr);
        f(0, 0);
        System.out.println(sb);
    }
}
```