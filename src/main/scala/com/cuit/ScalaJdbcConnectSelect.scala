package com.cuit

/**
 * Created by Administrator on 2017/12/23.
 */
import java.sql.{Connection, DriverManager}

import scala.collection.mutable.ArrayBuffer


object ScalaJdbcConnectSelect extends App {


  // 访问本地MySQL服务器，通过3306端口访问mysql数据库
  val url = "jdbc:mysql://123.56.125.48:3306/commentanalysis?serverTimezone=UTC"
  //驱动名称
  val driver = "com.mysql.cj.jdbc.Driver"

  //用户名
  val username = "mrli"
  //密码
  val password = "Liqi@233526"
  //初始化数据连接
  var connection: Connection = _

  def insert(movieName: Map[String,String])={

      //注册Driver
      Class.forName(driver)
      //得到连接
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
    statement.executeUpdate("delete from temp")
    for (elem <- movieName) {
      statement.executeUpdate(
        s"INSERT INTO temp (`name`, `people`) VALUES ('${elem._1}','${elem._2}')")
    }
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