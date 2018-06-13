已知扫地机器人有move(), turn_left(k), turn_right(k), clean()方法，机器人能面向东南西北四个方向，
move是按当前方向移动一格，如果不能移动返回false; turn_left(k), turn_right(k)是旋转k*90度; 
房间里可能有障碍物，机器人并不知道房间的布局，设计算法让扫地机器人清扫房间（走完房间每一格）。

Given a robot cleaner in a room modeled as a grid.
Each cell in the grid can be empty or blocked.
The robot cleaner with 4 given APIs can move forward, turn left or turn right.
When it tries to move into a blocked cell,. 一亩-三分-地，独家发布
its bumper sensor detects the obstacle and it stays on the current cell.

The 4 APIs are:
clean(): clean the current location.
turnleft(k=1): turn left k*90 degrees.
turnrigt(k=1): turn right k*90 degrees.
move(direction=None): move forward for 1 position, return False if that’s not possible.

其中关于 `move` 这个 API 看到两个版本：一个是没有 parameter，每次就朝机器人面向的方向前进一步，所以需要自己在递归中维护方向；一个是可以传 direction 进去，让机器人直接朝那个方向走一步。

两个版本试下来都能实现，但第一个版本难一些，所以我估计第二个版本应该是第一个版本做不出来的时候，面试官用来降低难度的版本吧。

以下分成三个部分，.本文原创自1point3acres论坛
API, Robot and Room
DFS + 手动维护方向
DFS + move 可传参方向



代码可以在 https://github.com/jaychsu/algorithm/blob/master/other/robot_cleaner.py 看到，这里着重总结，代码就只放连结了。
代码的正确性可以在 repo 的根目录透过这条命令检查 `python -m doctest -v other/robot_cleaner.py`
测试的代码在注释中，以 `>>>` 和 `...` 开头
http://www.1point3acres.com/bbs/thread-416332-1-1.html

1. API, Robot and Room
代码：L159-L299，https://github.com/jaychsu/algorithm/blob/master/other/robot_cleaner.py

实现肯定有很多种，但我倾向把 Room 和 Robot 解耦。因为其实做到后来你会发现，Robot 根本不需要知道他在 Room 的实际座标，也不需要知道在 Room 的相对方位，在搜索的过程中维护一套相对的就行（我觉得还挺 make sense 的，毕竟我们在路上走也不需要知道实际经纬度和实际方位）。

Room 负责记录实际的座标和 robot 的所在座标，用来判断 robot 是否撞墙，以及房间是不是已经干净，简单来说这个 API 有上帝视角。
Robot 只记录 robot 面向的方向，以及跟 Room 说我要朝这个方向走，由 Room 返回有没有撞墙。
具体实现其实不难，我只稍微提一下方位，和面经里的分享一样，我用了 0, 1, 2, 3 来代替方位，这样做的好处是要转换方位只需要 `(i + k) % 4` 就行。python 里面能直接 `(i - k) % 4`，也可以直接 `(i - k + 4) % 4` 先换成正数。


2. DFS + 手动维护方向
代码：L325-L371，https://github.com/jaychsu/algorithm/blob/master/other/robot_cleaner.py#L325-L371

手动维护方向稍微 tricky 一些，可以对照代码仔细思考以下这三句话。
- 进格子：举个实例吧，假设当前位于 O 格子，上下左右分别为 UDLR，那么我要往周围移动的方向要顺着 DFS 的特点，D -> R -> L -> U（只要是十字形的移动就行，使得能够尽可能的直走，以及递归退回来的时候能面向进来时候的反向，比如 R -> U -> D -> L 也行）。. from: 1point3acres 
- 换方向：比如以下代码，是对应前一步进格子的 D，也就是往下走的部分 (在 robot_cleaner.py 的 L334-L338)
# down
复制代码
大白话就是，如果下方 (D) 没去过，而且没墙，就去 (DFS)，回来之后 (机器人面向上方) 转右边进去右方 (R)；如果不能去下方，那么 (机器人面向下方) 转左边进去右方。
- 出格子：要让递归返回的时候，Robot 刚好朝向进去格子的反方向(用前述十字形的移动)，如此才能在递归完准备离开当前格子的时候调用 robot.move() 离开。


3. DFS + move 可传参方向. from: 1point3acres 
代码：L393-L417，https://github.com/jaychsu/algorithm/blob/master/other/robot_cleaner.py#L393-L417

没前面那么复杂，中心思想就两个。1) 如果能走，就直接过去 2) 如果走到一个走过的格子，就退回去，然后转回原来的方向




