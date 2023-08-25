# Travel_Planner_BE


## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/KITS2023/Travel_Planner_BE.git
```

**2. Run the app using docker**

```bash
docker compose up
```

**3. Test API using Postman**

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/14939620-e06ebf33-45f0-4899-9973-dcde4ddd1b21?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D14939620-e06ebf33-45f0-4899-9973-dcde4ddd1b21%26entityType%3Dcollection%26workspaceId%3Db20622ef-ec4e-4fa1-9be6-e657610de356)

Set token (after login) in Header:
```code
Authorization: Bearer token
 
```


The app will start running at <http://localhost:8080/test/api-doc-travel-planner>

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
  "fullName": "Vuong Duy Hieu",
  "email": "hieudeptrai@gmail.com",
  "username": "hieuvd2",
  "preferences": "Sport",
  "password": "1"
}
```

##### <a id="login">Log In -> /api/auth/signin</a>
```json
{
	"usernameOrEmail": "hieuvd2",
	"password": "1"
}
```

##### <a id="resetPassword">Log In -> /api/auth/resetPassword</a>
```code
Request Param:
  usernameOrEmail=vannguyen
```




