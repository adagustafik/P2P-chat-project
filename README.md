# P2P Chat Project

## Main functionality
Build an instant messaging client consuming provided API broker to retrieve chat data.

1. Registration + Login/Logout
2. View & Update user profile
3. View & Post messages 
5. Create new channels
6. Update my channels

## Deployed on heroku
https://radiant-ocean-51687.herokuapp.com/login


## Tech Stack
**Java 17**
**Gradle + Gradle Daemon**
automation build tools

**Spring framework + Spring Boot**
spring module for RAD (Rapid App Development)

**Lombok**
library of decorators for automated build tools

**Thymeleaf**
template engine


## Architecture
Current solution doesn't persist any data on its own. It fully utilizes the broker storage instead.

### Basic data flow
GET templates/login -> POST UserController -> UserService -> BrokerExchange - new User(username + password) - json format sent 
-> [on success] UserApiDto received + autContext setup (apiKey, avatarUrl, channels) -> redirect index
-> [on error] catch HttpClientErrorException -> throw LoginUnsuccessfulException -> RestResponseEntityExceptionHandler -> display error on login

GET templates/index -> GET MainController -> MessageService -> 


## Lessons learned  
1. Consuming API services
    - rest template

2. DateTime conversions from/to different time zones
3. Displaying images for avatars & channel thumbnails

4. Java Generics - BrokerExchange -> create HTTP request Entity + post
