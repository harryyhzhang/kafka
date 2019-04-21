import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaWordCount {
  def main(args: Array[String]) {
//    if (args.length < 4) {
//      System.err.println("Usage: KafkaWordCount <zkQuorum><group> <topics> <numThreads>")
//      System.exit(1)
//    }

//    val Array(zkQuorum, group, topics, numThreads) = Array("localhost:9092","", "streams-plaintext-input", "1")
//    val sparkConf = new SparkConf().setAppName("KafkaWordCount").setMaster("local[*]")
//    val ssc = new StreamingContext(sparkConf, Seconds(50))
//    ssc.checkpoint("checkpoint")
//
//    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
//    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
//    val words = lines.flatMap(_.split(" "))
//    val wordCounts = words.map(x => (x, 1L))
//      .reduceByKeyAndWindow(_ + _, _ - _, Minutes(10), Seconds(50), 2)
//    wordCounts.print()
//
//    ssc.start()
//    ssc.awaitTermination()


    val conf = new SparkConf().setMaster("local[*]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(2))
    val lines = ssc.socketTextStream("localhost", 9092)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)
    wordCounts.print()
//    lines.print()
    System.out.print(wordCounts.count())
    ssc.start()             // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate

  }
}