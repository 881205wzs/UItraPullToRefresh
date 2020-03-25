# UItraPullToRefresh
下拉刷新

## 主要功能
 - 支持下拉未释放时刷新；
 - 支持下拉释放后刷新；
 - 支持自动下拉刷新；
 - 支持上拉加载；
 - 支持自定义下拉头设置；
 
## 效果图

<img src="https://github.com/881205wzs/UItraPullToRefresh/raw/master/default_1.jpg" height="360" width="200"/><img src="https://github.com/881205wzs/UItraPullToRefresh/raw/master/default_2.jpg" height="360" width="200"/><img src="https://github.com/881205wzs/UItraPullToRefresh/raw/master/default_3.jpg" height="360" width="200"/><img src="https://github.com/881205wzs/UItraPullToRefresh/raw/master/default_4.jpg" height="360" width="200"/>

## Import to your project

Step 1.添加Gradle

```kotlin
allprojects {
     repositories {
	...
	jcenter()
     }
}
```

 
Step 2.Add the dependency

```kotlin
dependencies {
      ...
      implementation 'com.davis.ui.pulltorefresh:pulltorefresh:2.1.0'
}
```
