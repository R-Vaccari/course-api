# Language Courses API
RESTful API made in Spring Boot.
This API executes Post, Put, Get and Delete operations on a Mongo database.
Objects are Students, Teachers, Courses and Classes. A Course is represented by a language, and may contain various Classes. Each Class
has a Teacher and a set of Students associated with it. Classes are also divided by their level, such as Business or Beginner.

## How to use
The API may be accessed here: 
https://rvapp-course-api.herokuapp.com/swagger-ui.html

Each Object has a resource associated with it. Click on one of them in order to see their possible operations.

**GET** endpoints will search the database for the respective resource's object. All Objects may be listed with the first **GET**. Then, they may be found by their id, which is listed with the previous method.

Classes may be also found by their ClassLevel. Possible entries are these (must match exactly): **BEGINNER**, **INTERMEDIATE**, **ADVANCED**, **BUSINESS**.

Courses may be also found by their CourseType. Possible entries are these (must match exactly): **ENGLISH**, **PORTUGUESE**, **GERMAN**, **ITALIAN**.

**POST** endpoints will register a new Object entry. Simply fill the attributes as you wish.

**PUT** endpoints will update a registered Object. In order to update, fill the **Id** field with the id of the Object you wish to update. Below that, fill in the attributes you wish to update. Attributes may be deleted in this field, meaning they will not be changed.
The method will *only* change the fields executed in this operation.

**DELETE** will delete an Object entry. Simply put the Object's id in the **Id** field and execute.
