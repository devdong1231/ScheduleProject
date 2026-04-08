일정 제목, 일정 내용, 작성자명, 비밀번호, 작성/수정일(날짜 시간을 모두 포함한 형태)
> # **ScheduleProject API 명세서**

# 일정 생성 API

## 🔹 기본 정보

- **Method** : `POST`
- **URL** : `/api/schedules`
- **설명** : 새로운 일정을 생성

<br>

## 🔹 Request

### Headers

```
Content-Type: application/json
```

<br>

### Body

```json
{
  "title": "오후 스크럼",
  "contents": "오후 7시 30분에 zep으로 오후 스크럼 진행",
  "author": "김유하",
  "password": "asdf1234"
}
```

| 필드명      | 타입     | 필수 | 설명             |
|----------|--------|----|----------------|
| title    | String | O  | 일정 제목          |
| contents | String | O  | 일정 내용          |
| author   | String | O  | 작성자명           |
| password | String | O  | 비밀번호(수정/삭제 검증) |

<br>

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

<br>

### ❌ 실패 - 400 Bad Request

```json
{
  "message": "필수 입력값이 입력되지 않았습니다!"
}
```

<br>

# 일정 전체 조회 API

## 🔹 기본 정보

- **Method** : `GET`
- **URL** : `/api/schedules`
- **설명** : 전체 일정 조회 / 작성자명이 전달되면 해당 작성자의 일정만 조회

<br>

### Query Parameters

| 파라미터명  | 타입     | 필수 | 설명      |
|--------|--------|----|---------|
| author | String | X  | 작성자명 필터 |

## 🔹 요청 예시

```http
GET /api/schedules
GET /api/schedules?author=홍길동
```

<br>

## 🔹 Request

### Body

```json
[
  {
    "id": 2,
    "title": "오전 스크럼",
    "contents": "오전 10시 5분에 zep으로 오전 스크럼 진행",
    "author": "김유하",
    "createdAt": "2026-04-08T08:40",
    "updatedAt": "2026-04-08T08:40"
  },
  {
    "id": 1,
    "title": "오후 스크럼",
    "contents": "오후 7시 30분에 zep으로 오후 스크럼 진행",
    "author": "김유하",
    "createdAt": "2026-04-08T08:40",
    "updatedAt": "2026-04-08T16:40"
  }
]

```

| 필드명       | 타입            | 설명     |
|-----------|---------------|--------|
| id        | Long          | 고유 식별자 |
| title     | String        | 일정 제목  |
| contents  | String        | 일정 내용  |
| author    | String        | 작성자명   |
| createdAt | LocalDateTime | 작성일    |
| updatedAt | LocalDateTime | 수정일    |

<br>

## 🔹 Response

### ✅ 성공 - 200 OK

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

### ❌ 실패 -

<br>

# 일정 단건 조회 API

## 🔹 기본 정보

- **Method** : `GET`
- **URL** : `/api/schedules/{scheduleId}`
- **설명** : scheduleId로 특정 일정을 조회

<br>

### Path Variable

| 변수명 | 타입   | 설명     |
|-----|------|--------|
| id  | Long | 고유 식별자 |

### 요청 예시

```
GET /api/schedules/1
```

<br>

## 🔹 Response

### ✅ 성공 - 200 OK

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

<br>

### ❌ 실패 - 404 Not Found

```
{
    "message": "해당 일정을 찾을 수 없습니다!"
}
```

<br>

# 일정 수정 API

## 🔹 기본 정보

- **Method** : `PATCH`
- **URL** : `/api/schedules/{scheduleId}`
- **설명** : 선택한 일정의 제목과 작성자명만 수정 / 수정 시 비밀번호 필요

<br>

## 🔹 Path Variable

| 변수명 | 타입   | 설명     |
|-----|------|--------|
| id  | Long | 고유 식별자 |

<br>

## 🔹 Request

### Headers

```
Content-Type: application/json
```

### Body

```json
{
  "title": "오후 스크럼",
  "author": "김유하",
  "password": "asdf1234"
}
```

| 필드명      | 타입     | 필수 | 설명    |
|----------|--------|----|-------|
| title    | String | O  | 일정 제목 |
| author   | String | O  | 작성자명  |
| password | String | O  | 비밀번호  |

<br>

## 🔹 Response

### ✅ 성공 - 200 OK

```json
{
  "id": 1,
  "title": "오후 스크럼",
  "contents": "오후 7시 30분에 zep으로 오후 스크럼 진행",
  "author": "김유하",
  "createdAt": "2026-04-18T16:30",
  "updatedAt": "2026-04-18T17:30"
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

# 일정 삭제 API

## 🔹 기본 정보

- **Method** : `DELETE`
- **URL** : `/api/schedules/{scheduleId}`
- **설명** : 선택한 일정을 삭제 / 삭제 시 비밀번호 필요

<br>

## 🔹 Path Variable

| 변수명 | 타입   | 설명     |
|-----|------|--------|
| id  | Long | 고유 식별자 |

<br>

## 🔹 Request

### Headers

```
Content-Type: application/json
```

### Body

```json
{
  "password": "asdf1234"
}
```

| 필드명      | 타입     | 필수 | 설명   |
|----------|--------|----|------|
| password | String | O  | 비밀번호 |

<br>

## 🔹 Response

### ✅ 성공 - 204 No Content

### ❌ 실패 - 400 Bad Request

```
{
    "message": "비밀번호가 일치하지 않습니다."
}
```

### ❌ 실패 - 404 Not Found

```
{
    "message": "해당 일정을 찾을 수 없습니다."
}
```