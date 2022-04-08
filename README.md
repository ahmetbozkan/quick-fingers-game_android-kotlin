# Quick Fingers, Fast Typing Game - Android/Kotlin
---
Quick Fingers is a simple game that the user have to write given words in a limited time with the best accuracy possible. This app is remake of the first <a href="https://play.google.com/store/apps/details?id=ahmet.bozkan.quickfingers&hl=tr&gl=US" target="_blank">Quick Fingers</a> version that is released on the Google Play Store in 2020.

## Description
---
The main goal of this project is to revise the <a href="https://play.google.com/store/apps/details?id=ahmet.bozkan.quickfingers&hl=tr&gl=US" target="_blank">old Quick Fingers</a> that I developed when I was new to the Android environment with much more modern, cleaner and structured way. The application is developed with using some of the most current Android libraries such as <a href="https://developer.android.com/topic/libraries/architecture/datastore?gclid=EAIaIQobChMIv-zh_rSF9wIVU5nVCh2MCwW7EAAYASAAEgKoofD_BwE&gclsrc=aw.ds" target="_blank">Jetpack DataStore</a>, <a href="https://developer.android.com/kotlin/coroutines?gclid=EAIaIQobChMI7Me-lrWF9wIV1PhRCh1OOwC_EAAYASAAEgJk9_D_BwE&gclsrc=aw.ds" target="_blank">Kotlin Coroutines</a>, <a href="https://developer.android.com/training/dependency-injection/hilt-android" target="_blank">Hilt for DI</a>, <a href="https://developer.android.com/training/data-storage/room" target="_blank">Room Database</a> etc. 
</br></br>
This application is not developed for any financial purpose as it is not released in Google Play Store like old quick fingers, also the application is not fully finished. It is developed just for learning purposes and come up with a practical example using current knowledge.

## Architecture
---
This application is developed with following the <a href="https://developer.android.com/jetpack/guide?gclid=EAIaIQobChMImePW5LiF9wIVEdN3Ch2kgg1OEAAYASAAEgJ2-vD_BwE&gclsrc=aw.ds" target="_blank">Model-View-ViewModel (MVVM)</a> and Clean Architecture methodologies to create clean, maintainable code and lifecycle aware structure. Application is following the Single-Activity pattern as it also does not contain much screens. 
</br></br>
The words in this application are not coming from any remote web server and application does not contain any web service communication tools/libraries like retrofit, okhttp etc. All the words in the application is are prepared in a json format and stored in local database (Room). The words.json file is used to <a href="https://developer.android.google.cn/training/data-storage/room/prepopulate?hl=en" target="_blank">Prepopulate</a> the Room database single time when the app is first installed with using <a href="https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase.Callback" target="_blank">RoomDatabase.Callback</a>. These database operations and other long running operations are executed asynchronously with using <a href="https://developer.android.com/kotlin/coroutines?gclid=EAIaIQobChMI7Me-lrWF9wIV1PhRCh1OOwC_EAAYASAAEgJk9_D_BwE&gclsrc=aw.ds" target="_blank">Kotlin Coroutines</a> for protecting UI from blocked by these long operations. Other than Room database, <a href="https://developer.android.com/topic/libraries/architecture/datastore?gclid=EAIaIQobChMIv-zh_rSF9wIVU5nVCh2MCwW7EAAYASAAEgKoofD_BwE&gclsrc=aw.ds" target="_blank">Jetpack DataStore</a> is used to store small amount of data like User settings (app language, theme etc.) as key-value pairs in thread-safe way with of course Kotlin Coroutines. Also application uses <a href="https://developer.android.com/guide/navigation/navigation-getting-started" target="_blank">Navigation Components</a> to handle navigation between fragments.
</br></br>
The UI of the application is also developed with following <a href="https://material.io/" target="_blank">Material Components</a> principles and guidelines.

### Libraries Used
---
- <a href="https://developer.android.com/jetpack/guide" target="_blank">Architecture</a>
  - <a href="https://developer.android.com/guide/navigation?gclid=Cj0KCQiAj9iBBhCJARIsAE9qRtB8q19xWrOMU0xmUn61XdeIv8N7920hIVv1NtWswr5ZegovD3HwUYsaAm2IEALw_wcB&gclsrc=aw.ds" target="_blank">Jetpack Navigation</a> - To handle in-app navigation.
  - <a href="https://developer.android.com/training/data-storage/room" target="_blank">Room Database</a> - To store larege amount of data such as words in this app's case (The application contains more than 160000 words).
  - <a href="https://developer.android.com/topic/libraries/architecture/livedata" target="_blank">LiveData</a> - To build a lifecycle-aware dataset and notify the UI immediately when related data changes.
  - <a href="https://developer.android.com/jetpack/guide" target="_blank">Model-View-ViewModel</a> - To have much more maintainable and clean code, lifecycle-aware structure.
  - <a href="https://developer.android.com/training/dependency-injection/hilt-android" target="_blank">Dagger-Hilt</a> - For dependency injection.
  - <a href="https://developer.android.com/topic/libraries/architecture/datastore?gclid=EAIaIQobChMIv-zh_rSF9wIVU5nVCh2MCwW7EAAYASAAEgKoofD_BwE&gclsrc=aw.ds" target="_blank">Jetpack DataStore</a> - To store small sized data with key-value pairs.
