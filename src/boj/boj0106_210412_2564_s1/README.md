## 문제 정의

1. `R` * `C` 직사각형 모양의 가장자리에 동근이와 상점 `N` 이 존재합니다 (1 ≤ R, C, N ≤ 100)
2. 상점의 정보는 가장자리 값(1 : 북 , 2 : 남 , 3 : 서, 4: 동), 해당 가장자리에서 존재하는 인덱스 값이 주어집니다.
3. 동근이는 가장자리로 밖에 움직일 수 없을 때 각 상점과의 최소거리 합을 출력합니다.

## 문제 풀이

1. 입력에 맞게 각 상점 및 동근이의 좌표를 구해줍니다. 이 때 직사각형을 일자로 편 모양으로 하여 두 직사각형을 편 모양을 두개 붙인 모양의 좌표를 붙여줍니다.
2. 직선은 북 동 남 서 북 동 남 서 순으로 구성되며 북과 남은 가로의 길이, 서와 동은 세로의 길이를 갖게 됩니다.
3. 곧게 펴진 모양으로 만들어야 하므로 남과 서는 좌표를 뒤집은 모양으로 만들어줍니다.

## 코드

```java
private static void init() throws IOException{
    st = new StringTokenizer(br.readLine());
    C = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(br.readLine());

    length = R * 2 + C * 2;
    stores = new int[N + 1];

    Map<Integer, Integer> map = new HashMap<>();
    map.put(1, 0);
    map.put(4, C);
    map.put(2, length - R);
    map.put(3, length);

    for (int i = 0; i <= N ; i++) {
        st = new StringTokenizer(br.readLine());
        int dir = Integer.parseInt(st.nextToken());
        int dist = Integer.parseInt(st.nextToken());
        stores[i] = map.get(dir) + (dir / 2 == 1 ? -dist : + dist);
    }
}

private static int solve(){
    int ret = 0;
    for (int i = 0; i < N ; i++) {
        int sub = stores[N] - stores[i];
        ret += Math.min(Math.abs(sub)
                , Math.min(Math.abs(sub - length), Math.abs(sub + length)));
    }
    return ret;
}
```