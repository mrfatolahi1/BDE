import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object Q1_Problem1_Spark {
  def main(args:Array[String]): Unit ={
    val sparkSession: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate()
    val sparkContext: SparkContext = sparkSession.sparkContext

    val a = sparkContext.textFile("src/test/scala/data/books.json")

    a.map(_.split(" "))
      .flatMap(x => x.drop(1)
        .map(y => {
        val set = scala.collection.mutable.Set[String]()
        set+=x(0)
        (y, set)
      }))
      .reduceByKey(_++_)
      .foreach(println)
  }
}
