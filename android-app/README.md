# Android App

Android app that fetches from a [API REST](https://api.punkapi.com/v2/beers) beers and
shows them in a list.

Sound easy, right? Well there's a catch `[o_o]`. The challenge here is to use all tha Android
cutting edge libraries and Kotlin capabilities.

The app fetches data from cloud an then store it, when there's is no internet connection
it will fetch the data from a local databse.

**The app follows the requirements of the challenge**


## App flow

![](./art/app-flow.gif)


## Infinite Scrolling using [Paging](https://developer.android.com/topic/libraries/architecture/paging)

![](./art/infinite-scroll-demo.gif)

