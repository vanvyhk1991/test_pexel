# test_pexel

Actions Done: 
Photo list:
  - show list photos
  - load more list photo with icon loading
  - search photos.
  - show text if list photo empty.
  - show dialog error if have exception.
    
In item photo show info: photo, photographer, size image.
In Photo detail: show image, zoom image with gesture.

Technical:
 Use MVVM pattern, koin DI, Retrofit, Coroutines.
App architecture:
 data----model
    |____remote____adapter
    |         |____api
    |         |____interceptor
    |         |____repository
    |____response
 presentation----photoList
            |____photoDetail
 utils

 Result:
   File video result: 
  <video width="30%" controls>
    <source src="app/src/main/assets/result_pexel.mov" type="video/*">
  </video>
 
 
