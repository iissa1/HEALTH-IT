#README

This is small application for the purpose of solving a take home interview assignment: 
>Go to the following web site: https://www.healthit.gov/data/datasets/centers-medicare-medicaid-services-cms-ehr-incentive-program-measures.  

>In Java and, using open-source libraries, configure dependencies via Maven, connect to the appropriate endpoint, and print out, by state and
> in descending order, the percentage of eligible and critical access hospitals that have demonstrated Meaningful Use of CEHRT in the year 2014.
> As a plus, please containerize the app and deploy it to Docker Hub. You may use any tool you wish to containerize your application. If you choose to use a tool,
>please include a very short note on what tool you used and how you published it.


# Java Health-IT Data Processor

## Overview
This project is a Spring/Java-based application that fetches and processes data from www.healthit.gov. It extracts and displays state-wise percentages of hospitals demonstrating Meaningful Use of CEHRT in 2014, sorted in descending order. 

## Sorting Order
#### The expected ordering is:
##### Descending by percentage → Highest percentage of hospitals demonstrating Meaningful Use of CEHRT appears first

## Features
- Fetches data from the https://www.healthit.gov/data/open-api?source=Meaningful-Use-Acceleration-Scorecard.csv.
- Processes and displays state-wise hospital percentages.
- Outputs the results in descending order based on `pct_hospitals_mu`.
- Built with Maven for dependency management.
- Containerized using Docker for easy deployment.

## Prerequisites
- Java 17 or later
- Maven
- Docker (for containerized deployment)

## Installation

### Clone the repository
```sh
 git clone https://github.com/iissa1/HEALTH-IT
```

### Build the project
```sh
./gradlew clean build
```

## Usage
### Run the application
```sh
./gradlew run
```
## send api request to see data output 
### once application is running
```
Once running, visit localhost:9091/api for a response and console output 
```

>[!NOTE]
>To build the project or run the project make sure you're in the project directory after cloning. "~/project/HEALTH-IT/"

>[!IMPORTANT]

# Running with Docker
### 1. Build the Application
###### Before running the application in Docker, you need to build the app using Gradle:

```sh
./gradlew clean build
```

This will generate the JAR file (health-it-0.0.1-SNAPSHOT.jar) in the ./build/libs directory.

## 2. Build the Docker Image
Once the JAR is built, you can proceed to build the Docker image:

```sh
docker build -t health-it .
```
## 3. Start the Application
To run the application in a Docker container, use the following command:

```sh
docker run -p 9091:9091 --name health-it  health-it
```
This will:

Start the container with the name health-it.

Map port 9091 inside the container to port 9091 on your local machine.

```sh
 http://localhost:9091 
````
