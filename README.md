# common-Utils
因为有插件一些植入

2021-01-13

记得在根目录下的build.gradle

添加：


    // Top-level build file where you can add configuration options common to all sub-projects/modules.

    buildscript {
        ext.kotlin_version = '1.3.50'
        repositories {
            google()
            jcenter()

    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.2'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath 'org.greenrobot:greendao-gradle-plugin:3.3.0'//greendao
        classpath 'com.github.dcendents:android-maven-gradle-plugin:2.1'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
              }
    }

    allprojects {
        repositories {
            google()
            jcenter()
            maven { url 'https://www.jitpack.io' }
              }
            }

    task clean(type: Delete) {
        delete rootProject.buildDir
    }
