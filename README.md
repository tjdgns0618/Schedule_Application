## 목차
1. API 명세서
2. ERD 이미지
3. 프로젝트 구조

---

## `실패 케이스 작성 예정`
# 1. 일정 작성 (Create Schedule)
Method: POST

엔드포인트: http://localhost:8080/schedules

Description: 일정 제목, 일정 내용, 일정 작성자를 입력받아 새로운 일정을 저장합니다.

Request (Body)
```
JSON
{
    "title": "일정 제목",
    "content": "일정 내용",
    "userId": "유저 고유 식별자"
}
```

---

Response `(201 Created)`
```
JSON
{
    "id": 1,
    "title": "일정 제목",
    "content": "일정 내용",
    "userId": "유저 고유 식별자",
    "createdAt": "2026-04-16T20:15:29",
    "modifedAt": "2026-04-16T20:15:29"
}
```

---

# 2. 전체 일정 조회 (Get All Schedules)
Method: GET

엔드포인트: http://localhost:8080/schedules

Description: 저장된 일정 목록을 전체 조회합니다.

Request
`Body 없음`

---

Response `(200 OK)`
```
JSON
[
    {
        "id": 1,
        "title": "일정 제목 1",
        "content": "일정 내용 1",
        "userId": "유저 고유 식별자 1",
        "createdAt": "2026-04-16T18:00:00",
        "modifiedAt": "2026-04-16T18:00:00"
    },
    {
        "id": 2,
        "title": "일정 제목 2",
        "content": "일정 내용 2",
        "userId": "유저 고유 식별자 2",
        "createdAt": "2026-04-16T20:00:00",
        "modifiedAt": "2026-04-16T20:00:00"
    }
]
```

---

# 3. 선택 일정 조회 (Get One Schedule)
Method: GET

엔드포인트: http://localhost:8080/schedules/{scheduleId}

Description: 선택한 scheduleId에 해당하는 단일 일정 정보를 조회합니다.

Request `Path Variable: scheduleId (조회할 일정의 고유 식별자)`

Response `(200 OK)`

```
JSON
{
    "id": 1,
    "title": "일정 제목 1",
    "content": "일정 내용 1",
    "userId": "유저 고유 식별자 1",
    "createdAt": "2026-04-16T15:00:00",
    "modifiedAt": "2026-04-16T15:00:00"
}
```
---
# 4. 선택 일정 수정 (Update Schedule)
Method: PUT

엔드포인트: http://localhost:8080/schedules/{scheduleId}

Description: 선택한 일정의 제목, 내용, 작성자를 수정합니다.

Request `(Body),
Path Variable: scheduleId`

```
JSON
{
    "title": "변경할 일정 제목",
    "content": "변경할 일정 내용"
}

```

---

Response `(200 OK)`
```
JSON
{
    "id": 1,
    "title": "변경할 일정 제목",
    "content": "변경할 일정 내용",
    "userId": "유저 고유 식별자",
    "createdAt": "2026-04-16T15:00:00",
    "modifiedAt": "수정 시각"
}
```
---
# 5. 선택 일정 삭제 (Delete Schedule)
Method: DELETE

엔드포인트: http://localhost:8080/schedules/{scheduleId}

Description: 선택한 일정을 삭제합니다.

Request `없음`

---

Response `(204 No Content)`

---
# 1. 유저 생성 (Create User)
Method: POST

URL: http://localhost:8080/users

Description: 이름과 이메일을 입력받아 새로운 유저를 저장합니다. 생성 시간과 수정 시간은 서버에서 자동으로 할당됩니다.

Request `Body`
```
JSON
{
    "name": "홍길동",
    "email": "hong@example.com"
}
```

---

Response `201 Created`
```
JSON
{
    "id": 1,
    "name": "홍길동",
    "email": "hong@example.com",
    "createdAt": "2026-04-17T18:30:00",
    "modifiedAt": "2026-04-17T18:30:00"
}
```
---
# 2. 전체 유저 조회 (Get All Users)
Method: GET

URL: http://localhost:8080/users

Description: 데이터베이스에 저장된 모든 유저의 목록을 조회합니다.

Request `Body 없음`

---

Response `200 OK`
```
JSON
[
    {
        "id": 1,
        "name": "홍길동",
        "email": "hong@example.com",
        "createdAt": "2026-04-17T18:30:00",
        "modifiedAt": "2026-04-17T18:30:00"
    },
    {
        "id": 2,
        "name": "김스프링",
        "email": "spring@example.com",
        "createdAt": "2026-04-17T19:00:00",
        "modifiedAt": "2026-04-17T19:00:00"
    }
]
```

---

# 3. 선택 유저 조회 (Get One User)
Method: GET

URL: http://localhost:8080/users/{userId}

Description: 선택한 userId에 해당하는 단일 유저의 상세 정보를 조회합니다.

Request `Path Variable: userId (조회할 유저의 식별자)`

---

Response `200 OK`
```
JSON
{
    "id": 1,
    "name": "홍길동",
    "email": "hong@example.com",
    "createdAt": "2026-04-17T18:30:00",
    "modifiedAt": "2026-04-17T18:30:00"
}
```

---

# 4. 선택 유저 수정 (Update User)
Method: PUT

URL: http://localhost:8080/users/{userId}

Description: 선택한 유저의 이름 또는 이메일을 수정합니다. 수정이 완료되면 modifiedAt 값이 갱신됩니다.

Request `Body, Path Variable: userId`
```
JSON
{
    "name": "홍길동(수정)",
    "email": "hong_updated@example.com"
}
```

---

Response `200 OK`

```
JSON
{
    "id": 1,
    "name": "홍길동(수정)",
    "email": "hong_updated@example.com",
    "createdAt": "2026-04-17T18:30:00",
    "modifiedAt": "2026-04-17T20:15:00"
}
```

---

# 5. 선택 유저 삭제 (Delete User)
Method: DELETE

URL: http://localhost:8080/users/{userId}

Description: 선택한 유저의 정보를 데이터베이스에서 삭제합니다.

Request `Path Variable: userId`

---

Response `204 No Content`

---

# ERD 이미지

<img width="845" height="227" alt="image" src="https://github.com/user-attachments/assets/0a20595d-016d-410c-ba35-861e7697fe67" />
