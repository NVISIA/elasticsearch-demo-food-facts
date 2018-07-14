# Demos

Below are the full details of demos for the Food Facts Elasticsearch presentation. 

# Demo Contents

[Demo 1 - Project Download and IDE Setup](#demo-1-project-download-and-ide-setup)


# Demo 1 Project Download and IDE Setup

In this demonstration, I'll be showing you how to get the demo project from GitHub. I'll walk you through cloning the project then adding it to an IDE.

# Demo 1.1 - Project Download

1. Open a browser to:  
    ```cmd
    https://github.com/NVISIA/elasticsearch-demo-food-facts
    ```
    1. This is the project we will use for the demo.
    2. I'm going to use a command window to retrieve the project.
2. Open a command prompt 
3. I'll be using the folder ~/elasticsearch for the demo, so I'll navigate to that folder. 
4. I'm going to run the command below:
    ```cmd
    git clone https://github.com/NVISIA/elasticsearch-demo-food-facts.git
    ```
5. Now, change to the project folder directory:
    ```cmd
    cd elasticsearch-demo-food-facts
    ```
6. And finally, checkout the start tag for the project. This will put the project in a detached head state: 
    ```cmd
    git checkout tags/start
    ```

Once this is complete, we have the initial copy of the application and can load it into our IDE.

## 1.2 - IDE Setup

Next, let's add the project to the Intellij IDEA IDE.

1. I've opened the IntelliJ IDEA new project form. 
2. I'm going to select to open an existing project from source
3. Select the build.gradle file from the project's root directory
4. Make sure that JDK 8 is selected
5. Import the project. Once this is complete, we've successfully loaded the project template. Now, let's try to run the application to assure it works. 
6. Select ESFoodFactsApp in the toolbar run configurations, then click the icon to run. 
7. This will start the application using Spring Boot's runner
8. If you see the message that the application is running and you don't get any exceptions, it means you have successfully set up the project.
9. Note here in the log that we have the Spring Boot and Spring versions.
10. We are using the dev spring profile. That's simple a spring profile used by default to configure for a development environment. 
11. We are using an Undertow embedded web server to run the application
12. Finally, note the port is 8080.
13. As a last step, let's verify runtime. 
14. Open the rest client in Intellij. You can also use a browser or REST client tool if you wish. 
15. Enter the request below: 
    ```
    host/port = http://localhost:8080
    path = /actuator/health
    ```
16. This is one of many URLs provided by the actuator dependency to provide you with metrics and observability around the application. I've included it as part of the template to support any issues you may run into along the way with the course.

This completes setup of the application. 

# Demo 2 Running Elasticsearch

I've provided support for running a Docker image of Elasticsearch. 

