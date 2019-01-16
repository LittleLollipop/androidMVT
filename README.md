# MVT

![https://github.com/LittleLollipop/androidMVT/blob/master/Design/App.png](https://github.com/LittleLollipop/androidMVT/blob/master/Design/App.png)


## MVT

Machine View Tools，此库试图解决高复杂度的业务逻辑在长期迭代中的稳定性，以及保障功能库/模块 的高健壮性。如果在较简单的业务中使用可能体验会有些繁琐。
保证稳定性/健壮性的逻辑是依靠完备状态机对每个状态的清晰定义保障逻辑隔离，依靠状态机迁移时的检查保障程序运行状态正确。通过将功能抽象为工具类/静态方法，将逻辑用状态机表达，实现分离业务功能与业务逻辑。

## App Machine

被设计用来管控App运行状态以及进行全局性操作，同时管理其他状态机。
	
## Business Machine

所有业务的管理器，其中提供了业务模块的基类。业务基类中提供了基本的四种状态，并限定同一时间只有一个业务可以处于前台运行状态。
在业务切换时，管理器会限制切换流程复合设计，只有切换流程完全执行完才会解锁业务切换功能，这个特性可以保障业务模块切换时出现并发逻辑可以安全运行，并且不需要在业务中做额外逻辑处理这种并发，所以同时也提供了高度解耦业务的可能。


## 这些部分的设计可能也需要了解
	
UI部分的设计

## 这里有些工具，被设计用来表达纯粹的逻辑

State Machine

Mission

Task Loader

Manifold Valve

## 如何使用
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  	dependencies {
	        implementation 'com.github.LittleLollipop:androidMVT:0.0.17'
	}
