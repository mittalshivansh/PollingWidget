### Given Problem Statement ###

"Build a Poll Widget with Text
[See example](https://cl.ly/428948d9eb48)

Build a Poll Widget with Images
[See example](https://cl.ly/132aa3808c01)

Rules:
- Both widgets should be in the same android app
- Do not worry about the fancy design, images and the timer animation
- Code should be tested with at least one UI test
- Image list should be scrollable with 4 elements
- UI should retain state between orientation change (without any manifest modification to android:configChanges)
- Bonus if percentages updates over time (eg every 1sec)."


### My Approach ###

* Skipping DI for now to hustle up
* Implementing MVVM 
* ViewModel to survive the state during orientation change
* LiveData for reactive bindings
* Coroutines for async tasks 

### Pending TODOs ###
* Move common traits and code of poll widget in BaseWidget to keep the code dry
* make all BaseDao function suspendable
* Todo : add foriegn key  declarations in schema, timertask or any other alternative to refresh percentage every second
    
     