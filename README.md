# Authors Demo
Android Application with new micro blogging platform consisting of only 1 Activity and 2 fragments when the user opens the app should check the network connectivity and if the device connected to internet then hit API and get all authors data and cache them using room. if the app not connected to internet then it will get all cached authors if found. when clicked on any author then open another screen and hit api to get posts for the selected author and cache them using jetpack data store.
# Tech Stack
- Clean Architecture with MVVM
- Modularity
- RxJava
- Coroutines with flow
- Unit test and UI test
- Jetpack Navigation component
- Jetpack data store for caching
- Room for caching
- Dependency Injection: Hilt
- Repository pattern
- LiveData, ViewModel
- Retrofit
- DataBinding
- Recycler view 
- JSON
- Glide for Image Loading
- KTX
- Sdp library for textSize
- ConstraintLayout
- LeakCanary to check memory leaks
# ScreenShots
<img width="209" alt="screen3" src="https://user-images.githubusercontent.com/40995581/154990808-0bcf3b71-6168-4d88-a46d-d9057e8cbcd0.jpg"><img width="208" alt="screen1" src="https://user-images.githubusercontent.com/40995581/154990309-75b31dac-922e-4ec5-a5a6-bbc7d59b79e4.jpg">





