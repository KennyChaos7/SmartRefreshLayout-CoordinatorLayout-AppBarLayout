#	融合 CollapsingToolbarLayout +SmartRefreshLayout 的效果

当然，按照国际惯例，先上个效果图
> device-2021-06-08-104734.png
> device-2021-06-08-104753.png
> device-2021-06-08-104824.png
> device-2021-06-08-104941.png


![](https://upload-images.jianshu.io/upload_images/3012678-bdc245bb489b8981.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/3012678-2558fdf912f4af65.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/3012678-7ec9da6f42b1f55c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

 ![](https://upload-images.jianshu.io/upload_images/3012678-e26eee111a3599d4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

​	



- **CoordinatorLayout** **AppBarLayout** **CollapsingToolbarLayout** **Toolbar** 

    > 上面这四个布局其实除了**Toolbar**之外，可能都不是场景常见布局，所以在这里会贴一些自己的理解和一些参考的文章

    - **CoordinatorLayout**



- ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        android:orientation="vertical">
        <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:background="@drawable/ic_launcher_background">
            <TextView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:text="header"
                android:gravity="center"/>
        </FrameLayout>
        <com.scwang.smart.refresh.layout.SmartRefreshLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fitsSystemWindows="true"
            app:srlEnableRefresh="true"
            app:srlEnableLoadMore="true"
            android:id="@+id/smartrefreshlayout"
            tools:context=".ScrollingActivity">
            <com.scwang.smart.refresh.header.ClassicsHeader
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>
            <androidx.coordinatorlayout.widget.CoordinatorLayout
                android:id="@+id/coordinatorlayout"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:fitsSystemWindows="true">
                <com.google.android.material.appbar.AppBarLayout
                    android:id="@+id/app_bar"
                    android:layout_width="match_parent"
                    android:layout_height="250dp"
                    android:fitsSystemWindows="true"
                    android:theme="@style/Theme.MyApplication.AppBarOverlay">
    
                    <com.google.android.material.appbar.CollapsingToolbarLayout
                        android:id="@+id/toolbar_layout"
                        android:layout_width="match_parent"
                        android:layout_height="200dp"
                        android:fitsSystemWindows="true"
                        app:contentScrim="?attr/colorPrimary"
                        app:layout_scrollFlags="scroll|exitUntilCollapsed"
                        app:toolbarId="@+id/toolbar">
                        <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="match_parent"
                            android:orientation="vertical"
                            app:layout_collapseMode="parallax">
                            <TextView
                                android:layout_width="match_parent"
                                android:layout_height="150dp"
                                android:text="CENTER"
                                android:gravity="center"
                                android:textSize="22sp"
                                android:textColor="@color/black"/>
                        </LinearLayout>
                        <androidx.appcompat.widget.Toolbar
                            android:id="@+id/toolbar"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            app:layout_collapseMode="pin"
                            app:popupTheme="@style/Theme.MyApplication.PopupOverlay"
                            android:layout_gravity="bottom">
                            <TextView
                                android:layout_width="match_parent"
                                android:layout_height="50dp"
                                android:textColor="@color/white"
                                android:layout_gravity="bottom"
                                android:gravity="center"
                                android:text="1"
                                android:textSize="18sp"/>
                        </androidx.appcompat.widget.Toolbar>
                    </com.google.android.material.appbar.CollapsingToolbarLayout>
                    <TextView
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:textColor="@color/white"
                        android:layout_gravity="bottom"
                        android:gravity="center"
                        android:text="2"
                        android:textSize="18sp"/>
                </com.google.android.material.appbar.AppBarLayout>
                <include layout="@layout/content_scrolling" />
            </androidx.coordinatorlayout.widget.CoordinatorLayout>
            <com.scwang.smart.refresh.footer.ClassicsFooter
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>
        </com.scwang.smart.refresh.layout.SmartRefreshLayout>
    </LinearLayout>
    ```

    > ​	这里就不贴Activity了，因为并没有所有的效果实现并不需要Activity的参与

    如果做到这一步，你就会发现，其实这里的效果跟实际的效果图的效果不一致，或者应该说完全没有想要的效果。

    这是因为下面这两行代码

    ```xml
    ...
     <androidx.appcompat.widget.Toolbar
        android:layout_height="wrap_content"                                
     	...
     	android:layout_gravity="bottom">
         ...
     </androidx.appcompat.widget.Toolbar>
    ... 
    ```

    这里我们把**Toolbar**设置成了位于**CollapsingToolbarLayout**的最底部，而且由于高度没有限制，是随着子布局的扩大而自适应的**wrap_content**，从而使得在滑动时**AppBarLayout**计算**CollapsingToolbarLayout**与顶边的高度为0，没有可以折叠的距离，从而使得整个折叠效果不存在

    解决方法也很简单，三选一即可：

    	1.	去掉**android:layout_gravity="bottom"**，改用**android:layout_marginTop="xx"**来间接设置可折叠的距离
    	2.	给定**Toolbar**一个固定的高度
    	3.	给**Toolbar**设置1px的高度，把折叠后要显示的布局放在**CollapsingToolbarLayout**内 **Toolbar**同级 （如果你不希望你的布局会在下拉加载时被折叠，则最好使用此方法） 

    最后给个正常有效果的xml看看吧

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        android:orientation="vertical">
        <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:background="@drawable/ic_launcher_background">
            <TextView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:text="header"
                android:gravity="center"/>
        </FrameLayout>
        <com.scwang.smart.refresh.layout.SmartRefreshLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fitsSystemWindows="true"
            app:srlEnableRefresh="true"
            app:srlEnableLoadMore="true"
            android:id="@+id/smartrefreshlayout"
            tools:context=".ScrollingActivity">
            <com.scwang.smart.refresh.header.ClassicsHeader
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>
            <androidx.coordinatorlayout.widget.CoordinatorLayout
                android:id="@+id/coordinatorlayout"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:fitsSystemWindows="true">
                <com.google.android.material.appbar.AppBarLayout
                    android:id="@+id/app_bar"
                    android:layout_width="match_parent"
                    android:layout_height="250dp"
                    android:fitsSystemWindows="true"
                    android:theme="@style/Theme.MyApplication.AppBarOverlay">
    
                    <com.google.android.material.appbar.CollapsingToolbarLayout
                        android:id="@+id/toolbar_layout"
                        android:layout_width="match_parent"
                        android:layout_height="200dp"
                        android:fitsSystemWindows="true"
                        app:contentScrim="?attr/colorPrimary"
                        app:layout_scrollFlags="scroll|exitUntilCollapsed"
                        app:toolbarId="@+id/toolbar">
                        <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="match_parent"
                            android:orientation="vertical"
                            app:layout_collapseMode="parallax">
                            <TextView
                                android:layout_width="match_parent"
                                android:layout_height="150dp"
                                android:text="CENTER"
                                android:gravity="center"
                                android:textSize="22sp"
                                android:textColor="@color/black"/>
                        </LinearLayout>
                        <androidx.appcompat.widget.Toolbar
                            android:id="@+id/toolbar"
                            android:layout_width="match_parent"
                            android:layout_height="50dp"
                            app:layout_collapseMode="pin"
                            android:layout_gravity="bottom"
                            app:popupTheme="@style/Theme.MyApplication.PopupOverlay">
                            <TextView
                                android:layout_width="match_parent"
                                android:layout_height="50dp"
                                android:textColor="@color/white"
                                android:layout_gravity="center"
                                android:gravity="center"
                                android:text="1"
                                android:textSize="18sp"/>
                        </androidx.appcompat.widget.Toolbar>
                    </com.google.android.material.appbar.CollapsingToolbarLayout>
                    <TextView
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:textColor="@color/white"
                        android:layout_gravity="bottom"
                        android:gravity="center"
                        android:text="2"
                        android:textSize="18sp"/>
                </com.google.android.material.appbar.AppBarLayout>
                <include layout="@layout/content_scrolling" />
            </androidx.coordinatorlayout.widget.CoordinatorLayout>
            <com.scwang.smart.refresh.footer.ClassicsFooter
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>
        </com.scwang.smart.refresh.layout.SmartRefreshLayout>
    </LinearLayout>
    ```

    

