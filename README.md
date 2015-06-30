# CardStackView
Pretty useless trick to emulate "Card Stack effect". Support custom stack size(0..5) and 2 stack direction(Down, Up). 
Both can be changed at runtime.

##How to use:

1. Clone this lib and reference :app module inside of your module's gradle.build
2. Wrap your card layout(which can be anything actually) around my com.pavelsikun.cardstackview.CardStackView :
```xml
  <com.pavelsikun.cardstackview.CardStackView
        android:id="@+id/stack"
        android:layout_width="220dp"
        android:layout_height="130dp"
        stack:stackSize="5"
        stack:stackDirection="up"
        android:layout_centerInParent="true">
        <TextView
            android:layout_width="220dp"
            android:layout_height="100dp"
            android:background="@drawable/card"
            android:layout_gravity="bottom"
            android:gravity="center"
            android:text="YOUR CARD"/>
    </com.pavelsikun.cardstackview.CardStackView>
```
3. CardLayout should be 30dp smaller(in height) that CardStack.
4. Custom tags:
  
  * ```stack:stackSize="5" ``` <-- anything from 0..5
  * ```stack:stackDirection="up" ``` <--- "up" or "down". If stack is "up", then please make sure that child has android:layout_gravity="bottom".
    If. Initial stackDirection is "down", then there's no need to specify layout gravity manually. 
5.  If managing from code, we can call next methods to the CardStackView:

  * ```setStackDirection(int direction) ```, where direction could be either CardStackView.DIRECTION_DOWN or CardStackView.DIRECTION_UP;
  * ```getStackDirection() ```, if => 0 == DIRECTION_DOWN, 1 == DIRECTION_UP;
  * ```getStackSize() ```
  * ```setStackSize(int size) ```, could be 0..5;
