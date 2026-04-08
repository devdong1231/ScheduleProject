일정 제목, 일정 내용, 작성자명, 비밀번호, 작성/수정일(날짜 시간을 모두 포함한 형태)
> # **MemoProject API 명세서**
# 일정 생성 API

## 🔹 기본 정보

- **Method** : `POST`
- **URL** : `/api/memos`
- **설명** : 새로운 일정을 생성


## 🔹 Request

### Headers

```
Content-Type: application/json
```

### Body

```json
{
  "title": "오후 스크럼",
  "contents": "오후 7시 30분에 zep으로 오후 스크럼 진행",
  "author": "김유하",
  "password": "asdf1234"
}
```

| 필드명      | 타입     | 필수 | 설명    |
|----------|--------|----|-------|
| title    | String | O  | 일정 제목 |
| contents | String | O  | 일정 내용 |
| author   | String | O  | 작성자명  |
| password | String | O  | 비밀번호  |


## 🔹 Response

### ✅ 성공 - 201 Created

```json
{
  "id": 1,
  "title": "오후 스크럼",
  "contents": "오후 7시 30분에 zep으로 오후 스크럼 진행",
  "author": "김유하",
  "createdAt": "2026-04-18T16:30",
  "updatedAt": "2026-04-18T16:30"
}
```

| 필드명       | 타입            | 필수 | 설명     |
|-----------|---------------|----|--------|
| id        | Long          | O  | 고유 식별자 |
| title     | String        | O  | 일정 제목  |
| contents  | String        | O  | 일정 내용  |
| author    | String        | O  | 작성자명   |
| createdAt | LocalDateTime | O  | 생성한 날짜 |
| updatedAt | LocalDateTime | O  | 수정한 날짜 |

### ❌ 실패 - 400 Bad Request

```
{
    "message": "필수 입력값이 입력되지 않았습니다!"
}
```
<br>

# 일정 단건 조회 API

## 🔹 기본 정보

- **Method** : `GET`
- **URL** : `/api/memos/{memoId}`
- **설명** : 단일 일정 조회


## 🔹 Request
### Parameter & Querystring
```
/memos/
```

### Headers

```
Content-Type: application/json
```

### Body

```json
{
  "id": 1,
  "title": "오후 스크럼",
  "contents": "오후 7시 30분에 zep으로 오후 스크럼 진행",
  "author": "김유하"
}
```

| 필드명      | 타입     | 필수 | 설명    |
|----------|--------|----|-------|
| title    | String | O  | 일정 제목 |
| contents | String | O  | 일정 내용 |
| author   | String | O  | 작성자명  |
| password | String | O  | 비밀번호  |


## 🔹 Response

### ✅ 성공 - 201 Created

```json
{
  "id": 1,
  "title": "오후 스크럼",
  "contents": "오후 7시 30분에 zep으로 오후 스크럼 진행",
  "author": "김유하",
  "createdAt": "2026-04-18T16:30",
  "updatedAt": "2026-04-18T16:30"
}
```

| 필드명       | 타입            | 필수 | 설명     |
|-----------|---------------|----|--------|
| id        | Long          | O  | 고유 식별자 |
| title     | String        | O  | 일정 제목  |
| contents  | String        | O  | 일정 내용  |
| author    | String        | O  | 작성자명   |
| createdAt | LocalDateTime | O  | 생성한 날짜 |
| updatedAt | LocalDateTime | O  | 수정한 날짜 |

### ❌ 실패 - 400 Bad Request

```
{
    "message": "필수 입력값이 입력되지 않았습니다!"
}
```
<br>

# 일정 전체 조회 API

## 🔹 기본 정보

- **Method** : `POST`
- **URL** : `/api/memos`
- **설명** : 새로운 일정을 생성


## 🔹 Request

### Headers

```
Content-Type: application/json
```

### Body

```json
{
  "title": "오후 스크럼",
  "contents": "오후 7시 30분에 zep으로 오후 스크럼 진행",
  "author": "김유하",
  "password": "asdf1234"
}
```

| 필드명      | 타입     | 필수 | 설명    |
|----------|--------|----|-------|
| title    | String | O  | 일정 제목 |
| contents | String | O  | 일정 내용 |
| author   | String | O  | 작성자명  |
| password | String | O  | 비밀번호  |


## 🔹 Response

### ✅ 성공 - 201 Created

```json
{
  "id": 1,
  "title": "오후 스크럼",
  "contents": "오후 7시 30분에 zep으로 오후 스크럼 진행",
  "author": "김유하",
  "createdAt": "2026-04-18T16:30",
  "updatedAt": "2026-04-18T16:30"
}
```

| 필드명       | 타입            | 필수 | 설명     |
|-----------|---------------|----|--------|
| id        | Long          | O  | 고유 식별자 |
| title     | String        | O  | 일정 제목  |
| contents  | String        | O  | 일정 내용  |
| author    | String        | O  | 작성자명   |
| createdAt | LocalDateTime | O  | 생성한 날짜 |
| updatedAt | LocalDateTime | O  | 수정한 날짜 |

### ❌ 실패 - 400 Bad Request

```
{
    "message": "필수 입력값이 입력되지 않았습니다!"
}
```
<br>

# 일정 수정 API

---

# 일정 삭제 API
