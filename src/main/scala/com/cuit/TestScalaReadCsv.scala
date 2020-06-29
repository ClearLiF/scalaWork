package com.cuit

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
class TestScalaReadCsv {
  def getMovieInfo(movieName: String): String = {
    val spark = SparkSession
      .builder()
      .master("local")
      .appName("Spark sql whole stage example")
      .config("spark.sql.codegen.wholeStage", false)
      .getOrCreate()

    println("init spark")

    val schema = StructType(
      List(
        StructField("movieId", IntegerType, true),
        StructField("title", StringType, true),
        StructField("genres", StringType, true)
      )
    )
    val df = spark.read.format("csv")
      .option("header", true)
      .option("delimiter", ",")
      .schema(schema)
      .load("hdfs://192.168.90.201:9000/spark/movies.csv").cache()
    println(s"read text file, df type = ${df.getClass.toString} rdd type = ${df.rdd.getClass.toString}")
    //The Prime Gig (2000)
    //    println("请输入：")
    //    var movieName = StdIn.readLine()
    val pattern = movieName.toLowerCase().r

    val filter_rdd = df.rdd.filter(i => !pattern.findFirstIn(i.toString().toLowerCase()).toString().equals("None"))
    val temp = filter_rdd.collect().mkString("")

    println("temp  :" + temp)


    //    df.createOrReplaceTempView("people")
    //    spark.sql("select * from people where age > 14").show()

    //    spark.stop()
    temp
  }
}
