# In-App Update and Review Library

## Overview

This library provides seamless integration for In-App Updates and In-App Reviews, supporting Android 14 (SDK 34). It leverages the latest releases of Play In-App Update (2.1.0) and Play In-App Review (2.0.1) to ensure your app stays current and receives user feedback efficiently.

## Documentation

### Dependency Configuration

Add the following configuration to your `settings.gradle` or `build.gradle` (depending on your project structure):

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Then, add the library dependency to your `build.gradle`:

```gradle
dependencies {
    implementation 'com.github.SabbirMMS:In-App-Update_Review:1.0'
}
```

### Usage Guide

To use this library in your project, follow the example below:

```java
public class MainActivity extends AppCompatActivity {

    // Declare an instance of InAppUpdateHelper
    InAppUpdateHelper inAppUpdateHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the InAppUpdateHelper
        inAppUpdateHelper = new InAppUpdateHelper(MainActivity.this);

        // Request a flexible in-app update
        // Pass "true" for an immediate update if required
        inAppUpdateHelper.launchInAppUpdate(false);

        // Request the in-app review dialog
        // The maxOpen parameter specifies the number of app launches before the review dialog is shown
        // For example, if the value is set to 5, the review dialog will appear on the 6th launch of the app
        inAppUpdateHelper.requestInAppReview(5);
    }
}
```

### Key Features

- **Flexible In-App Updates**: Prompt users to update your app without interrupting their usage.
- **Immediate In-App Updates**: Ensure critical updates are installed immediately.
- **In-App Review**: Request user reviews after a specified number of app launches to gather feedback and improve your app's rating.

### Getting Started

1. **Install the Dependency**: Add the required dependency to your project.
2. **Initialize the Helper**: Create an instance of `InAppUpdateHelper` in your main activity.
3. **Configure Update and Review**: Call the appropriate methods to handle updates and request reviews.

### Future Enhancements

- **Enhanced User Experience**: Future versions will include more customization options for update prompts and review dialogs.
- **Comprehensive Analytics**: Track user interactions with update and review prompts to better understand engagement.

## Credit

Developed by [Sabbir MMS](https://github.com/SabbirMMS)
