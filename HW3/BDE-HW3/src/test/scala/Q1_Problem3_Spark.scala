import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object Q1_Problem3_Spark {
  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate()
    val sparkContext: SparkContext = sparkSession.sparkContext

    var source = sparkContext.textFile("src/test/scala/data/friends.json")

    val namesArray = source.map(x => (x.substring(2, x.length-2).split("\", \"")(0), 1))
      .reduceByKey((A, B) => A+B).take(100)
    for (name <- namesArray){
      println(name)
    }
  }
}
