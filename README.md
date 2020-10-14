# Actionsheet-android

[![API](https://img.shields.io/badge/API-16%2B-red.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![](https://jitpack.io/v/mkhoiron/Actionsheet-android.svg)](https://jitpack.io/#mkhoiron/Actionsheet-android/4)

Actionsheet-android is an Android Library to build Actionsheet like iOS UIActionSheet component, iOS7 style, custom color etc

![alt text](https://image.ibb.co/bzgpFy/mm_min.png)
![alt text](https://www.appcoda.com/wp-content/uploads/2014/05/t11_1_normal_action_sheet.jpg)

## Installation

-  Add the following to your project level `build.gradle`:
 
```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```
  -  Add this to your app `build.gradle`:
 
```gradle
dependencies {
	implementation 'com.github.mkhoiron:Actionsheet-android:4'
}
```

## Using Java

```java
ArrayList<String> data = new ArrayList<>();

data.add("Delete it");
data.add("Copy");
data.add("Move");
data.add("Duplicate");

new ActionSheet(MainActivityJava.this,data)
                        .setTitle("What do you want to do with the file")
                        .setCancelTitle("Cancel")
                        .setColorTitle(getResources().getColor(R.color.title))
                        .setColorTitleCancel(getResources().getColor(R.color.action))
                        .setColorData(getResources().getColor(R.color.action))
                        .create(new ActionSheetCallBack() {
                            @Override
                            public void data(@NotNull String data, int position) {
                                switch (position){
                                    case 0:
                                        // your action
                                    case 1:
                                        // your action
                                    case 2:
                                        // your action
                                    case 3:
                                        // your action
                                }
                            }
                        });
```
  -  In Kotlin
```kotlin
val data by lazy { ArrayList<String>() }

data.add("English")
data.add("Indonesia")

ActionSheet(this, data)
                   .setTitle("Select Country")
                   .setCancelTitle("Cancel")
                   .setColorTitleCancel(Color.parseColor("#FF4081"))
                   .setColorTitle(Color.parseColor("#FF4081"))
                   .setColorData(Color.parseColor("#FF4081"))
                   .create(object :ActionSheetCallBack{
                       override fun data(data: String, position: Int) {
                           if ("English".equals(data)){
                               // your action
                           }else if("Indonesia".equals(data)){
                               // your action
                           }
                           setToast(data)
                       }
                   })
```
  
## With variable

  -  Initialize variable:
```java
ActionSheet actionSheet;

actionSheet = new ActionSheet(MainActivityJava.this, data)
                        .setTitle("What do you want to do with the file")
                        .setCancelTitle("Cancel")
                        .setColorTitle(getResources().getColor(R.color.title))
                        .setColorTitleCancel(getResources().getColor(R.color.action))
                        .setColorData(getResources().getColor(R.color.action));
                        
actionSheet.create(new ActionSheetCallBack() {
                    @Override
                    public void data(@NotNull String data, int position) {
                        // switch or if handle
                    }
                });
```
## update feature

Function      				   | description
-------------------------------------------| -------------
.hideTitle()  			           | hide title
.setFontData(R.font.meryana_script)        | change font data
.setFontCancelTitle(R.font.meryana_script) | change font cancel
.setFontTitle(R.font.meryana_script)       | change font title
.setSizeTextCancel(30)			   | change size text cancel
.setSizeTextData(30)		           | change size text data
.setSizeTextTitle(30)		 	   | change size text title



License
=======
Copyright 2018 [M Khoiron](https://linkedin.com/in/khoiron)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Al-hamdulillahi Rabbil 'alamin And Special thanks to [JetBrains](https://github.com/JetBrains) and [jitpack.io](https://github.com/jitpack-io) for their contributions to this project.
