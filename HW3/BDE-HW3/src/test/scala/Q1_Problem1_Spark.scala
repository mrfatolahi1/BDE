import scala.collection.mutable.ListBuffer
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.rdd


object Q1_Problem1_Spark {
  def main(args: Array[String]): Unit = {
    val a:SparkContext = new SparkContext()

    a.textFile("src/test/scala/data/books.json")
      .flatMap(x => x.split("\n"))
      .map(m => (m.split(" ")(0).substring(2, m.split(" ")(0).length-2), m.split("\", \"")(1)))
      .map(m => (m._1, m._2.replace(",", "").replace(",", "")))

    println()

  }
}
