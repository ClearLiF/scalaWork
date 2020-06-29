package com.cuit

import java.sql.DriverManager

import com.cuit.ScalaJdbcConnectSelect.{connection, driver, password, url, username}

import scala.collection.mutable.ArrayBuffer

object JDBCcrud {
  def main(args: Array[String]): Unit = {
    var list =Set[String]()
    list+="weqwd"
   // println(list)
    for (elem <- list) {
      println(elem)
    }
  }
  def insert(movieName: String,peopleName:String)={

    //注册Driver
    Class.forName(driver)
    //得到连接
    connection = DriverManager.getConnection(url, username, password)
    val statement = connection.createStatement

    val rs4 = statement.executeUpdate(
      s"INSERT INTO temp (`name`, `people`) VALUES ('$movieName','$peopleName')")

    println("删除数据完成！")
    //connection.commit()
    connection.close
    //打印返回结果

  }
  def deleteTable()= {
    try {
      //注册Driver
      Class.forName(driver)
      //得到连接
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
      val rs4 = statement.executeUpdate("delete from temp")
      println("删除数据完成！")
      connection.close
      //打印返回结果
    }
  }

}
