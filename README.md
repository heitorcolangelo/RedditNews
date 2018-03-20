# README #

## Build & Run ##

These are instructions on how to build and run the app.

### Clone the repository ###

``` groovy
git clone https://github.com/heitorcolangelo/RedditNews.git
```

### Execute gradlew task ###

Navigate to project root folder, and execute the task to build and install the apk.
``` groovy
./gradlew installDebug
```

## Tests ##

These are instructions on how to run tests.

### Execute instrumented tests ###
``` groovy
./gradlew app:connectedAndroidTest
```

### Execute unit tests ###
``` groovy
./gradlew repository:test
```

### Execute Jacoco test report ###
``` groovy
./gradlew app:jacocoTestReport
```

That's all!