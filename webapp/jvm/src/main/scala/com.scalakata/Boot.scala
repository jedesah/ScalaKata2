package com.scalakata

import scala.concurrent.duration._

import java.nio.file.Paths
import java.net.URI
import java.io.File

object Boot {
  def main(args: Array[String]): Unit = {
    val (rp :: artifacts :: host :: port ::
         security :: timeoutS :: scalacOptions) = args.to[List]

    val readyPort =
      if(rp == "0") None
      else Some(rp.toInt)

    Server.start(
        Duration(timeoutS), security.toBoolean,
        artifacts.split(File.pathSeparatorChar).map(p ⇒ Paths.get(new URI(p))), scalacOptions, host, port.toInt, readyPort
    )
  }
}