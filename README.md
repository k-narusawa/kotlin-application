## 認証機能つきAPI

### 起動方法

1. dockerを起動する

```shell
./gradlew clean composeUp
```

2. アプリケーションを起動する

```shell
./gradlew clean bootRun
```

### 使い方

* ログインAPIにてアクセストークンおよびリフレッシュトークンを取得する

リクエスト

```shell
curl -X POST \
  http://localhost:8080/api/login \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
        "loginId":"admin",
        "password": "admin"
}'
```

レスポンス

```shell
{
  "accessToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1MzMyNzFkZC00NjE5LTRiMWUtOTMyNC02MjA0NzIwOGJiMjQiLCJleHAiOjE2NTk3NzgwNDgsImlhdCI6MTY1OTc3NjI0OH0.F-Pz2TLsVOLLHiXoaUo7LDmAfHHXeN_uNgKkTJOyDg9ZfRRSx1SZ9DCp1X7-B7PdkN8cQZSJasGzCGeE5x_AfQ",
  "refreshToken":"owCuqmKjAX"
}
```

* 取得したアクセストークンを使用してToDoのCRUD機能を使用できる

リクエスト

```shell
curl -X GET \
  'http://localhost:8080/api/todos?keyword=%E5%8B%89%E5%BC%B7&doneFlg=false&limit=5&offset=5' \
  -H 'authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1MzMyNzFkZC00NjE5LTRiMWUtOTMyNC02MjA0NzIwOGJiMjQiLCJleHAiOjE2NTk3NzgyODgsImlhdCI6MTY1OTc3NjQ4OH0.3-EMJraTB8EXsZplgvIvyttcfA3rpDu4qFD2Ge2Zyc4_WUU5fI9A-qKnGD1fvjOyxQqWu7bjuhCT1iWl_sYI1w' \
  -H 'cache-control: no-cache'
```

レスポンス

```shell
[
    {"todoId":6,"userid":"533271dd-4619-4b1e-9324-62047208bb24","todo":"勉強","timeLimit":"2022-08-06T09:08:00","priority":"HIGH","doneFlg":false,"createdAt":"2022-08-06T17:57:10","updatedAt":"2022-08-06T17:57:10"},
    {"todoId":7,"userid":"533271dd-4619-4b1e-9324-62047208bb24","todo":"勉強","timeLimit":"2022-08-06T09:08:00","priority":"HIGH","doneFlg":false,"createdAt":"2022-08-06T17:57:10","updatedAt":"2022-08-06T17:57:10"},
    {"todoId":8,"userid":"533271dd-4619-4b1e-9324-62047208bb24","todo":"勉強","timeLimit":"2022-08-06T09:08:00","priority":"HIGH","doneFlg":false,"createdAt":"2022-08-06T17:57:10","updatedAt":"2022-08-06T17:57:10"},
    {"todoId":9,"userid":"533271dd-4619-4b1e-9324-62047208bb24","todo":"勉強","timeLimit":"2022-08-06T09:08:00","priority":"HIGH","doneFlg":false,"createdAt":"2022-08-06T17:57:10","updatedAt":"2022-08-06T17:57:10"},
    {"todoId":10,"userid":"533271dd-4619-4b1e-9324-62047208bb24","todo":"勉強","timeLimit":"2022-08-06T09:08:00","priority":"HIGH","doneFlg":false,"createdAt":"2022-08-06T17:57:10","updatedAt":"2022-08-06T17:57:10"}
]
```