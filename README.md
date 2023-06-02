# IBM CHallenge: Cloud Native Application Developer

## Description

By using this application, it will be possible to:

- Obtain a gif according to entered string which will be interpreted by AI.
- See last 5 entered requests with date.

## Requirements

- Newest JAVA Version (at the time I'm writing this, it's Version 8 Update 341) to run application
- Maven to "build" application
- SQL based database (used MySQL database)
- Git (optional)
- These system environment variables must be set to appropriate values:
    - 
- MYSQL_HOST
- MYSQL_PORT
- MYSQL_USER
- MYSQL_PASSWORD
- APIKEY - Watson Natural Language Understanding API KEY
## Installation

In order to build this application, you need to download .ZIP file of code or clone Git repository.
After extracting/cloning repository, go into "IbmChallenge" folder and using PowerShell/Command prompt,
use "mvn package" to build the application. All commands in order using Git Bash are listed below:
```
git clone https://github.com/zeman37/IbmChallenge
cd IbmChallenge
mvn package
```
built application will appear in target folder:

*\IbmChallenge\target\IbmChallenge-0.0.1-SNAPSHOT.jar

## Running application
To run application, open PowerShell/Command Prompt in the same folder where "IbmChallenge-0.0.1-SNAPSHOT.jar"
is located. Use command:
`java -jar IbmChallenge-0.0.1-SNAPSHOT.jar` to run the application.
Application will be accessible via: "http://localhost:8080"

## Using Application
Wait, gif will appear soon:)

![](https://github.com/zeman37/IbmChallenge/blob/master/HowToUse.gif?raw=true)
