This repo wraps https://github.com/KhronosGroup/VK-GL-CTS/ to allow it to work with Android Studio. This allows for step through debugging through the Android Studio UI.

To set up this project first clone the VK-GL-CTS repo into the app/src/main/cpp/ folder and fetch the remote sources:
```git clone https://github.com/KhronosGroup/VK-GL-CTS/ app/src/main/cpp/VK-GL-CTS/ && cd app/src/main/cpp/VK-GL-CTS/ && python3 ./external/fetch_sources.py```

Then update the "ANDROID_NDK_PATH" variable in /app/build.gradle.kts
```"-DANDROID_NDK_PATH=/Users/<USERNAME>/Library/Android/sdk/ndk/27.0.11718014/", //FIXME: add your user name here ```

Then open the project in Android Studio and run/debug it. :)

To run a specific test use these launch flags:
```
-e "cmdLine"  "deqp --deqp-case=\"dEQP-VK.wsi.android.swapchain.private_data*\" --deqp-log-images=disable --deqp-log-shader-sources=disable --deqp-log-filename=/data/user/0/com.example.deqptest/files/TestResults.qpa"
```
