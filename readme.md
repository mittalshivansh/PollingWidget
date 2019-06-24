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
* using LiveData for reactive bindings
* Coroutines for efficient threading
* Giving preference to RecyclerView even for small list. As notifyDataSetChanged or updating data operation for normal LinearLayout will be  more expensive than RecyclerView,
  Also RecyclerView nicer Api helps us to keep the code clean out of the box
    
    