# test_pexel

Actions Done: 
Photo list:
  - show list photos
  - load more list photo with icon loading
  - search photos.
  - pull to refresh photo list.
  - show text if list photo empty.
  - show dialog error if have exception.
    
In item photo show info: photo, photographer, size image.
In Photo detail: show image, zoom image with gesture.

Technical:
 Use MVVM pattern, koin DI, Retrofit, Coroutines.
App architecture:
```plaintext
 |data____model
 |   |
 |   |____remote____adapter
 |   |
 |   |         |____api
 |   |
 |   |         |____interceptor
 |   |
 |   |         |____repository
 |   |
 |   |____response
 |
 |presentation----photoList
 |           |____photoDetail
 |utils
```

 Result:
   File video result: app/src/main/assets/result_pexel.mov (https://youtube.com/shorts/-cDKUc3aCo4)

   File APK result: app/src/main/assets/result_pexel.apk
 
