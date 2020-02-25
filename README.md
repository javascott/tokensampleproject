Token Generator Sample program:

The following code generates a token if you use the endpoint:
GET/
localhost:8080/auth

Then if you call any other path with an Authorization token, it will count how many times that has been hit.
For example:
Header:
Authorization: "B40TMVXKTJNECG48VC2GIEF31PWDD55PZJ9SVTORW8PWOENABWBX1XAF9QUQV5VX" 
GET/
localhost:8080/path1


Startup:
To run the file you must have Java 8 (everything else is handled by the fat jar) in your path.
On the commandline

java -jar tokencounter-0.0.1-SNAPSHOT.jar

It should startup on port 8080

Enjoy!!!!
