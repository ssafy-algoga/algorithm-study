# 1202번 보석 도둑
[문제 보러가기](https://www.acmicpc.net/problem/1202)

## 🅰 설계

보석 훔치는 시간이 너무 오래 걸려서 코드를 꽤 오래 수정했습니다.😂


처음에는 다음처럼 설계했습니다.
```java
int[] bag = new int[K];
List<Jewel> list = ArrayList<>();

```

1. 가방과 보석을 각각 담을 수 있는 용량과 무게를 기준으로 오름차순으로 정렬하고
2. 가벼운 용량의 가방부터 담을 수 있는 무게 이하의 보석 가운데 가장 무거운 보석의 가격과 idx를 갱신해서 저장한 다음
3. 현재 용량보다 무거운 보석이 나타나면 현재 idx가 가리키는 보석을 없애고 tmp를 sum에 더하기
(sum 최대는 300,000 * 1,000,000니까 long)  *int 최대 범위 2,147,483,647*

위 방법대로 짠 코드는 15프로에서 시간 초과로 진행되지 못했습니다...

가지치기로 시간을 단축시켜보려 했지만 잘못된 가지치기로 오답이 나타나는 현상만 나타났습니다.💀💀

연주언니의 조언에 따라 우선순위 큐를 도입하고 문제를 해결할 수 있었습니다! (짱)

처음엔 우선순위 큐도 어떻게 사용해야 할 지 헷갈렸지만
우선순위 큐의 정렬 기준이 보석의 가격일 때
2번의 담을 수 있는 무게 이하의 보석 저장을 pq.offer(해당하는 보석);
3번의 보석 얻기를 pq.poll();로 하면 해당 가방이 담을 수 있는 용량 내에서 담을 수 있는 최대 무게를 얻을 수 있다는 걸 알 수 있었습니다.

![image](https://user-images.githubusercontent.com/23499504/109537597-66378e00-7b02-11eb-95e0-3f7843f33799.png)
![image](https://user-images.githubusercontent.com/23499504/109537714-9121e200-7b02-11eb-96cf-d9181391991b.png)

헷갈려서 그려봤어요

## 전체 코드
```java
public static class Jewel {
        int weight;
        int value;

        public Jewel(int w, int v) {
            this.weight = w;
            this.value = v;
        }
    }

    public static int N, K;
    public static int[] bag;
    public static Jewel[] jewels;
    public static PriorityQueue<Jewel> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bag = new int[K];
        jewels = new Jewel[N];
        pq = new PriorityQueue<Jewel>((a,b) -> b.value - a.value);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels, Comparator.comparingInt(o -> o.weight));
        Arrays.sort(bag);

        long sum = 0;
        int idx = 0;
        for (int i = 0; i < K; i++) {
            int capa = bag[i];
            while (idx < N && jewels[idx].weight <= capa){
                pq.offer(jewels[idx++]);
            }

            if (!pq.isEmpty()) {
                sum += pq.poll().value;
            }

        }
        System.out.println(sum);
    }

```
## ✅ 후기
우선순위 큐를 특정 기준으로 정렬하려면 comparator의 compare 사용한다는 걸 잊지 않을 것 같습니다! 적재적소에 쓸 수 있도록 잘 익혀둬야겠습니다.
시간 복잡도 낮출 방법을 떠올리는 건 아직 너무 어려워요..
~시간 초과는 너무 슬프다~