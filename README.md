# circles-loading-indicator
A customizable loading indicator for Android.


## Maven dependency

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/eu.micer/circlesloadingindicator/badge.svg)](https://search.maven.org/artifact/eu.micer/circlesloadingindicator)

```gradle
implementation 'eu.micer:circlesloadingindicator:<version>'
```

## Usage
```
<eu.micer.circlesloadingindicator.CirclesLoadingIndicator
    android:id="@+id/circles_loading_indicator"
    android:layout_width="48dp"
    android:layout_height="48dp"
    android:layout_gravity="center"
    app:circlesColor="@color/colorAccent"
    app:circlesCount="8"
    app:circlesRadius="4dp"
    app:rotationDuration="3000"
    app:showLeadingLine="false" />
```

## Sample
![Example settings](/screenshots/device-2019-10-06-213739.gif "Example settings")
