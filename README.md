# Generic Node - Socket-based  clients and servers
GenericNode will receive command arguments and then take on the role of a client or server for a basic Key-Value store
## Dependencies

  
  - **Git** - Distributed version-control system
  - **Java SE Development Kit 8**
  - **Maven 3.6.0** - Build Automation System
  - **Docker** - Containerization
 
## Steps to Run Solution 
1. Open Command Line Interface
2. Download code from git repository using command 
    ```
    git clone https://github.com/soniaarora/GenericNode.git
    ```
3. Navigate to solution folder
    ```
    cd GenericNode
    ```
4. Package solution
    ```
    mvn package
    ```
5. Build docker server container
    ```
    cd docker_server
    sudo docker build -t tcss558server .
    ```
6. Run docker server
   ```
   sudo docker run -d --rm tcss558server
   ```
7. Grab Docker Server Container Inet Addr
    ```
    sudo docker ps -a
    sudo docker exec -it <Container ID> bash
    ifconfig
    ```
8. Exit from Docker Server Container
    ```
    exit
    ```
9. Navigate to docker client
    ```
    cd ..
    cd docker_client
    ```
10. Run Udp client command 
    ```
    java -jar GenericNode.jar uc <docker server Inet Addr> 1234 put b 456
    java -jar GenericNode.jar uc <docker server Inet Addr> 1234 get b 
    java -jar GenericNode.jar uc <docker server Inet Addr> 1234 store
    java -jar GenericNode.jar uc <docker server Inet Addr> 1234 del
    ```