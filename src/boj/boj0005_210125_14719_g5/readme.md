## 문제 정의

1. 블록이 쌓여있다고 가정할 때 비가 온다면 고이는 빗물의 총량이 얼마일까?
2. 빗물은 벽과 벽 사이에서만 고일 수 있고 바닥은 막혀있다고 가정하며 맵의 외부는 벽으로 판단하지 않는다.

## 입출력

1. 맵의 사이즈 row = H, col = W : 1 ≤ H,W ≤ 500
2. 첫 row 부터 아래부터 몇개의 블록이 쌓였는지 col 개의 입력 
3. 출력 : 빗물이 고일 수 있는 양

## 풀이 도출

1. 위와 아래 벽을 제외한 양쪽 벽으로 막혀있는 면적을 구한다.
2. 아래의 row부터 시작하여 벽을 만날때마다 그 벽의 오른쪽으로 반복하여 다음 벽까지의 거리를 구하여 count

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1fcd6ace-859c-47e4-be43-0a254e325648/_2021-01-25__7.20.21.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1fcd6ace-859c-47e4-be43-0a254e325648/_2021-01-25__7.20.21.png)

## 풀이 과정

1.  모든 row와 col 에서 반복한다. col에서 반복하다가 벽을 만나면 flag를 통해 벽이 있음을 알린다.
2.  flag가 있으면 한쪽 벽으로 막혀있고 보고, 빈 공간은 물이 찰 수 있는 공간으로 보아 flag == true 일때 그 오른쪽의 빈공간을 count
3. 다음 벽을 만났을때 count 된 모든 빈 공간은 갇혀있다고 보고 answer에 +한다. 반대쪽 벽이 막혀있지 않으면 버린다
4.  매 반복마다 flag와 count를  초기화 해주어 해당 row에 대한 값만 계산한다.

## 코드

```jsx
package a0005_210125_14719_g5;

import java.util.Scanner;

public class myProblem.Elevator {
    static boolean[][] map;                     //벽의 위치를 표시할 맵
    static int answer;                          //제출 답
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int row = sc.nextInt();                 //맵 크기 받기
        int col = sc.nextInt();
        int input ;

        map = new boolean[row][col];            //맵 할당

        for(int c = 0; c < col ; c++){          //매 col 당 벽돌이 몇 개 쌓이는지
            input = sc.nextInt();               //입력받기
            for(int r = row - 1, j = 0; j < input ; r--, j++) // 벽돌이 아래에서부터 쌓이므로 아래에서부터 입력값의 개수까지 반복
                map[r][c] = true;               //벽돌이 있으면 true
        }

        /**
         * 1. 모든 row와 col 에서 반복한다. col에서 반복하다가 벽을 만나면 flag를 통해 벽이 있음을 알린다.
         * 2. flag가 있으면 한쪽 벽으로 막혀있고 보고, 빈 공간은 물이 찰 수 있는 공간으로 보아 flag == true 일때 그 오른쪽의 빈공간을 count
         * 3. 다음 벽을 만났을때 count 된 모든 빈 공간은 갇혀있다고 보고 answer에 +한다. 반대쪽 벽이 막혀있지 않으면 버린다
         * 4. 매 반복마다 flag와 count를  초기화 해주어 해당 row에 대한 값만 계산한다.
         */
        for(int r = 0; r < row ; r++) {         //모든 row에서 반복
            boolean flag = false;               //flag 및 cnt 초기화
            int cnt = 0;
            for(int c = 0 ; c < col ; c++) {    //매 col에서 반복
                if(map[r][c]) {                 //벽이 있으면
                    flag = true;                //한쪽벽이 막혀있음을 flag
                    answer += cnt;              //지금까지 빈 공간의 수를 모두 더한다(반대쪽이 막혀있지 않다면 cnt는 0이다
                    cnt = 0;                    //더한 후 cnt를 초기화한다.
                }
                else if(!map[r][c] && flag)     //flag가 true이고(한쪽 벽이 막혀있고), 빈 공간이라면 cnt++
                    cnt++;
            }
        }
        System.out.println(answer);

    }
}
```

## 후기

단순한 문제인 것으로 보고 도전했는 데 정말 단순했다. 빗물이 고이는 면적을 구하는 규칙만 생각하면 다른 조건은 고려할 것이 없다. 한쪽 벽과 다른쪽 벽이 둘다 막혀있는 row에서의 공간을 구해 더해주기만 하면 된다.