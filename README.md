# Travel_Planner_BE

## Explore Rest APIs

The app defines following CRUD APIs.

### Auth

| Method | Url                | Decription | Sample Valid Request Body | 
| ------ |--------------------|------------|---------------------------|
| POST   | /api/auth/login    | Login      | [JSON](#login)            |
| POST   | /api/auth/register | Register   | [JSON](#register)         |

## Sample Valid JSON Request Bodies

##### <a id="login">Login -> /api/auth/login</a>
```json
{
	"fullName": "Nguyen Van A",
	"username": "vannguyen",
	"email": "abcd@gmail.com",
	"password": "password"
}
```

##### <a id="signin">Log In -> /api/auth/signin</a>
```json
{
	"usernameOrEmail": "vannguyen",
	"password": "password"
}
```