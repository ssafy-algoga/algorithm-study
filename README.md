# Algorithm Study
### 떨지말고 떨게해라 😎
<p align=center>
<img src="https://cdn.uc.assets.prezly.com/2619a11f-0441-48af-8784-36dc8e204bcf/-/resize/1200x/-/format/auto/" height=500>
</p>

## 💖 1. What
Baekjoon, Programmers 등에서 Baekjoon 기준 from 실버 to 골드인 문제를 선별해서 풉니다. 

<h4>
    <a href="https://www.notion.so/4b5b2f9465c94258af50d5a836b429cc?v=9677bbf83b0d43eda686ddd500a4087d">
        😎 이번 주 풀어야할 문제 보러가기
    </a>
</h4>
<h4>
    <a href="https://www.notion.so/48f4281db6344a53ad3092c226a16461"> 
        😗 풀고 싶은 문제 추천하러 가기 
    </a>
</h4>

## 💖 2.  How
-`Pull Request` 자기 계정으로 해당 Repository fork 하기
- 주말에 그 다음주에 풀 문제 정하기
- 화요일에 전체 실시간 리뷰하기

## 💖 3. Convention
저희는 다음과 같은 Convention을 지키는 걸 지향합니다

### ✅  Code Convention
#### 코드 마다 이 코드는 **어떤 목적**으로 작성되었는지 주석을 답니다.
#### 변수와 함수 이름은 어떤 역할을 하는지 알 수 있도록 붙입니다.
#### code 마지막 줄에는 한 줄을 비웁니다. git add + git commit 하기 전에 확인해보는 걸 추천합니다.
    
    [왜?](https://github.com/ssafy-algoga/algorithm-study/pull/9#discussion_r564339257)

### ✅ Commit Convention
#### 한 번에 git add . 하는 것보다 commit type에 맞게 분리하는 걸 지향합니다.
```
docs : README.md 등 문서 작성 및 수정
code : 코드 작성
fix : 코드 수정
add : 기존에 푼 문제 대한 또 다른 솔루션 코드 추가
merge : 내 레포에서 올린 pull request를 현재 organization의 alogorithm-study 레포에 합치기
```
commit type 'code'인 경우 commit message에는 다음과 같은 정보를 명시하는 걸 지향합니다.
```
git commit -m "code : 자기이름 문제플랫폼 문제번호 문제유형 문제이름"  
```
예시는 다음과 같습니다.

곰팅이라는 사람이 있습니다. 곰팅이의 github ID는 gomting2입니다. 곰팅이는 백준에서 다이나믹 프로그래밍 유형인 1000번을 풀엇습니다.
우선 코드를 하나의 커밋으로 분리합니다.
```bash
git add answer.java
git commit -m "code : boj 1000"
```
코드에 대한 설명을 작성한 문서를 하나의 커밋으로 분리합니다. 
```bash
git add README.md
git commit -m "docs : boj 1000"
```

### ✅ Review Convention
#### Pull Request 를 작성할 때 제목에는 "본인 이름: 문제플랫폼 문제번호 문제유형 문제이름"을 작성하는 것을 지향합니다.
```
곰팅이 : BOJ 1000 dp 꿀단지를 찾아서
```
#### Pull Request 를 작성할 때 메시지에는 "본인이 작성한 README.md의 내용"을 추가하는 것을 지향합니다.
```
열심히 풀었습니다 (X)
```
#### 자신이 푼 문제 유형을 자신의 pull request에 label을 붙입니다.

#### 자신의 pull request의 assignee에 자신을 추가합니다.

#### 자신이 받고 싶은 review 받고 싶은 reviewr가 있을 경우, 자신의 pull request에 reviewer로 추가합니다.

#### 1개의 Pull request에는 1개의 문제관련 commit만 추가하는 것을 지향합니다. 

#### 기존에 Pull Request를 작성했지만 새로운 문제를 풀었을 경우, 새로운 문제에 대한 commit을 하기 전 다음과 같은 과정을 수행하는 것을 지향합니다.
#### 상황1. 자신의 PR에 대한 적절한 리뷰를 받았다고 생각했을 경우,
1. 해당 오가니제이션의 레포의 github issue에 자기가 푼 문제에 대한 issue를 생성합니다. 이슈 제목에는 "본인 이름: 문제플랫폼 문제번호 문제유형 문제이름"을 작성하는 것을 지향합니다.
```
곰팅이 : BOJ 1000 dp 꿀단지를 찾아서
```
2. 자신이 작성했던 Pull Request에서 초록색 merge버튼을 눌러서 merge합니다.

3. 자신이 작성했던 Pull Request에서 issue로 앞서 생성한 이슈를 연결합니다. 이슈는 닫히지 않았는지 확인합니다.

#### 상황2. 자신의 PR에 대한 적절한 리뷰를 받지 못했지 생각했을 경우,
1. 자신의 로컬에서 clone한 폴더에서 새로 푼 문제에 대한 브랜치를 생성합니다. (git checkout과 branch 이용)
브랜치 이름은 다음과 같은 이름을 지향합니다.
```
git checkout -b feature/문제플랫폼-문제번호-문제유형

IMplay@Joylish-LAPTOP MINGW64 /c/SSAFY/2_Java/workspace/hw/0127 (main)
git checkout -b feature/boj-2000-greedy

IMplay@Joylish-LAPTOP MINGW64 /c/SSAFY/2_Java/workspace/hw/0127 (feature/boj-2000-greedy)

```
2. 새로운 문제에 대한 code와 README.md에 대한 commit을 추가하고 push합니다. 이 때 반드시 터미널에서 브랜치 이름이 지정되어있는지 확인합니다.
```bash
IMplay@Joylish-LAPTOP MINGW64 /c/SSAFY/2_Java/workspace/hw/0127 (feature/boj-2000-greedy)
git commit -m "code : boj 2000"

IMplay@Joylish-LAPTOP MINGW64 /c/SSAFY/2_Java/workspace/hw/0127 (feature/boj-2000-greedy)
git commit -m "docs : boj 2000"

IMplay@Joylish-LAPTOP MINGW64 /c/SSAFY/2_Java/workspace/hw/0127 (feature/boj-2000-greedy)
git push
```
3. 본인 계정에 있는 algorithm-study 레포에서 pull request를 작성할 때, 자신이 push한 브랜치(feature/boj-2000-greedy)를 현재 oraganization의 레포의 브랜치(main)에 pull request에 보냅니다.
















