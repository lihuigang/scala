package sparkScala


import org.apache.spark.sql.types._
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.log4j.{Level, Logger}

object DataFrameTest {
  //Logger.getLogger("org").setLevel(Level.ERROR)
  val conf = new SparkConf().setAppName("test").setMaster("local")
  val spark = SparkSession.builder
    .enableHiveSupport()
    .config(conf)
    .getOrCreate()

  import spark.implicits._

  case class Person(name: String, age: Long)

  val peopleDF = spark.sparkContext.textFile("BigData.txt")
    .map(_.split(","))
    .map(row => Person(row(0), row(1).trim.toInt))
    .toDF()
  peopleDF.createOrReplaceTempView("people")
  val teenagersDF = spark.sql("SELECT name, age FROM people")
  teenagersDF.show()
  teenagersDF.map(teenager => "Name: " + teenager(0)).show()
  def main(args:Array[String]):Unit={
    println(11111)
  }

}
