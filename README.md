# employeeREST
## Basic Design
This application uses two microservices to service 4 REST calls <br>
to allow the user to view, modify, add and delete from the employee database. <br>
The data is stored in an AWS mysql database. <br>




## Requirements to Run Program

1. Must have java
1. Must have maven installed.
1. Must have git installed.

## Instructions to Install
1. Get all the code out of git using the following command: <br>
`git clone https://github.com/dstrower/employeeREST`
1. cd employeeREST
1. `mvn clean install`

## Instructions to start
For LINUX, `./startApp.sh` <br>
For WINDOWS, `startApp`

## Rest Commands

## HTTP Header

All require the http header of Content-Type = application/json

## Rest URLs for Different Actions

| HTTP Type | URL                                  | Description                   |
| --------- | -------------                        | ---------                     |
| GET       | http://localhost:8080/employee       | get all employees             |
| GET       | http://localhost:8080/employee?id=5  | get employee with id of 5     |
| POST      | http://localhost:8080/createEmployee | Create new employee           | 
| PUT       | http://localhost:8080/employee       | Update employee               |
| DELETE    | http://localhost:8080/employee?id=8  | Delete employee with if of 8  |

## Sample Request for POST and PUT

### Post
<pre>
   {
      "id" : 8,
      "name": "Amber Johnson",
      "office": "332c",
      "email" : "amber.johnson@oscorp.com",
      "phone" : "415.337.4345",
      "role" : "Senior Project Manager"
    }
</pre>

## PUT
<pre>
    {
      "id" : 8,
      "name": "Randy Johnson",
      "office": "332c",
      "email" : "amber.johnson@oscorp.com",
      "phone" : "415.337.4345",
      "role" : "Senior Project Manager"
    }
</pre>

## Responses for GET all
<pre>
[
    {
        "id": 5,
        "name": "Michael Stone",
        "office": "321b",
        "email": "michael.stone@oscorp.com",
        "phone": "415.331.3321",
        "role": "Teir 3 Support Engineer"
    },
    {
        "id": 8,
        "name": "Amber Johnson",
        "office": "332c",
        "email": "amber.johnson@oscorp.com",
        "phone": "415.337.4345",
        "role": "Senior Project Manager"
    }
]
</pre>

### List of One Employee
<pre>
[
    {
        "id": 5,
        "name": "Michael Stone",
        "office": "321b",
        "email": "michael.stone@oscorp.com",
        "phone": "415.331.3321",
        "role": "Teir 3 Support Engineer"
    }
]
</pre>

## Successful Response for POST and DELETE

http status 200 <br>
"ACCEPTED" <br>

## Sample Error Message
http status code 400 <br>
Message: 
<pre>
{
    "responseMessage": "The employee id must be unique."
}
</pre>