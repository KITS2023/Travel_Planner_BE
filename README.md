# Travel_Planner_BE

## Explore Rest APIs

The app defines following CRUD APIs.

### Auth

| Method | Url                     | Decription     | Sample Valid Request Body | 
|--------|-------------------------|----------------|---------------------------|
| POST   | /api/auth/login         | Login          | [JSON](#login)            |
| POST   | /api/auth/register      | Register       | [JSON](#register)         |
| GET    | /api/auth/resetPassword | Reset Password | [JSON](#resetPassword)    |



## Sample Valid JSON Request Bodies

##### <a id="register">Login -> /api/auth/login</a>
```json
{
	"fullName": "Nguyen Van A",
	"username": "vannguyen",
	"email": "abcd@gmail.com",
	"password": "password"
}
```

##### <a id="login">Log In -> /api/auth/signin</a>
```json
{
	"usernameOrEmail": "vannguyen",
	"password": "password"
}
```

##### <a id="resetPassword">Log In -> /api/auth/resetPassword</a>
```code
Request Param:
  usernameOrEmail=vannguyen
```
