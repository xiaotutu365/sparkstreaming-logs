## spark streaming 流式日志处理

本文由Spark Streaming日志处理系统来讲解Spark的相关知识点，包括：
* RDD编程

### RDD编程
RDD：Spark对数据的核心抽象，全称是Resilient Distributed Dataset，弹性分布式数据集。它其实就是分布式的元素集合。
在Spark中，对数据的操作不外乎：
* 创建RDD
* 转化已有RDD
* 调用RDD

操作进行求值。而在这一切的背后，Spark会自动将RDD中的数据分发到集群上，并将操作并行化执行。

#### RDD基础
Spark中的RDD就是一个不可变的分布式对象集合。每个RDD都被分为多个分区，这些分区运行在集群中的不同节点上。

用户可以使用两种方法创建RDD：
* 读取一个外部数据集
* 在驱动器程序里分发驱动器程序中的对象集合（比如list和set）

创建出来后，RDD支持两种类型的操作：
* 转化操作（transformation）：会由一个RDD生成一个新的RDD
* 行动操作（action）：会对RDD计算出一个结果，并把结果返回到驱动器程序中或把结果存储到外部存储系统（如HDFS）中
