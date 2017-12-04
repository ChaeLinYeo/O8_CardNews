#Specification introduction for developers
---

## Contents
* __Developement Environment__
* __Code Instruction__
  * Setting custom saving directory
  * Changing the form of saved file name
    * *Changing file name of card news you made*
  * Setting Custom Url link
  * Modifying res/values/\*.xml
    * *strings.xml*
    * *styles.xml*


---
## Developement Environment
#### Developement base: Android Studio 3.0.1
  - __System Requirements for Android Studio 3.0.1__
    - Operation System: Microsoft Windows 10, 8.1, 7 / Mac OS X 10.10 Yosemite ~ macOS 10.12 Sierra / GNOME or KDE desktop (64-bit version which can run 32-bit application)
    - RAM: Minimum 3GB / Recommended 8GB
    - HDD: 2GB (for Studio) + 2GB (for Android SDK, Emulator System Image, Cache)
    - Display Resolutions: Minimum 1280 * 800
    - Java: Java Developement Kit 7 (Oracle for Linux) / Java Runtime Enviroment 6 (only for OS X)
    - Hardware Acceleration(Option): Intel® processor with support for Intel® VT-x / Intel® EM64T (Intel® 64) / Execute Disable (XD) Bit functionality
  - __Application Requirements__
    - Operation System: Android 4.2 or higher

---



##Code Instruction

  * ### Setting custom saving directory
    In `BaseActivity.class`, go to here:
    ```Java
      protected Uri getOutputMediaFiel() {
        if (isSDCARDMounted()) {
          File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), PHOTO_PATH);
          // Create a storage directory if it does not exist
          if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
              Log.d("MediaAbstractActivity",
              getString(R.string.directory_create_fail));
              return null;
            }
          }
          .
          .
        }
      }
      ```

    `Enviroment.DIRECTORY_PICTURES` set the saving directory of pictures as `/mnt/sdcard/Android/data/<package name>/files/Pictures`.
    <br>
    If you want to set your own directory, recode like this:
    ```Java
    // Get absolute path of sdcard
    String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath()
    // Set custom path under the absolute path of sdcard
    // Type your custom path starts from absolute path at <custom>
    String customPath = absolutePath + "/<custom>"
    // Create new File object for custom directory
    File path = new File(customPath);
    //Make custom directory if it doesn't exist.
    if (!path.exists()) {path.mkdir();}
    ......
    ```

    You can use `path` as `mediaStorageDir` in first code block.
    <br>
* ### Changing the form of saved file name
    To set your own form of file name, edit this part:
    ```Java
    .
    .
    .
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File mediaFile;
            selectedOutputPath = mediaStorageDir.getPath() + File.separator
                   + "IMG_" + timeStamp + ".jpg";
            Log.d("MediaAbstractActivity", "selected camera path "
                   + selectedOutputPath);
            mediaFile = new File(selectedOutputPath);
            return Uri.fromFile(mediaFile);
        } else {
            return null;
        }
    }
    ```
    If you want to change the form of expressing the date saving your files, change String `"yyyyMMdd_HHmmss"` at first line as what you want. If you don't want to include save date in your file name, you can delete first line and modify `"IMG_" + timeStamp + ".jpg"` to different name not include timeStamp. Notice that you must set your file name saved as different name each, or it will overwrite your file everytime when you saving your files.
    <br>For example, you can use variable like this:
    ```Java
    .
    .
    .
            File mediaFile;
            int i = 0;
            selectedOutputPath = mediaStorageDir.getPath() + File.separator
                   + "My pictures No-" + i + ".jpg";
            Log.d("MediaAbstractActivity", "selected camera path "
                   + selectedOutputPath);
            mediaFile = new File(selectedOutputPath);
            i += 1;
            return Uri.fromFile(mediaFile);
        } else {
            return null;
        }
    }
    ```
    File will save as "*My pictures No-1.jpg*", "*My pictures No-2.jpg*", ... goes on.
    * #### Changing file name of card news you made
      You can also change the form of name for your card news at `PhotoEditorActivity.class`
      ```Java
          public void onFinish() {
          String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
          String imageName = "CARD_" + timeStamp + ".jpeg";
          Intent returnIntent = new Intent();
          returnIntent.putExtra("imagePath", photoEditorSDK.saveImage("CardNewsMaker",         imageName));
          setResult(Activity.RESULT_OK, returnIntent);
          finish();
          }
        }.start();
      }

      private void saveAndShareThisImage() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "Card_" + timeStamp + ".jpg";
        String imagePath = photoEditorSDK.saveImage("CardNewsMaker", imageName);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("imagePath", imagePath);
        setResult(Activity.RESULT_OK, returnIntent);
      .
      .
      .
      ```
    <br>
* ### Setting Custom Url link
  ```Java
      @Override
      public void onClick(View v) {
          webView.loadUrl("http://news.naver.com/main/hotissue/sectionList.nhn?mid=hot&sid1=110&cid=1010805");
      }
  });
  ```
  You can change the linked Url(Basically setted to Naver Card News Url) as you want. Simply Change the parameter as another Url address in method `webView.loadUrl()`.
  <br>
* ### Modifying res/values/\*.xml
  * #### strings.xml
  ```Java
    <string name="app_name">Card News Making Tool</string>
    <string name="title_activity_photo_editor">PhotoEditorActivity</string>
    <string name="done">Done</string>
    <string name="save">Save</string>
    <string name="clear_all">Clear All</string>
    <string name="camera">Camera</string>
    <string name="gallery">Gallery</string>
    <string name="upload_dialog_title">Upload Image</string>
    <string name="upload_picker_title">Select Picture</string>
    <string name="directory_create_fail">Failed to create directory</string>
    <string name="access_media_permissions_msg">To attach photos, we need to access media on your device</string>
    <string name="continue_txt">Continue</string>
    <string name="not_now">NOT NOW</string>
    <string name="media_access_denied_msg">You denied storage access, no photos will be added.</string>
  ```
  You can change the massages and various strings to what you want at `strings.xml`. Be careful about the references of edited strings.

    * #### styles.xml
    ```Java
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
    ```
    You can customize your application theme at here. Add, remove or exchange the color for your theme as you want.

    ```Java
    <style name="SplashTheme" parent="@android:style/Theme.Light.NoTitleBar">
       <item name="android:windowBackground">@drawable/roading</item>
   </style>
    ```
    You can change the loading image of your app. Set the image to what you want at `@drawable`.
