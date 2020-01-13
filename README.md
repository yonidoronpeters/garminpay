# Sample application for interacting with FitPay API
This app exposes a single endpoint `http://localhost:8080/compositeUsers/:userId` which retrieves a user and returns a response containing the userId, along with the user's devices and credit cards.
It takes an optional `creditCardState` and `deviceState` filter query parameters.  

To run this app locally you will have to get a client id and client secret for the QA environment as well as Java 11 installed locally.
```
$ git clone https://github.com/yonidoronpeters/garminpay.git 
$ cd garminpay
$ export FITPAY_CLIENT_ID=<yourClientId> && export FITPAY_CLIENT_SECRET=<yourClientSecret> && mvn spring-boot:run
```                
The app is also deployed to Heroku for ease of testing. Example resource for a user: https://garminpay-interview-yoni.herokuapp.com/compositeUsers/b1ad2850-6ba5-4922-8332-9ec5eae2fc67

Please note that the first request may take a few seconds because the Heroku dyno is sleeping due to the use of a free tier.
