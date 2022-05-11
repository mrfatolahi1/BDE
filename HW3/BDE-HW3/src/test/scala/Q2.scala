import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, countDistinct, udf}

object Q2 {
  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate()
    val sparkContext: SparkContext = sparkSession.sparkContext
    val trainCSVRDD = sparkContext.textFile("/home/mohammadreza/Documents/data/facebook-recruiting-iii-keyword-extraction/Train.csv")

    var df = sparkSession.read.format(source = "csv").option("header", "true").load("/home/mohammadreza/Documents/data/facebook-recruiting-iii-keyword-extraction/Train.csv")
//    println(d)

    val tf = df.toDF("Id","Title","Body","Tags")
      .groupBy("id", "title")
      .count.withColumnRenamed("count", "tf")
      .take(100)/*.foreach(println(_))*/

    df = df.groupBy("token").agg(countDistinct("id") as "df")
    val docCount = df.count
    val calcdfUdf = udf { df: Long => math.log((docCount + 1)/(df + 1))}
    val idf = df.withColumn("idf", calcdfUdf(col("df")))
    idf.take(100).foreach(println(_))

  }
}
