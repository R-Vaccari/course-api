# Language Courses API
RESTful API made in Spring Boot. Industry-standart API that executes CRUD operations on a MongoDB database.

The API may be accessed here: 
https://rvapp-course-api.herokuapp.com/swagger-ui.html

## How It Works
This API works in the context of a language courses app. It consists of 4 layers - Resources, Services, Domain and Repository - and a Model of 4 objects

Layers | Classes | Function
------------ | ------------- | -------------
Resources | AuthenticateResource & respective object Resources | Provide HTTP endpoints for CRUD operations, such as GET and POST.
Services | Object Services | Services connect Resources layer with the app data. Services access the Repositories in order to retrieve requested data or modify stored data according to the request, such as deleting or updating.
Domain | Course, ClassGroup, Teacher, Student | Objects that define the language courses context.
Repository | Object Repositories | This layer communicates with the database. Each object has a respective repository in order to separate their implementations. 

## How to use

### Authentication
Firstly, use the **POST** method in the Authenticate-resource in order to generate a JWT token. Use this request body:

{"password": "password",
 "username": "user"}
 
Then, copy the token generated in the response body. Click on the **Authorize** button and add:
"Bearer " + token. It will look like this: "Bearer ey [...] 5hTU"
 
You are now authorized to use all other methods.
Each Object has a resource associated with it. Click on one of them in order to see their possible operations.

### Endpoints

/authenticate/



/courses/

/courses/{id}

/courses/typesearch?="TYPE"  -- Possible entries are these (must match exactly): **ENGLISH**, **PORTUGUESE**, **GERMAN**, **ITALIAN**.



/classes/

/classes/{id}

/classes/levelsearch?="LEVEL" -- Possible entries are these (must match exactly): **BEGINNER**, **INTERMEDIATE**, **ADVANCED**, **BUSINESS**.


/teachers/

/teachers/{id}



/students/

/students/{id}


Contact Me | Links
------------ | -------------
E-mail | rodrigo.v.melo@hotmail.com
LinkedIn | https://www.linkedin.com/in/rodrigo-vaccari-3128b7167
