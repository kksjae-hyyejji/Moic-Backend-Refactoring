# Software Architecture

![SoftArchitecture](https://github.com/kksjae-hyyejji/Moic-Backend-Refactoring/assets/87571953/a5af2e3b-935b-4c1c-a02c-cf6f3572d50f)

## 클린 아키텍처 적용 이유

- 계층 간의 모든 의존성을 안쪽으로 향하게 하여 변경 가능성을 줄입니다.
    - 상위 계층의 변화가 하위 계층에 변화를 주지 않도록 합니다.

![Package](https://github.com/kksjae-hyyejji/Moic-Backend-Refactoring/assets/87571953/d14c6a5a-075e-4642-91e7-21ada4a3d2b0)

- `‘소리 치는 아키텍처’`로 패키지, 클래스의 이름만으로 무엇을 하는지 대략적으로라도 유추하게 하고 싶었습니다.
    - 도메인을 기준으로 adapter, application, domain으로 나뉩니다.
        - adapter는 in과 out으로 나뉩니다.
            - 요청을 받는 in 에는 Controller
            - out에는 external(외부 서비스), persistence(영속성)의 구현체가 위치합니다.
        - application도 port에 in과 out으로 나뉩니다.
            - in에는 컨트롤러가 사용할 UseCase의 interface가 위치합니다.
            - out에는 external(외부 서비스), persistence 계층 으로 향하는 port가 위치합니다.
            - application은 바깥 계층으로 향하는 것에 대해 port만 알고 있습니다.
        - domain

<br/>
          
- application 계층으로 진입하는 길은 **port**로 이용합니다.
    - 의존성을 역전시켜 도메인 코드가 바깥쪽 코드로 의존하지 않도록 합니다.
 
<br/>

## 적용하면서 고민했던 점

- **adapater 계층과 application 계층의 Request 변환을 하지 않습니다.**
    - RequestDto에 대해서 분리한다면 각 계층에 독립하여 추후 유지보수성을 향상시킬 수 있습니다. 하지만 관리 파일이 많아지는 단점 또한 존재합니다.
    - 관점을 "분리"로 유지보수성을 향상시키느냐, "파일"의 개수를 줄이느냐로 나누어 고민했습니다.
    - 토론 결과 아래와 같은 이유로 분리하지 않습니다.
        - 추후 유지보수성을 높여 분리가 필요하다고 판단될 시점에 분리합니다.
        - 프로젝트에서 Controller와 Service가 다른 Dto 형태를 가진 경우가 없으므로 동일한 Dto를 써도 무방한 상황입니다.
          

<br/>
          
- **CQRS 패턴을 적용합니다.**
    - 프로젝트에서 JPA와 Querydsl을 동시에 사용하고 있습니다.
        - 처음에는 JPA와 Querydsl을 구분지으려 했으나, 경계가 명확하지 않았습니다.
    - 하여 DB의 변화를 요구하는 Command와, 단순 조회를 요구하는 Query를 분리합니다.
    - Command, Query 어느 부분에서 문제가 발생했는지에 따라 가독성이 높아집니다.
        - JPA와 Querydsl의 경계는 명확하지 않으나, Command와 Query의 경계가 명확해짐에 따라 추후 유지보수의 가능성을 더 높게 판단했습니다.

<br/>
          
- **헬퍼 클래스(util)의 크기를 어떻게 명확하게 할 것인가?**
    - 범위가 애매한 것에 대해 자꾸 util 클래스로 집어넣으려는 유혹이 발생했습니다. 결국, 걷잡을 수 없이 util의 클래스가 커져버립니다.
    - 해당 위치에는 global하게만 사용되는 것만 들어가도록 합니다.
        - 데이터베이스 설정 정보, BaseEntity, 응답 포맷(Response), Exception

<br/>        

## 아키텍처를 적용하면서 느낀 점

- 아키텍처는 만병통치약이 아니다. 팀 간 적절한 규칙을 정하고 해당 규칙을 고수한다면, 그것 또한 방법이 될 수 있다고 생각한다.
- 패키지에 무엇이 위치해야 하고, 클래스가 어떤 역할을 하는지 생각하면서 코드를 작성하게 된다. 굳이 클린 아키텍처가 아니더라도, 이런 의미에서 강제성이 부여가 되어 유지보수에 도움이 된다.
- 직접 구현했던 프로젝트였음에도 불구하고, 구조와 코드를 수정하는 작업이 쉽지만은 않았습니다. 하나의 클래스에 의존성으로 엮여 있는 것들이 많았기 때문입니다. 리팩토링을 진행하며 프로젝트의 시작부터 객체지향적인 프로그래밍과 클린 아키텍처를 적용하는 것이 중요함을 배웠습니다.
결국 모든 프로젝트는 서비스를 운영하며 유지보수의 작업을 거치게 되기 마련인데, 이를 위해 유지보수성을 생각하는 프로그래밍을 하고자 다짐하게 되었습니다.
- 확장성을 고려하게 되었습니다. 여러 가지 서비스를 운영하다 보면, 각각의 서비스는 다른 서비스의 세부 내용을 알지 못합니다.
알지 못하는 상황에서도 서비스 구현 및 제공을 하기 위해서는 각각의 포트가 그 역할을 해주어야 함을 배울 수 있는 시간이었습니다.

<hr/>

# 쿼리 계획을 보고 인덱스 적용 및 개선

## 인덱스 적용이 필요한 컬럼을 선택한 논리는 다음과 같습니다.

- 유의미한 데이터 크기를 가진 column → 테이블의 행 수가 약 100만개 이상 되는 경우
    - shop의 Name, category
- 쿼리가 가져오는 결과가 매우 느린 경우
- 목적 : range (ref, const, range) 수준으로 개선한다.

## 개선 결과

### 백만 개의 데이터에 대해 인덱스 유무에 따른 시간 측정

1. 시간이 많이 걸리는 쿼리 개선 -> category에 인덱스 추가
- 인덱스 적용 안할 때 `약 3초` 소요되었던 것이 인덱스 적용 이후 `0.1~0.2초`로 단축되었습니다.
- 실행 계획의 type이 ALL -> ref로 변경되었고 rows를 통해 변화를 확인할 수 있습니다.

`인덱스 적용 전의 실행 계획`
<img width="1128" alt="실행전" src="https://github.com/kksjae-hyyejji/Moic-Backend-Refactoring/assets/87571953/bf036bee-765f-4fab-a2fa-77b50b7b12fd">
`인덱스 적용 후의 실행 계획`
<img width="1122" alt="실행후" src="https://github.com/kksjae-hyyejji/Moic-Backend-Refactoring/assets/87571953/15dd11d7-ead5-4c37-9501-9a45e3cfc239">



2. 다중 컬럼 인덱스를 선택하지 않고 단순 인덱스 사용 
```sql
explain select category, name, location, address
    from shop
    where name = "스타벅스" and location = "현대목동점";
```
- 위의 쿼리는 shop과 location으로 적합한 shop의 정보를 반환하는 쿼리입니다.
- 해당 쿼리의 index는 shop의 name에만 걸어두어 index Condition을 유도하였습니다.
  - shop의 name은 카디널리티를 0.9로 가지는 컬럼입니다. name을 통해 필터링된 location의 개수는 다중 컬럼 인덱스를 적용할 만큼 많은 행을 가지고 있지 않습니다.
  - 아래의 적용 후의 실행 계획을 보면, 1차 필터링을 거치고 나서 464개의 행을 검사합니다. 이 정도로 충분합니다.


`인덱스 적용 전의 실행 계획`
<img width="1099" alt="Untitled" src="https://github.com/kksjae-hyyejji/Moic-Backend-Refactoring/assets/87571953/a72606a4-7dd1-48bf-9b4e-c501db63251f">
`인덱스 적용 후의 실행 계획`
<img width="1225" alt="Untitled (1)" src="https://github.com/kksjae-hyyejji/Moic-Backend-Refactoring/assets/87571953/733c667d-fd18-4f00-a309-e3834549a9cd">

### 서브 쿼리 →  join 문으로 개선

- 서브 쿼리 이용시 만들어지는 결과는 테이블이 아니므로 메타 데이터를 가지고 있지 않기 때문에 인덱스 사용 등으로 최적화를 이루어내기 어렵습니다. 따라서 서브 쿼리보다 join을 이용하는 것이 적절하다고 판단해 join문으로 변경했습니다.
    - mysql 5.5 버전 이후부터는 옵티마이저가 서브쿼리를 join으로 변경하여 사용합니다.
    - 하지만 직접 명시해주는 것이 더 좋다고 판단하여 변경합니다.

- 예시 쿼리
    
    ```sql
    SELECT *
    FROM card_benefit
    WHERE card_name IN
          (
              SELECT card_name
              FROM user_card
              WHERE user_id = "test1111"
          )
      AND shop_name = "스타벅스";
    ```
- 쿼리 계획으로 확인한 결과 서브 쿼리로 작성을 해도, join을 이용하는 것을 확인했습니다.
  <img width="825" alt="Untitled (2)" src="https://github.com/kksjae-hyyejji/Moic-Backend-Refactoring/assets/87571953/f2167c67-639f-4d51-9092-c0d46512cc4c">

- 옵티마이저가 변형하여 실행한 쿼리는 다음과 같습니다.
    
    ```sql
    select `moicDB`.`card_benefit`.`card_benefit_seq` AS `card_benefit_seq`,`moicDB`.`card_benefit`.`created_at` AS `created_at`,`moicDB`.`card_benefit`.`deleted_at` AS `deleted_at`,`moicDB`.`card_benefit`.`is_delete` AS `is_delete`,`moicDB`.`card_benefit`.`updated_at` AS `updated_at`,`moicDB`.`card_benefit`.`cashback` AS `cashback`,`moicDB`.`card_benefit`.`category` AS `category`,`moicDB`.`card_benefit`.`content` AS `content`,`moicDB`.`card_benefit`.`discount` AS `discount`,`moicDB`.`card_benefit`.`point` AS `point`,`moicDB`.`card_benefit`.`shop_name` AS `shop_name`,`moicDB`.`card_benefit`.`card_name` AS `card_name` 
    from `moicDB`.`card_benefit` 
    semi join (`moicDB`.`user_card`) 
    where 
    `moicDB`.`user_card`.`card_name` = `moicDB`.`card_benefit`.`card_name` 
    and `moicDB`.`card_benefit`.`shop_name` = '스타벅스' 
    and `moicDB`.`user_card`.`user_id` = 'test1111'
    
    ```
 - 개선 쿼리
    
    ```sql
    SELECT *
    FROM card_benefit INNER JOIN user_card
    using (card_name)
    where user_card.user_id = "test1111" AND card_benefit.shop_name = "스타벅스";
    ```
- 쿼리 계획을 통해 옵티마이저가 semi join을 자동으로 하지 않는 것을 확인합니다.
    <img width="824" alt="Untitled (3)" src="https://github.com/kksjae-hyyejji/Moic-Backend-Refactoring/assets/87571953/ca9dabba-4fff-4735-99a0-76547ec93653">
- 옵티마이저가 변형하여 실행한 쿼리는 다음과 같습니다.
    
    ```sql
    select `moicDB`.`card_benefit`.`card_name` AS `card_name`,`moicDB`.`card_benefit`.`card_benefit_seq` AS `card_benefit_seq`,`moicDB`.`card_benefit`.`created_at` AS `created_at`,`moicDB`.`card_benefit`.`deleted_at` AS `deleted_at`,`moicDB`.`card_benefit`.`is_delete` AS `is_delete`,`moicDB`.`card_benefit`.`updated_at` AS `updated_at`,`moicDB`.`card_benefit`.`cashback` AS `cashback`,`moicDB`.`card_benefit`.`category` AS `category`,`moicDB`.`card_benefit`.`content` AS `content`,`moicDB`.`card_benefit`.`discount` AS `discount`,`moicDB`.`card_benefit`.`point` AS `point`,`moicDB`.`card_benefit`.`shop_name` AS `shop_name`,`moicDB`.`user_card`.`user_card_seq` AS `user_card_seq`,`moicDB`.`user_card`.`created_at` AS `created_at`,`moicDB`.`user_card`.`deleted_at` AS `deleted_at`,`moicDB`.`user_card`.`is_delete` AS `is_delete`,`moicDB`.`user_card`.`updated_at` AS `updated_at`,`moicDB`.`user_card`.`user_id` AS `user_id`
    from `moicDB`.`card_benefit`
        join `moicDB`.`user_card`
    where 
    `moicDB`.`card_benefit`.`card_name` = `moicDB`.`user_card`.`card_name` 
    and `moicDB`.`user_card`.`user_id` = 'test1111' 
    and `moicDB`.`card_benefit`.`shop_name` = '스타벅스'
    ```

## 느낀 점
- **PK 선정의 중요성**
  - 저희가 사용했던 DB는 MySQL입니다. 스토리지 엔진으로 InnoDB를 사용하고 있습니다.
  - InnoDB의 PK는 클러스터링 키로 사용됩니다. 즉 클러스터링 인덱스를 만들어내므로 PK로 검색을 하는 경우 그렇지 않은 테이블에 비해 매우 빠르게 처리됩니다.
     - 클러스터링되지 않은 테이블에서는 크게 고려하지 않아도 된다고 합니다.(From Real MySQL 8.0)
  - User 테이블에 대해서 AutoIncrement로 PK를 관리했습니다. 그러나 실상 검색시 이용되는 것은 user의 id입니다. 불필요하게 세컨더리 인덱스를 형성하게 됩니다.
  - 결론으로 PK로 선정할 칼럼의 크기가 너무 커서 대체를 해야되는 상황이 아니라면, 의미적으로 레코드를 대표할 수 있는 칼럼을 PK로 선정하는 것이 중요합니다.

- **무분별한 인덱스 방지**
  - 인덱스의 리프 노드에서 검색 조건에 일치하는 건들은 데이터 파일에서 레코드를 읽는 과정이 필요합니다.
  - 이 때 한 건 단위로 랜덤 I/O가 일어나므로 비용이 비싼 작업입니다.
  - 만약 인덱스가 읽어야 하는 데이터 파일의 개수가 너무 많다면 (20 ~ 25% 이상) 테이블 풀스캔이 더 효율적인 처리 방식이 될 수 있습니다.
