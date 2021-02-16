## 문제 정의

1. 첫 째 줄에 N( 1 ≤ N ≤ 50), K ( 0 ≤ K ≤ 26) 이 주어진다.
2. 다음 열에 N 개의 단어가 주어지며, 각각의 단어는 반드시 "anta"로 시작하며 "tica"로 끝난다.
3. K 개의 알파벳을 학습했을 때 읽을 수 있는 단어의 최대 개수를 출력하라

## 문제 풀이

### 풀이 1 - 진짜 알파벳 조합 만들기

1. a, n, t, i , c 는 반드시 학습할 수 있어야하므로 k < 5 면 반환.
2. 위 5개의 글자를 제외한 21개의 글자로 k - 5개의 원소를 가지는 조합을 만든다. ( 21 C K - 5)
3. 각각의 조합을 완성할 때마다 해당 조합에 a n t i c 를 포함시킨 후 N개의 문자열을 읽을 수 있는지 판별하여 카운트한다.
4. 최대의 카운트를 출력한다.

### 풀이 2 - 알파벳 배열 대신 비트 마스킹하기

1. 각 단어에 알파벳이 몇 번이나 사용되었는 지는 고려되지 않는다. 사용인지 미사용인지만 구별하면 된다
2. 따라서 조합을 생성할 때 배열을 사용하지 않고 해당조합에 사용된 알파벳에 해당하는 인덱스를 마스킹한다.
3. 조합을 만들었으면 그 조합과 각 단어의 마스킹 값을 & 연산으로 도출할 수 있다. 단어에 마스킹된 값들이 모두 사용됐다면( & 연산으로 같은 결과가 도출됐다면) 해당 단어는 읽을 수 있는 것으로 센다.
4. 이 때 acint 는 모두 반드시 포함되야 하므로 시작하기전에 조합 마스킹에 넣는다. 또한 이미 5개가 채워졌으므로 5개가 채워진 것을 가정한 후부터 시작한다.

## 코드

```java
static int init() throws IOException{
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    int selected = 0;

    if(K < 5) return -1;

    for(char c : ACINT){
        selected |= 1 << (c - 'a');
    }
    words = new int[N];
    for(int i = 0 ; i < N ; i++){
        for(char c : br.readLine().toCharArray()){
            words[i] |= 1 << c - 'a';
        }
    }
    return selected;
}
```

- N, K 와 N 개의 단어를 받을 때 K < 5면 아무것도 읽을 수 없으므로 바로 리턴한다. 그 후 만들 조합의 마스크에 acint 를 모두 넣어 놓는다.
- N개의 단어를 받아 해당 단어에 해당하는 마스크에 사용된 알파벳을 마킹한다.

```java
static void comb(int idx, int idxStart, int flag){
    if(idx == K){
        check(flag);
        return;
    }

    for(int i = idxStart; i < CHAR_MAX; i++){
        if((flag & 1 << i) != 0) continue;
        comb(idx + 1, i + 1, flag | 1 << i);
    }
}
```

- 조합을 생성할 때 비트마스킹을 통해서 만들어낸다.

```java
static void check(int flag){
    int cnt = 0;
    for(int w : words){
        if((flag & w) == w) cnt++;
    }
    max = max > cnt ? max : cnt;
}
```

- 조합을 만들어내면 모든 단어와 비교하여 & 연산을 한다. & 연산으로 단어 w에 마스킹된 모든 알파벳이 flag에 존재하는지 확인한다. 존재한다면 카운트를 센다.

```java
public static void main(String[] args) throws IOException{
    int selected = init();
    if(selected != -1)  comb(ACINT.length, 0, selected);
    System.out.println(max);
}
```

- 조합을 실행할 때 미리 acint 를 넣어두고 acint 만큼이 이미 채워져 있으므로 acint.length부터 조합 생성을 시작한다.