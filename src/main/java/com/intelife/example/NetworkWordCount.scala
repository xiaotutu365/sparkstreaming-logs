package com.intelife.example

import org.apache.spark.{SparkConf}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 从socket作为数据发送源，统计词频
  */
object NetworkWordCount {
  def main(args: Array[String]): Unit = {
    // 创建StreamingContext
    // 1、Spark Core:SparkContext
    // 2、Spark Streaming:StreamingContext
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(1))

    ssc.sparkContext.setLogLevel("ERROR")

    val lines = ssc.socketTextStream("192.168.3.116", 9999)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(words => (words, 1))
    val wordCounts = pairs.reduceByKey(_ + _)
    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
  }
}