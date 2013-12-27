Project intro
================================

-   This project is based on **rabbitmq**, and **nncloudtv**.
-   Its job is simply reading things in the queue and executing the URL defined in the task. 

Packaging from Eclipse
================================

-   Right click the project
-   Export **-->** Java **-->** Runnable jar file
-   Configuration:  
    Launch configuration, **Main**  
    Export destination, **nnqueue\nnqueue.jar**  
    Library handling: Package required libraries into generated jars  
    Finish

Packaging from Apache Maven
================================

-   Reference **installer/build.py**
-       :::bash
        mvn assembly:single  
    A big JAR file with dependencies included will be built under the **target/** called **nnqueue-0.0.1-SNAPSHOT-jar-with-dependencies.jar**  
    After packaging, you have runnable standalone JAR file

        :::bash
        java -jar "target/nnqueue-0.0.1-SNAPSHOT-jar-with-dependencies.jar"

Run
================================

    :::bash
    mvn exec:java
    
    ## - OR - ##
    
    nohup java -jar nnqueue.jar &

In the pipeline
================================

-   error handling, logging

