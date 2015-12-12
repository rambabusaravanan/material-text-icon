# Material Text Icon
A Simple, Light Weight Text Icon Widget for Android ..

[<img src="http://www.megasalesgraphicspack.com/images/download-now.png" height="30" title="Download AAR" />][1]

Tired of downloading every Material Icon images into every drawable folder ..?

It's now made simple as a Text widget with a single font. Now you can display **891 Material Icons** with any size with any color in pretty simple lines as below ..

```xml
<com.rambabusaravanan.TextIcon
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/ic_cloud_download" />
```

and use color and size of the text icon as usual
```xml
    android:textColor="@color/button_material_dark"
    android:textSize="@dimen/icon_size"
```

Import Library
--------

Download [the latest AAR][1] into *app/libs* folder and add the gradle scripts in project's *build.gradle* ..

```groovy
repositories {
    flatDir {
        dirs 'libs'
    }
}

dependencies {
    // other dependencies you need ..
    compile(name:'texticon-v1', ext:'aar')
}
```

Preview in Android Studio
-----
Sometimes, Android Studio may face difficulty in generting sample preview in preview pane. In such cases, [download the font][3] **MaterialIcons-Regular.ttf** into *app/src/main/assets/font*
 folder and try again ..
 
 [<img src="http://www.megasalesgraphicspack.com/images/download-now.png" height="30" title="Download AAR" />][3]

Download Sample
------

Download [the latest APK][2], **a handbook** for all 891 Material Icons and give it a try ..

[<img src="http://www.installads.net/buton/download-apk.png" height="70" title="Handbook APK" />][2]

[<img  src="https://github.com/rambabusaravanan/material-text-icon/raw/master/app/screenshot.jpg" width="300" title="Handbook APK Screenshot" />][2]



 [1]: https://github.com/rambabusaravanan/material-text-icon/raw/master/app/libs/texticon-v1.aar
 [2]: https://github.com/rambabusaravanan/material-text-icon/raw/master/app/texticon.apk
 [3]: https://github.com/rambabusaravanan/material-text-icon/raw/master/app/src/main/assets/font/MaterialIcons-Regular.ttf
