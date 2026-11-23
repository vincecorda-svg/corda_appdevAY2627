# Lab Activity 0B: Android First Build

## Laboratory Discussion

During this laboratory activity, I experienced several challenges while setting up the Android development environment using Android Studio and Flutter. One of the main challenges was configuring the Android Virtual Device (AVD), which involved creating an emulator, selecting a device model, and downloading the necessary emulator components. The installation process took some time because it required additional SDK components and system resources.

Another challenge was setting up the project correctly. I needed to select the appropriate project template, configure the project location, and follow the required naming conventions. I also had to make sure that the Android SDK and other required development tools were properly installed.

To resolve these challenges, I carefully followed the laboratory instructions and checked the Android Studio settings to ensure that the required components were installed. I also allowed Gradle to complete its synchronization and project build process before running the application. Whenever an issue occurred, I reviewed the error messages and checked the project configuration to identify possible problems.

After completing the setup, I successfully created my Android project and prepared the emulator for running the application. This activity helped me understand the basic process of creating an Android development environment, configuring an emulator, managing project settings, and building an application.

Overall, this laboratory activity improved my familiarity with Android Studio and the initial steps of Android application development. It also helped me understand the importance of proper environment configuration, SDK installation, and Gradle synchronization in successfully building and running an Android project.

# Lab Activity 1: Android Studio Bootstrap & Activity Lifecycle

## Discussion

This activity focuses on creating a basic Android application using Android Studio and Java. The project, called **LabCheck**, demonstrates how Android Activity Lifecycle callbacks work, including `onCreate()`, `onStart()`, `onResume()`, `onPause()`, `onStop()`, and `onDestroy()`.

The application records lifecycle events and displays them on the screen with their corresponding timestamps. It also uses Logcat to monitor and verify the application's activity transitions.

### Enhancements Implemented

**Enhancement 1: Application ID and Lab Room Label**

Added the application identification and active laboratory room label (CompLab 1) to the application header to make the application easier to identify.

**Enhancement 2: Clear Log Button**

Added a Clear Log button that allows users to reset the lifecycle trace displayed on the screen. The application also uses `onSaveInstanceState()` and `onCreate()` to preserve and restore the log when the device screen is rotated.

### Challenges Encountered

During the development process, I encountered some issues with XML formatting, View Binding, and the layout constraints of the buttons. I also needed to make sure that the Clear Log button appeared correctly and that the application could handle screen rotation without crashing.

These issues were resolved by correcting the XML layout, updating the Java code, and testing the application again in Android Studio.

### Learning Outcomes

Through this activity, I learned how Android Activity Lifecycle callbacks work and how to monitor application behavior using Logcat. I also gained experience using Java, XML layouts, View Binding, and saving application state during screen configuration changes.

Overall, this activity helped me understand the basic structure of Android application development and how to implement simple but useful application features.