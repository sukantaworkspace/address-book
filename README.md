# address-book
Address Book, an opensource spring boot application

> To get started...

### Step 1

- **Clone the Repository**
    - 👯 Clone this repo to your local machine using `https://github.com/sukantaworkspace/address-book.git`

### Step 2

- **Crate a folder for H2DB**
    - `C:\data`	if not windows user change the path for H2 from `C:\data` to some folder location.
	

### Step 3

- **Build Wisely**
    - Build the app either using maven wrapper available in the class path or use mvn command to run install pom.xml and post build the jar file will be 
	generated along with the test case report

### Step 4

- **Run Wisely**
    - Open command prompt at the root of the application folder, Run app with the externalise the configuration using the command `java -Dspring.config.location=target/application.yml -jar target/address-book-0.0.1-SNAPSHOT.jar`

### Step 5
- **Build sonar way**
	- Run Sonar Qube locally
	- Use the address-book-service and run the following code

```Sonar Qube
// code away!

mvn sonar:sonar -Dsonar.projectKey=address-book -Dsonar.host.url=http://localhost:9000 -Dsonar.login=yoursonarqube-key
```

### Step 6

- **Go to `localhost:8761/swagger-ui.html` and HACK AWAY!** 🔨🔨🔨
- There you will find the API versioning, explore the dropdown individually and will find the API service as `get-all-info` which is available for 
both vesion 1.0 and 1.1 and rest of the API services are available in version 1.1, think of to add authorization

### Step 7

- **Log Analysis using ELK**
	- Two types of log created, there is logstash formatted log as well, so that it can be analyzed in later point of time
    - Run Elastic Search
	- Run Logstash using `"..\bin\logstash" -f "logstash-v2.conf"` going into the config folder of logstash and find the logstash-v2.conf in the root repository.
	- Run Kibana
---

## All-IN-One

- **Modern Web App feature available**
	-Used advice for rescontroller exception hanling
	-Used customised exception and response
	-Used swagger docmentation
---


## Consideration

I have considered the following scenarios !

- Name considered as full name and make the name as key and the name is case insensitive such that Ram and ram will be the same person
the phn no can be same for different name it's allowed, data will be stored successfully.
if two values are new and only diference is case then the 
 latest one will override the previous one if there is a change either of the name and phn no.

- Input can be single data or multiple data and multiple data can be allowed maximum of 1000 rows at a time 
where each and every row contains only name and number 

- If there is same name but the phn no is different, it will override the existing one which will work as update of records here
to manage the concurrent session used h2db along with transactional of spring and hibernate session

- There is a validation of name and phn no, if there is a wrong input given for adding ther records, considered phn no of 10 digits long
Name conatins spaces and alphabets only

- For the other address book, there is no feature for file upload. One need to call the API with the info and it's considered only name while doing the union
of the relative complements, consider only name for fetching and case in sensitive and it's considered that other address book contains valid name
 due to short span of time page table is not implemented but it would be an added feature 

- Assuming it's an open rest api exposed to public, where any user can access it without any api key or JWT
validation
- Unit test case is available for service only, which is 85% covered, as the main business logic is the service end

---

## Support

Reach out to me at one of the following places!

- Email at <a href="sukanta.a1@gmail.com" target="_blank">`Gmail`</a>

- LindIn at <a href="https://www.linkedin.com/in/sukanta-banerjee-6ab375100?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_view_base_contact_details%3BrVVEwMzaRlGWsp3vJ7utzQ%3D%3D" target="_blank">`@linkedIn`</a>

---


## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**
- Copyright 2020 © <a href="https://github.com/sukantaworkspace" target="_blank">Sukanta Workspace</a>.

