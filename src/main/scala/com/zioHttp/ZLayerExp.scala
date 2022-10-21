package com.zioHttp


import zio._

import scala.collection.mutable

case class ZLayerExp(map: Ref[mutable.Map[String, Experiments]]) extends ExpEnvironment {
  def register(user: Experiments): UIO[String] = {
    for{
      id <- Random.nextUUID.map(_.toString)
      _ <- map.updateAndGet(_ addOne(id, user))}
    yield id
  }

  def lookup(id: String): UIO[Option[Experiments]] = {
    map.get.map(_.get(id))
  }

  def users: UIO[List[Experiments]] = {
    map.get.map(_.values.toList)
  }
}

object ZLayerExp {
  def layer: ZLayer[Any, Nothing, ZLayerExp] = {
    ZLayer.fromZIO(
      Ref.make(mutable.Map.empty[String, Experiments]).map(new ZLayerExp(_))
    )
  }
}