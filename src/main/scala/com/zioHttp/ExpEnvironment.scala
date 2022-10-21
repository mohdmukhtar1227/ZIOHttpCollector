package com.zioHttp


import zio._

trait ExpEnvironment {
  def register(user: Experiments): Task[String]

  def lookup(id: String): Task[Option[Experiments]]

  def users: Task[List[Experiments]]

}

object ExpEnvironment {
  def register(user: Experiments): ZIO[ExpEnvironment, Throwable, String] =
    ZIO.serviceWithZIO[ExpEnvironment](_.register(user))

  def lookup(id: String): ZIO[ExpEnvironment, Throwable, Option[Experiments]] =
    ZIO.serviceWithZIO[ExpEnvironment](_.lookup(id))

  def users: ZIO[ExpEnvironment, Throwable, List[Experiments]] =
    ZIO.serviceWithZIO[ExpEnvironment](_.users)
}
