# Bloodify
## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screenshots)
<!-- * [Setup](#setup)
* [Contact](#contact)  -->
## General Information
- Bloodify is project of swe course 
- What problem does it solve?<br>
Blood donation is a sensitive topic in Egypt. The country has one of the world's lowest rates of blood donation per capita. According to the most recent statistics, only 1% of Egyptians donate blood, while the global average is around 6%. Because of this disparity, blood supplies are in short supply, limiting the number of procedures doctors can perform on patients and leaving many in critical need.
Bloodify was created to solve the problem of blood donation by making it easier and more accessible to the public. Users can sign up for the app and become blood donors in just a few minutes. Users can request blood from others in their area and find the closest one who meets their criteria, as well as the types of donation they require. Bloodify is the first app to offer digital blood coupons that can be redeemed at local blood banks as needed.
Medical institutions can also use the app to post their needs, and the app will find nearby users who can assist. The app also supports blood donation campaigns, where users can donate blood.
- What is the purpose of your project?
1. useing princibles of agile 
2. use project develop tool like Jira 
3. using Design pattern
## Technologies Used
- Flutter
- Spring boot
- Fire base 
- Websocket
- MySQL
## Features
- User registration (Sign Up) and signing in.
- User Profile and Information: User profile contains name, gender, residence, blood type and previous donations.
- Blood Request: Each request has a criteria (required blood type, time, location). A request can be placed by a regular user or medical institution.
- Feed: All requests which haven't been fulfilled within the same location are added to the feed. Users within the same location can view the feed.
- Pushing notification: When a match happens or a blood campaign is held. 
- Chatting: When a match occurs, the two parties can chat with each other to exchange any necessary information.
- Match doner / recipient based on proximity (distance).
- Support hospitals and blood banks share in providing / consuming blood
units.
- Consumption analysis for hospitals and opposite for blood banks.
- Sort blood doner based on proximity.
## Screenshots
- login and signup UI
<p align="center">
<img src="https://user-images.githubusercontent.com/89037036/205827357-78a4092a-39ac-4dfb-8688-483c0e94a52b.png" alt="drawing" height="400"/>
<img src="https://user-images.githubusercontent.com/89037036/205827817-3ebab7e1-ea28-4008-9df6-fd3b5ee43499.png" alt="drawing" height="400"/>
<img src="https://user-images.githubusercontent.com/89037036/205827541-1dcc9d3f-d16f-4441-885d-3df55895352a.png" alt="drawing" height="400"/>
<img src="https://user-images.githubusercontent.com/89037036/205827702-8f974ddc-11da-426f-8613-ae4d682559cd.png" alt="drawing" height="400"/>
</p>
- home pages
<p align="center">
<img src="https://user-images.githubusercontent.com/26904065/208301481-71fe407d-eed0-4bae-9b38-cda395922d5c.png" alt="drawing" height="400"/>
<img src="https://user-images.githubusercontent.com/26904065/208301894-77ec284a-f4b5-4c32-8c96-c4f920c9dc04.png" alt="drawing" height="400"/>
<img src="https://user-images.githubusercontent.com/26904065/208301556-1d035723-1963-4330-a93f-c44ccb8da395.png" alt="drawing" width="400"/>
</p>
- transactions
<p align="center">
<img src="https://user-images.githubusercontent.com/77590247/208730779-995f4682-0b47-4012-acda-c54ca07e1ae2.png" alt="drawing" height="400"/>
<img src="https://user-images.githubusercontent.com/77590247/208731299-749c4a1b-f35a-4e51-bf3d-64941893e0db.png" alt="drawing" height="400"/>
<img src="https://user-images.githubusercontent.com/77590247/208731703-8012b575-3dc6-435b-85fb-39820c916130.png" alt="drawing" height="400" />
</p>
- requiest
<p align="center">
<img src="https://user-images.githubusercontent.com/89037036/208801117-395e7b9c-a396-4580-a697-bab4d5bb86f2.png" alt="drawing" height="400"/>
<img src="https://user-images.githubusercontent.com/89037036/208801241-1a84d4bf-2e7f-45fa-bd7b-a0d4d94238cb.png" alt="drawing" height="400"/>
<img src="https://user-images.githubusercontent.com/77590247/208864991-26b64e79-20f6-4b86-a2a4-4912ca39a2c8.png" alt="drawing" height="400"/>
</p>
- event creation
<p align="center">
<img src="https://user-images.githubusercontent.com/77590247/208864991-26b64e79-20f6-4b86-a2a4-4912ca39a2c8.png" alt="drawing" height="400"/>
</p>
<!-- ## Setup
1. download flutter 
2. download java spring boot
3. download my sql 
4. run spring boot and flutter and connect to ur local host api in flutter side -->
