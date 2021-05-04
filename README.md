### MetaWeather

### About this project
* Android application that shows tomorrow’s weather forecast for specific cities using the MetaWeather REST API.
* User can swipe the screen to view different locations weather information.

### Android Features used:
* Architectural Pattern : MVVM
* Language : Kotlin
* Service call: Retrofit
* DependencyInjection: Dagger2
* DataBinding
* Kotlin Coroutine
*Unit test: Mockito

## Libraries and Concepts used:
 * [Fragment]- A basic unit of reusable UI.
 * ViewPager2-This view allows us display a collection of fragments or views to display to the user in a swipe-able format 
 * [Foundation]- Components for core system capabilities, Kotlin extensions and support for multidex and testing.
  * [AppCompact]- Degrade gracefully on older versions of Android.
  * [Android KTX] - Write more concise, idiomatic Kotlin code.
  * [Data Binding]- The Data Binding Library is a support library that allows us to    bind UI components in our layouts to data sources in our app using a declarative format rather than programmatically.
  * [LiveData] - Build data objects that observes changes  when the underlying database changes.
  * [ViewModel] - Store UI-related data that isn't destroyed on app rotations. Eas
     asynchronous tasks for optimal execution.
  *[Coroutines] - On Android, coroutines help to manage long-running tasks.
  *[Mockito]- Unit testing : Mockito is a popular mock framework which can be used in conjunction with JUnit. Mockito allows us to create and configure mock objects.
  * Dagger2 - Dependency Injection
  * [Glide]- Image loading

### Future Enhancement:
* Instead of hardcoded location name, we should be able to cache it locally. User should be able to search the location and add/delete location.
* Handle UI in Landscape mode.





