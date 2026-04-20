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
    "author": "일정 작성자"
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
    "author": "일정 작성자",
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

Response `(200 OK)`
```
JSON
[
    {
        "id": 1,
        "title": "일정 제목 1",
        "content": "일정 내용 1",
        "author": "일정 작성자 1",
        "createdAt": "2026-04-16T18:00:00",
        "modifiedAt": "2026-04-16T18:00:00"
    },
    {
        "id": 2,
        "title": "일정 제목 2",
        "content": "일정 내용 2",
        "author": "일정 작성자 2",
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
    "author": "일정 작성자 1",
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
    "content": "변경할 일정 내용",
    "author": "변경할 일정 작성자"
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
    "author": "변경할 일정 작성자",
    "createdAt": "2026-04-16T15:00:00",
    "modifiedAt": "2026-04-16T21:00:00"
}
```
---
# 5. 선택 일정 삭제 (Delete Schedule)
Method: DELETE

엔드포인트: http://localhost:8080/schedules/{scheduleId}

Description: 선택한 일정을 삭제합니다.

Request `없음`
Response `(204 No Content)`

---
# ERD 이미지

<img width="496" height="292" alt="Image" src="https://github.com/user-attachments/assets/87933aef-b611-41b6-9d3a-f3a1d1a08cf1" />