# pengdolli-team11-mobile

## 수상
- 🏅GDSC 연합 해커톤: 눈꽃톤 최우수상 

## 팀원
### 안드로이드
|유진|박정민|황서아|
|:------:|:---:|:------:|
|`투표하기` `모아보기` `상세보기`|`홈` `3D 리소스 제작`|`카카오로그인`|
### 백엔드
|강승준|임형규|
|:------:|:---:|


## 서비스 소개
- **펭도리** : 기온에 따른 겨울철 실시간 옷차림 추천 서비스

### 시연
https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/1ea52a4b-1922-4f48-915c-097e65c77c94

### 발표자료
![눈꽃톤 11팀-01](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/80107c3c-02b5-4690-a3c2-bdd70c811680)
![눈꽃톤 11팀-02](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/bb9871d1-02e6-4060-b5f0-ab1ad8fa1c3f)
![눈꽃톤 11팀-03](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/d379ab7b-dd50-4f32-8033-c28236d2bb3a)
![눈꽃톤 11팀-04](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/a2db3e2c-ab28-423b-a708-a038edfd4495)
![눈꽃톤 11팀-05](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/4bfe5fc3-30b7-40e3-ba36-626ac6d6e885)
![눈꽃톤 11팀-06](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/819a166b-7f93-42b5-8f54-126c274ac253)
![눈꽃톤 11팀-07](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/9964f5fa-bee1-4dc5-ac6e-07dbee7428e5)
![눈꽃톤 11팀-08](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/a41e34fc-22c7-4b98-8b15-acdfb811be4f)
![눈꽃톤 11팀-09](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/0f9ce00a-3972-46a4-a709-a187ba6977dd)
![눈꽃톤 11팀-10](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/c9e61fc8-c6ad-46c5-a18d-185f080f416b)
![눈꽃톤 11팀-11](https://github.com/GDSC-snowflowerthon/pengdolli-team11-mobile/assets/94737714/e01a5d95-681b-45eb-b865-f747f1f1cde8)



<details>
<summary>Convention</summary>
<div markdown="1">

## Commit Message Convention

> 커밋태그: 내용 #이슈번호  

ex. `add: login 파일 추가 #20`

- 파일 추가 : add
- 버그 수정 : fix
- 리팩터링 : refactoring
- 파일 삭제 : remove
- 기능 추가 : feat
- 문서 수정 : docs
- 주석 추가 : comment



# Git convention

1. **Github Flow**
1. 작은 기능 하나 구현 할 때 마다 커밋하기  
2. issue는 큰 기능이나 한 화면 기준으로 큰 단위의 작업
3. PR은 작업 분량으로 정상작동 내용이 있다면 머지하는 식으로 작은 단위의 작업
4. 커밋 하나라도 했으면 PR 바로 하기
5. 깃헙 플로우의 생명은 빠른 merge (작은 구현 단위로 PR을 끊읍시다!)  
   한 PR에서 모든걸 다 하려고 하지 말아요

### Branch Convention
`{작업유형}/#{이슈 번호}` ex) feat/#3, fix/#4 ...

브랜치를 새로 생성할 때에는 꼭 최신 버전의 main 브랜치를 기점으로 생성하기

### Commit Message Convention
`{작업유형}: {작업내용} ` ex) add: login 파일 추가

- 파일 추가 : add
- 버그 수정 : fix
- 리팩터링 : refactoring
- 파일 삭제 : remove
- 기능 추가 : feat
- 문서 수정 : docs
- 주석 추가 : comment  

작은 기능 하나 구현 할 때 마다 커밋하기


### ISSUE Convention
`[{작업유형}] {작업내용}`
ex) [Feat] 리뷰 목록 

### PR Convention
`[{작업유형}/#{이슈번호}] 작업내용`
ex) [Feat/#3] 리뷰 쓰기 구현 완료  

### 메인에 있는 내용 머지하기（내 브랜치 최신화하기）

💡 팀원 A 브랜치에서 작업한 내용을 main 브랜치에 머지했다. 이는 main 브랜치에 들어간것이기에, 현재 내가 작업하는 B브랜치에는 내용이 존재하지는 않는다.  
그렇다면 내가 지금 작업하고 있는 B 브랜치에서 팀원 A가 main에 머지한 내용을 가져오고 싶다면 어떻게 해야할까?


```
git checkout main  
git pull //먼저 로컬의 메인 브랜치를 최신화 해준다.

git checkout B  
git merge main //b 브랜치에 main 내용 넣기
```

# Android convention

### layout id 규칙
`@+id/tv_login`  
`@+id/et_password`  
`@+id/btn_login`

_view 이름_
  - TextView -> `tv_`
  - ImageView -> `iv_`
  - EditText -> `et_`
  - Button, ImageButton -> `btn_` 
  - ConstraintLayout -> `cl_`
  - LinearLayout -> `ll_`
  - BottonNavitaionView -> bnv_

### layout 파일명
snake_case를 적용한다.  
- activity_기능: `activity_login`
- fragment_기능: `fragment_home`  
- item_기능: `item_user`  

### drawable 파일명
역시 xml 이기 때문에 snake_case를 적용한다. 자세한 내용은 아래를 참고한다.  
- icon은 “ic_” -> `ic_error`
- image는 “img_” -> `img_default_user`
- shape는 “shape_” -> `shape_border_radius10`
- selector는 “selector_” -> `selector_edittext_background`

### 아이콘 이름 규칙
`ic_기능_모양`  
1. 화살표 같은 경우는 ic_arrow_방향 -> `ic_back`, `ic_like`  
2. 선택 여부가 있는 아이콘은 ic_기능_모양과 ic_기능_모양_selected 로 구분  
3. 색은 안넣어도 될 거 같습니다 왜냐 코드에서 tint로 변경 가능 각자 변경해서 쓰기 ....  


### 클래스 파일명
UpperCamelCase 적용 하기 
`MainActivity.kt`, `UserViewModel.kt`, `WriteFragment.kt`, `UserInfo.kt`

### 함수명
동사형태로 작성하고, lowerCamelCase 적용하기  
`fun showList()`, `fun updateContacts()`  

### 변수명
명사형태로 작성하고, lowerCamelCase 적용  
`isEnd`(Boolean 타입 제외), `viewPagerAdapter`  

### 더미데이터 이름 규칙
`recyclerview 이름_숫자` -> `post_1`, `post_2`


### 코드 주석 규칙 (kt 파일에 사용)

1. 코드 위에 어떤 기능인지 설명 ex) 파이어베이스 연결, 좋아요 기능  
2. 공통적으로 사용하는 변수를 제외한 애들은 선언 옆에 // 이 주석을 사용해서 설명해주기  
3. 화면 전환 시 어느 화면에서 어느 화면으로 넘어가는지 설명  
4. 자세하게 써주기

</div>
</details>
