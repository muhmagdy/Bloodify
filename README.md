# Bloodify
## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
<!-- * [Screenshots](#screenshots)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact) -->
<!-- * [License](#license) -->
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
