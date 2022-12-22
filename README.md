# Thoughtctl Assignment Android APP
This is a sample Android App that shows a list of top images of the week from IMGUR Gallery

# Building the Sample App
First, clone the repo: git clone git@github.com:deepak-1212/ThoughtCTL.git

Next, to get api access: you will have to create your Account on Imgur gallery through their website and register your application to get access token.
This is where you will be asked to register your application: https://api.imgur.com/oauth2/addclient.

Once you have the access token, go to ImgurGalleryRepository under repository folder and change the access token which you got it from the above step

Building the sample then depends on your build tools.

# Android Studio (Recommended)
(These instructions were tested with Android Studio Dolphin | 2021.3.1 Patch 1)

Open Android Studio and select File->Open... or from the Android Launcher select Import project (Eclipse ADT, Gradle, etc.) and navigate to the root directory of your project.
Select the directory or drill in and select the file build.gradle in the cloned repo.
Click 'OK' to open the the project in Android Studio.
A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

# Build Configs
To convert xml into a class, viewBinding can be used. This means a binding class is generated for each XML layout file that the module contains

```
...
buildFeatures {
  viewBinding = true
}
...
```

# Third Party Libraries
Retrofit 2: This library is used to connect with RESTful web services
GSON: This library is used to convert json objects to POJOs, retrofit uses it to convert the response through its factory class
Glide: This library is used to show images, it provides image loading and caching

# Running the Sample App
Connect an Android device to your development machine.

## Android Studio
Select Run -> Run 'app' (or Debug 'app') from the menu bar
Select the device you wish to run the app on and click 'OK'

# Using the Sample App
Each time you press the toggle button, the list showing style will change from a grid view to linear view or vice versa. Listing of items are done in an reverse chronological order. You can also filter the list using search functionality present at the toolbar.
