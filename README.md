# P2P Chat Project

## Main functionality
Build an instant messaging client consuming provided API broker to retrieve chat data.

1. Registration + Login/Logout
2. View & Update user profile
3. View & Post messages 
5. Create + Update new private channels

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
Current solution doesn't persist any data on its own, rather fully utilizes the broker storage instead.
Possible extension on the logic would be to  create repository for user passwords 
& allow change password feature which broker does not provide.

### Basic data flows
* GET templates/login -> POST UserController -> UserService -> BrokerExchange api/user - User(username + password)
  - -> [on success] UserApiDto received + autContext setup (apiKey, avatarUrl, channels) -> redirect index
  - -> [on error] catch HttpClientErrorException -> throw LoginUnsuccessfulException -> RestResponseEntityExceptionHandler -> display error on login

* GET templates/index -> GET MainController -> MessageService -> BrokerExchange api/channel - MessagesGetDto(channelId, channelSecret, count)
  - -> Message[] (via MessagesGetDto) received & added to the model attributes
  - handling multiple channels: for general channel -> id & secret are null - on channelId param -> channelSecret is loaded via autContext

* POST templates/index -> POST MainController -> MessageService -> BrokerExchange api/message - MessagePostDto
  - -> MessagePostedDto(content, created, UserIdDto author) + parsing ISO DateString to LocalDateTime


## Lessons learned  
1. Consuming API services
    - rest template

2. @JsonProperty("created")
3. DateTime conversions from/to different time zones
4. Displaying images for avatars & channel thumbnails

5. Java Generics - BrokerExchange -> automated HTTP request Entity + post method
