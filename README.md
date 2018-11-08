# sparkstreaming-logs
spark streaming 流式日志处理

Spark面试链接：
https://blog.csdn.net/weixin_40691089/article/details/79606260

### Spark Streaming入门

Spark Streaming是否需要独立安装？
One stack to rule them all:一栈式解决

应用场景
* 推荐系统
* 日志处理
* 视频流处理
* 金融欺诈

集成spark生态系统的使用
![](https://i.imgur.com/8UW5NhZ.png)

![](https://i.imgur.com/ICn8adL.png)

![](https://i.imgur.com/L4rJi06.png)

![](https://i.imgur.com/S9jMzho.png)

![](https://i.imgur.com/SYZWnOH.png)

https://github.com/apache/spark

### 示例
输入源：
nc -lk 9999
#### 生产
使用spark-submit来提交我们的spark应用程序运行的脚本
> ./spark-submit --master local[2] \
> --class org.apache.spark.examples.streaming.NetworkWorkCount \
> --name NetworkWordCount \
> /examples/jars/spark-examples_2.11-2.2.0.jar hadoop000 9999

#### 测试
如何使用spark-shell来提交
> ./spark-shell --master local[2]
![](https://i.imgur.com/fZHC3vQ.png)

### Spark Streaming工作原理（粗粒度）
Spark Streaming接收到实时数据流，把数据按照指定的时间段切成一片片小的数据块，然后把小的数据块传给Spark Engine处理。 

![](https://i.imgur.com/lo3ZTHx.png)

---
## Spark Streaming核心概念与编程
### Spark Streaming核心
* 核心概念
* Transformations
* Output Operations
* 实战案例

#### 核心概念
* StreamingContext
一旦StreamingContext定义好之后，可以做一些事情，见官网：
http://spark.apache.org/docs/latest/streaming-programming-guide.html#initializing-streamingcontext
* DStreams（Discretized Streams）离散化流
	* Internally, a DStream is represented by a continuous series of RDDs.
	* Each RDD in a DStream contains data from a certain interval.
对DStream操作算子，比如map/flatMap，其实底层会被翻译为对DStream中的每个RDD都做相同的操作，因为一个DStream是由不同批次的RDD所构成的。
* Input DStreams and Receivers
Every input DStream (except file stream, discussed later in this section) is associated with a Receiver (Scala doc, Java doc) object which receives the data from a source and stores it in Spark’s memory for processing.
