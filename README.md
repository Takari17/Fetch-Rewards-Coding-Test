## Fetch Rewards Coding Test Requirements

Please write a native Android app in Kotlin or Java that retrieves the data from https://fetch-hiring.s3.amazonaws.com/hiring.json.

Display this list of items to the user based on the following requirements:

    1. Display all the items grouped by "listId"
    2. Sort the results first by "listId" then by "name" when displaying.
    3. Filter out any items where "name" is blank or null.

The final result should be displayed to the user in an easy-to-read list.

## My Approach

I architected the app using the MVVM pattern, and I used the following language and libraries:

- Kotlin
- Jetpack Compose
- Retrofit
- GSON
- Coroutines
- JUnit
- Mockito

Given the lack of hard time constraints, I spent additional time writing unit test for the business logic in the ViewModel. I've also wrote thorough documentation throughout the codebase. In total, I've spent around 4-5 hours on this assignment.
