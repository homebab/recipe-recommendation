```
                         ______    _______  _______  ___   _______  _______  
                        |    _ |  |       ||       ||   | |       ||       | 
                        |   | ||  |    ___||       ||   | |    _  ||    ___|   
                        |   |_||_ |   |___ |       ||   | |   |_| ||   |___    
                        |    __  ||    ___||      _||   | |    ___||    ___|   
                        |   |  | ||   |___ |     |_ |   | |   |    |   |___    
                        |___|  |_||_______||_______||___| |___|    |_______|   
 ______    _______  _______  _______  __   __  __   __  _______  __    _  ______   _______  ______   
|    _ |  |       ||       ||       ||  |_|  ||  |_|  ||       ||  |  | ||      | |       ||    _ |  
|   | ||  |    ___||       ||   _   ||       ||       ||    ___||   |_| ||  _    ||    ___||   | ||  
|   |_||_ |   |___ |       ||  | |  ||       ||       ||   |___ |       || | |   ||   |___ |   |_||_ 
|    __  ||    ___||      _||  |_|  ||       ||       ||    ___||  _    || |_|   ||    ___||    __  |
|   |  | ||   |___ |     |_ |       || ||_|| || ||_|| ||   |___ | | |   ||       ||   |___ |   |  | |
|___|  |_||_______||_______||_______||_|   |_||_|   |_||_______||_|  |__||______| |_______||___|  |_|

```
recipe API service for the following:
  
- recommend recipes
- search recipes


### Get Started
- Build * Test
```shell script
# build
> ./gradlew build
 
# Dockerize
> docker-compose up --build
```
- OpenAPI: http://localhost:8090/omtm/scraper/swagger-ui.html

### Deploy
- Deploy to Amazon ECS using github action 
    - path: .github/workflows/aws.yml
- Task Definition for ECS service
    - path: ./task-definition.json