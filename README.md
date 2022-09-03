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
* GET templates/login -> POST UserController -> UserService -> BrokerExchange api/user/login - User(username + password) ->
  - [on success] UserApiDto received + autContext setup (apiKey, avatarUrl, channels) -> redirect root
  - [on error] catch HttpClientErrorException -> throw LoginUnsuccessfulException -> RestResponseEntityExceptionHandler -> display error on login

* GET root -> GET MainController -> MessageService -> BrokerExchange api/channel/get-messages - MessagesGetDto(channelId, channelSecret, count) ->
  - handling multiple channels: for general channel -> id & secret are null - on channelId param -> channelSecret is loaded via autContext
  - Message[] (via MessagesGetDto) received & added to the model attributes - > templates/index generated

* POST root -> POST MainController -> on blank message redirect root -> MessageService -> BrokerExchange api/message - MessagePostDto ->
  - MessagePostedDto(content, created, UserIdDto author) + parsing ISO DateString to LocalDateTime -> redirect root


## Lessons learned  
1. Consuming API services workflow with Spring RestTemplateBuilder exchange function
   - automated serialization / deserialization of DATA (DTO objects/ Json String)
2. Creating Request HttpEntity with custom HttpHeaders incl. API key
3. Java Generics - BrokerExchange -> automated HTTP response entity generation

4. Utilizing java URI class to create valid endpoint paths
5. @JsonProperty() -> usage for data parsing
6. DateTime conversions from/to different time zones (UTC to CEST - local default)
7. Displaying images for avatars & channel thumbnails
