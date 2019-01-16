![https://github.com/LittleLollipop/androidMVT/blob/master/Design/StateMachine.png](https://github.com/LittleLollipop/androidMVT/blob/master/Design/StateMachine.png)

此库当中的状态机仅提供运行机制，状态完全由子类制定。

为保证状态机内行为有序，核心逻辑运行于独立线程中。

调用changeState方法即可触发状态迁移，状态迁移有三个步骤，check change，state in， state leave，三个方法调用依次执行于状态机内部线程中。
  
  + check change 会传入试图进行迁移的新状态以及当前所处状态，需要开发者根据设计以及其他数据是否齐全决定是否可以进行迁移，如果不可迁移，流程会立即结束。

  + state leave 在check change之后被调用，开发者在这里处理当前状态离开所需要进行的清理工作。

  + state in 在 state leave 之后被调用，开发者在这里处理新状态的启动。
