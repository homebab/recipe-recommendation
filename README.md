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
recipe recommendation API service for the following:
  
- recommend recipes
- search recipes

## Intro
#### keywords
- Webflux - Reactive, Non-Blocking IO, Async
- Kotlin - Functional, Coroutine
- Elasticsearch

## Get Started
Recipe Recommender API service is deployed on public container instances of AWS ECS. <br>
So, it is possible only on Prod to connect with AWS ES on Private Subnet. <br>
In order words, It is impossible to connect with AWS ES on Dev. In development, we launch ES locally with docker-compose. 
### Dev
- Build * Run
```shell script
# build jar file
> ./gradlew build
 
# build image and run locally
> docker-compose up --build
```
- API Spec: http://localhost:8090/omtm/recipe-recommender/swagger-ui.html

### Prod
- Deploy to Amazon ECS using github action 
    - path: .github/workflows/aws.yml
- Task Definition for ECS service
    - path: ./task-definition.json
    - env: ES_URL=https://vpc-omtm-recipe-elastic-mrjubur6xsdevr3mx5nfcdqof4.ap-northeast-2.es.amazonaws.com
    