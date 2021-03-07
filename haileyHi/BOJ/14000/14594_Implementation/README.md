# 14595번 동방 프로젝트(Small)
[문제 보러가기](https://www.acmicpc.net/problem/14594)

## 🅰 설계


![image](https://user-images.githubusercontent.com/23499504/109512481-7beb8a00-7ae7-11eb-917b-e3a3cb1d5e66.png)

겹치는 부분 제외하고 처음 부수는 부분이면 빼 버리기!


## 전체 코드
```java
public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int commandNum = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int x, y;
        int res = N;
        int[] rooms = new int[N + 1];

        for (int i = 0; i < commandNum; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            for (int j = x + 1; j <= y; ++j) {
                if (rooms[j] == 0) {
                    rooms[j] = 1;
                    --res;
                }
            }

        }

        System.out.println(res);
    }

```
## ✅ 후기
없습니다.