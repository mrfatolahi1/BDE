import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

import scala.collection.mutable.ListBuffer


object Q1_Problem1_Spark {
  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate()
    val sparkContext: SparkContext = sparkSession.sparkContext

    var M = sparkContext.textFile("src/test/scala/data/books.json")
      .flatMap(x => x.split("\n"))
      .map(m => (m.split(" ")(0).substring(2, m.split(" ")(0).length-2), m.split("\", \"")(1)))
      .map(m => (m._1, m._2.replace(",", "").replace(",", "")))
      .map(m => (m._2.split(" ")(_), m._1))

    val a = M.collect()
//    for (line <- a){
//      print(line._2)
//      for (e <- line._1){
//        print(" " + e)
//      }
//    }

//    var words: ListBuffer[String] = ListBuffer()
//    val linesList = M.collect()
//    for (line <- linesList){
//      line._2.split(" ").map(x => words.append(x))
//    }
//
//
//    val words2: List[String] = words.toSet.toList
//    while ()

  }
}
