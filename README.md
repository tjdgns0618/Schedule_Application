## 목차
1. API 명세서
2. ERD 이미지

---

# 1. 일정 작성 (Create Schedule)
Method: POST

엔드포인트: http://localhost:8080/schedules

Description: 세션이 유효한 로그인 유저만 일정을 생성할 수 있습니다.

일정 제목, 일정 내용, 일정 작성자를 입력받아 새로운 일정을 저장합니다.

### Request
- Headers : `Cookie: JSESSIONID=AB12CD34EF56GH78`
- Body
```
JSON
{
    "title": "일정 제목",
    "content": "일정 내용"
}
```

---

Response `201 Created`
```
JSON
{
    "id": 1,
    "title": "일정 제목",
    "content": "일정 내용",
    "userName": "일정 작성자 이름",    
    "createdAt": "2026-04-16T20:15:29",
    "modifedAt": "2026-04-16T20:15:29"
}
```

---

Error Response 
- `400 Bad Request(잘못된 요청)`
```
{
    "message": "JSON 문법에 맞게 요청을 작성해주세요."
}
```

- `401 Unauthorized(세션 없음)`
```
{
    "message": "로그인이 필요한 서비스입니다."
}
```

---

# 2. 전체 일정 조회 (Get All Schedules)
Method: GET

엔드포인트: http://localhost:8080/schedules

Description: 저장된 일정 목록을 전체 조회합니다.

Request
- Body `없음`
- Query Parameters : `?page=0&size=10` `필수는 아닙니다.`

---

Response `(200 OK)`
```
JSON
[
    {
        "id": 10,
        "title": "일정 제목 10",
        "content": "일정 내용 10",
        "userName": "일정 작성자명",
        "createdAt": "2026-04-16T22:00:00",
        "modifiedAt": "2026-04-16T22:00:00"
    },
    {
        "id": 9,
        "title": "일정 제목 9",
        "content": "일정 내용 9",
        "userName": "일정 작성자명",
        "createdAt": "2026-04-16T20:00:00",
        "modifiedAt": "2026-04-16T20:00:00"
    },
    ...
    {
        "id": 1,
        "title": "일정 제목 1",
        "content": "일정 내용 1",
        "userName": "일정 작성자명",
        "createdAt": "2026-04-16T18:00:00",
        "modifiedAt": "2026-04-16T18:00:00"
    }
]
```

---

# 3. 선택 일정 조회 (Get One Schedule)
Method: GET

엔드포인트: http://localhost:8080/schedules/{scheduleId}

Description: 선택한 scheduleId에 해당하는 단일 일정 정보를 조회합니다.

Request 
- Path Variable: `scheduleId (조회할 일정의 고유 식별자)`

---

Response `200 OK`

```
JSON
{
    "id": 1,
    "title": "일정 제목 1",
    "content": "일정 내용 1",
    "userName": "일정 작성자명 1",
    "createdAt": "2026-04-16T15:00:00",
    "modifiedAt": "2026-04-16T15:00:00"
}
```

---

Error Response `404 Not Found(존재하지 않는 일정 조회 요청)`
```
{
    "message": "해당 일정을 찾을 수 없습니다.(존재하지 않는 ID)"
}
```

---

# 4. 선택 일정 수정 (Update Schedule)
Method: PUT

엔드포인트: http://localhost:8080/schedules/{scheduleId}

Description: 세션이 유효한 로그인 유저만 일정을 수정할 수 있습니다.

선택한 일정의 제목, 내용, 작성자를 수정합니다.

Request 
- Headers : `Cookie: JSESSIONID=AB12CD34EF56GH78`
- Path Variable : `scheduleId`
- Body

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
    "userName": "일정 작성자명",
    "createdAt": "2026-04-16T15:00:00",
    "modifiedAt": "수정 시각"
}
```

---

Error Response

- `400 Bad Request(잘못된 요청)`
```
{
    "message": "JSON 문법에 맞게 요청을 작성해주세요."
}
```

- `401 Unauthorized(세션 없음)`
```
{
    "message": "로그인이 필요한 서비스입니다."
}
```

- `404 Not Found(존재하지 않는 일정 수정 요청)`
```
{
    "message": "해당 일정을 찾을 수 없습니다.(존재하지 않는 ID)"
}
```

---

# 5. 선택 일정 삭제 (Delete Schedule)
Method: DELETE

엔드포인트: http://localhost:8080/schedules/{scheduleId}

Description: 세션이 유효한 로그인 유저만 일정을 삭제할 수 있습니다. 

선택한 일정을 삭제합니다.

Request
- Headers : `Cookie: JSESSIONID=AB12CD34EF56GH78`
- Path Variable : `scheduleId`

---

Response `(204 No Content)`

---

Error Response

- `401 Unauthorized(세션 없음)`
```
{
    "message": "로그인이 필요한 서비스입니다."
}
```

- `404 Not Found(존재하지 않는 일정 삭제 요청)`
```
{
    "message": "해당 일정을 찾을 수 없습니다.(존재하지 않는 ID)"
}
```

---

# 1. 회원가입 (Sign Up)
Method: POST

URL: http://localhost:8080/users/signup

Description: 이름, 이메일, 비밀번호를 입력받아 새로운 유저로 회원가입을 진행합니다. 생성 시간과 수정 시간은 서버에서 자동으로 할당됩니다.

DB에 존재하는 이미 존재하는 이메일은 사용 불가능합니다.

Request 
- Body

```
JSON
{
    "name": "홍길동",
    "email": "hong@example.com",
    "password": "password123"
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

Error Response
- `400 Bad Request(잘못된 요청)`
```
{
    "message": "JSON 문법에 맞게 요청을 작성해주세요."
}
```

- `409 Conflict(이메일 중복)`
```
{
    "message": "이미 사용 중인 이메일입니다. 다른 이메일로 입력해주세요."
}
```

---

# 2. 유저 로그인 (Login)
Method: POST

URL: http://localhost:8080/users/login

Description: 이메일과 비밀번호를 확인하여 로그인을 수행하고 세션 쿠키를 발급합니다.

Request 
- Body
```
JSON
{
    "email": "hong@example.com",
    "password": "password123"
}
```

---

Response `200 OK`

- Headers `Set-Cookie: JSESSIONID=AB12CD34EF56GH78`

- Body
```
JSON
{
    "id": 1,
    "name": "홍길동",
    "email": "hong@example.com"
}
```

---

# 3. 유저 로그아웃 (Logout)
Method: POST

URL: http://localhost:8080/users/logout

Description: 현재 로그인된 사용자의 유효한 세션을 무효화하여 로그아웃 처리를 진행합니다.

Request 
- Headers : `Cookie: JSESSIONID=AB12CD34EF56GH78 (로그인 시 발급받은 세션 쿠키가 반드시 포함되어야 합니다.)`

---

Response `204 No Content`

---

Error Response
- `401 Unauthorized(세션 없음)`
```
{
    "message": "로그인이 필요한 서비스입니다."
}
```

---
# 4. 전체 유저 조회 (Get All Users)
Method: GET

URL: http://localhost:8080/users

Description: 데이터베이스에 저장된 모든 유저의 목록을 조회합니다.

Request 
- Body `없음`

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

Error Response
- `400 Bad Request(잘못된 요청)`
```
{
    "message": "JSON 문법에 맞게 요청을 작성해주세요."
}
```

---

# 5. 선택 유저 조회 (Get One User)
Method: GET

URL: http://localhost:8080/users/{userId}

Description: 선택한 userId에 해당하는 단일 유저의 상세 정보를 조회합니다.

Request Path Variable: `userId (조회할 유저의 식별자)`

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

Error Response
- `400 Bad Request(잘못된 요청)`
```
{
    "message": "JSON 문법에 맞게 요청을 작성해주세요."
}
```

- `404 Not Found(존재하지 않는 유저)`
```
{
    "message": "존재하지 않는 유저입니다."
}
```

---

# 6. 선택 유저 수정 (Update User)
Method: PUT

URL: http://localhost:8080/users/{userId}

Description: 선택한 유저의 이름 또는 이메일을 수정합니다. 수정이 완료되면 modifiedAt 값이 갱신됩니다.

Request 
- Path Variable: `userId`
- Body 

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
    "modifiedAt": "수정시각"
}
```

---

Error Response
- `400 Bad Request(잘못된 요청)`
```
{
    "message": "JSON 문법에 맞게 요청을 작성해주세요."
}
```

- `401 Unauthorized(권한 없음)`
```
{
    "message": "권한이 없습니다."
}
```

- `404 Not Found(존재하지 않는 유저)`
```
{
    "message": "존재하지 않는 유저입니다."
}
```

---

# 7. 선택 유저 삭제 (Delete User)
Method: DELETE

URL: http://localhost:8080/users/{userId}

Description: 선택한 유저의 정보를 데이터베이스에서 삭제합니다.

Request 

- Path Variable: `userId`

---

Response `204 No Content`

---

Error Response
- `400 Bad Request(잘못된 요청)`
```
{
    "message": "JSON 문법에 맞게 요청을 작성해주세요."
}
```

- `401 Unauthorized(권한 없음)`
```
{
    "message": "권한이 없습니다."
}
```

- `404 Not Found(존재하지 않는 유저)`
```
{
    "message": "존재하지 않는 유저입니다."
}
```

---

# 1. 댓글 작성 (Create Comment)
Method: POST

URL: http://localhost:8080/schedules/{scheduleId}/comments

Description: 선택한 일정에 댓글을 작성합니다.

Request 
- Headers : `Cookie: JSESSIONID=AB12CD34EF56GH78`
- Path Variable: `scheduleId`
- body
```
{
    "content": "정말 재밌는 일정이네요." 
}
```

---

Response `201 Created`
```
{
    "id": 1,
    "content": "정말 재밌는 일정이네요.",
    "scheduleTitle": "일정제목",
    "userName": "양성훈",
    "createdAt": "2026-04-20T16:56:44.3460818",
    "modifiedAt": "2026-04-20T16:56:44.3460818"
}
```
---

Error Response
- `400 Bad Request(잘못된 요청)`
```
{
    "message": "JSON 문법에 맞게 요청을 작성해주세요."
}
```

- `401 Unauthorized(세션 없음)`
```
{
    "message": "로그인이 필요한 서비스입니다."
}
```

- `404 Not Found(존재하지 않는 일정)`
```
{
    "message": "존재하지 않는 일정입니다."
}
```

---

# 1. 댓글 조회 (Get Comment)
Method: GET

URL: http://localhost:8080/comments

Description: 모든 댓글을 조회합니다.

Request
- body `없음`

---

Response `200 OK`
```
[
    {
        "id": 1,
        "content": "댓글 1",
        "scheduleTitle": "일정 제목 1",
        "userName": "작성자명 1",
        "createdAt": "2026-04-20T16:56:40.391114",
        "modifiedAt": "2026-04-20T16:56:40.391114"
    },
    {
        "id": 2,
        "content": "댓글 2",
        "scheduleTitle": "일정 제목 2",
        "userName": "작성자명 2",
        "createdAt": "2026-04-20T16:56:41.362813",
        "modifiedAt": "2026-04-20T16:56:41.362813"
    }
]
```

---

# ERD 이미지

<img width="914" height="597" alt="Image" src="https://github.com/user-attachments/assets/b755de03-6090-4f85-be50-2ffa0a84d013" />
