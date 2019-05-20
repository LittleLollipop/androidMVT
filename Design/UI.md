![https://github.com/LittleLollipop/androidMVT/blob/master/Design/UI.png](https://github.com/LittleLollipop/androidMVT/blob/master/Design/UI.png)

页面与逻辑代码的衔接处考虑到复用以及activity管理机制的因素，将activity与UI分离处理，UI的处理全部在Actor当中，作为基类的activity会转发调用给当前所持有的Actor。

Actor本身使用多层装饰器进行创建，将功能以装饰器的形式进行解耦和复用。

此处的设计同时也可以适应系统对于Activity的销毁及重建，任何业务都可以不渗透到Activity层面，重建的Activity只需要重新挂载已存在的Actor，或让对应的Actor重建即可，而页面中所依赖的数据信息被Actor或Business Machine所持有，并不需要在Activity中进行存储读写。


