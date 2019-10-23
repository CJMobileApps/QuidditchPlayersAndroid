# Quidditch Players Android

This is an app that displays Quidditch players for Android. Each individual player is displayed in a list. The list is the updated every few seconds with a certain player status update.

## Branches

**Master:** This branch uses RxJava/RxKotlin for background threading. The Data Binding Library is used to set data to views.

**Coroutines-And-Kotlinx-Synthetic** This branch uses Coroutines for background threading. Kotlinx Synthetic Library is used to get views.

## Architecture Pattern

**MVVM**

**LiveData**

**Android Jetpack: ViewModel**

**Dagger 2**

**Service and Repository Pattern**

## User Stories / Feature List

The following functionality is completed:

* [X] Create readme
* [X] User story 1: Display players in a list.
     * [X] Part 1: Make call to following end point: https://cjmobileapps.com/api/v1/quidditch/players

     1) Players from JSON
     ```
     [
         {
             "id": 3,
             "firstName": "Harry",
             "lastName": "Potter",
             "yearsPlayed": [
                 1991,
                 1992,
                 1993,
                 1994,
                 1995,
                 1996,
                 1997
             ],
             "favoriteSubject": "Defense Against The Dark Arts",
             "position": 4,
             "imageurl": "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/harry+potter.jpg"
         },
         {
             "id": 4,
             "firstName": "Katie",
             "lastName": "Bell",
             "yearsPlayed": [
                 1991,
                 1992,
                 1993,
                 1994,
                 1995,
                 1996,
                 1997
             ],
             "favoriteSubject": "Transfiguration",
             "position": 1,
             "imageurl": "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/katie+bell.jpg"
         },
         {
             "id": 5,
             "firstName": "Angelina",
             "lastName": "Johnson",
             "yearsPlayed": [
                 1990,
                 1991,
                 1992,
                 1993,
                 1994,
                 1995,
                 1996
             ],
             "favoriteSubject": "Care of Magical Creatures",
             "position": 1,
             "imageurl": "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/angelina+johnson.jpg"
         },
         {
             "id": 6,
             "firstName": "Fred",
             "lastName": "Weasley",
             "yearsPlayed": [
                 1990,
                 1991,
                 1992,
                 1993,
                 1994,
                 1995,
                 1996
             ],
             "favoriteSubject": "Charms",
             "position": 2,
             "imageurl": "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/fred+weasley.jpg"
         },
         {
             "id": 7,
             "firstName": "George",
             "lastName": "Weasley",
             "yearsPlayed": [
                 1990,
                 1991,
                 1992,
                 1993,
                 1994,
                 1995,
                 1996
             ],
             "favoriteSubject": "Charms",
             "position": 2,
             "imageurl": "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/george+weasley.jpg"
         },
         {
             "id": 8,
             "firstName": "Alicia",
             "lastName": "Spinnet",
             "yearsPlayed": [
                 1990,
                 1991,
                 1992,
                 1993,
                 1994,
                 1995,
                 1996
             ],
             "favoriteSubject": "Charms",
             "position": 1,
             "imageurl": "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/alicia+spinnet.jpg"
         },
         {
             "id": 9,
             "firstName": "Oliver",
             "lastName": "Wood",
             "yearsPlayed": [
                 1989,
                 1990,
                 1991,
                 1992,
                 1993,
                 1994
             ],
             "favoriteSubject": "Potions",
             "position": 3,
             "imageurl": "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/oliver+wood.jpg"
         }
     ]

     ```
     * [X] Part 2: Make call to following end point: https://cjmobileapps.com/api/v1/quidditch/positions

     2) Positions from JSON
     ```
     [
         {
             "id": 1,
             "positionName": "Chaser"
         },
         {
             "id": 2,
             "positionName": "Beater"
         },
         {
             "id": 3,
             "positionName": "Keeper"
         },
         {
             "id": 4,
             "positionName": "Seeker"
         }
     ]

     ```
     * [X] Part 3: Combine the two list and show the results in a list.
     * [X] Part 4: Make call to following the following WebSocket end point: http://ios-hiring-backend.dokku.canillitapp.com

     1) Status updates

     ```
     {
        "id":8,
        "status":"Alicia Spinnet is dueling a Slytherin 🐍"
     }

     ```

     * [X] Part 5: Update the players in the list with the correct status update.

Features to be implemented in the future:
* [ ] Add unit test

## Screen shots and Video Walkthrough

Here's a screenshot and walkthrough video of implemented user stories:

<img src='https://i.imgur.com/vjyie1q.jpg' title='User Story 1 Screen shot' width='' alt='User Story 1 Screen Shot' />

<img src='https://i.imgur.com/KyWVPjd.jpg' title='User Story 1 video walkthrough' width='' alt='User Story 1 Video Walkthrough' />


## Open source libraries

- [Retrofit](http://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [OkHttp](http://square.github.io/okhttp/) - An HTTP & HTTP/2 client for Android and Java applications
- [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back
- [RxAndroid](https://github.com/ReactiveX/RxJava) - RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
- [Mockito](https://github.com/mockito/mockito) - Most popular Mocking framework for unit tests written in Java
- [Timber](https://github.com/JakeWharton/timber) - A logger with a small, extensible API which provides utility on top of Android's normal Log class.
