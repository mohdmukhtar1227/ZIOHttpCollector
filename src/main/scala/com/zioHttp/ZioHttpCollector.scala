package com.zioHttp


import zhttp.http._
import zio._
import zhttp.http.{Http, Method, Request, Response, Status}
import zhttp.service.Server
import zio.json._
import zio.kafka._
import zio.kafka.serde.Serde
import zio.schema._



case class Experiments(experimentId: String,
                        variantId: String,
                        accountId: String,
                        deviceId: String,
                        date: Int
                      )

//case class RootInterface (
//                           events: Seq[Experiments]
//                         )


//case class Events ( Seq)
object Experiments {
  implicit val encoder: JsonEncoder[Experiments] = DeriveJsonEncoder.gen[Experiments]
  implicit val decoder: JsonDecoder[Experiments] = DeriveJsonDecoder.gen[Experiments]
  implicit val codec: JsonCodec[Experiments] = DeriveJsonCodec.gen[Experiments]
  implicit val schema: Schema[Experiments]   = DeriveSchema.gen

}


object HttpService {
  def apply(): Http[ExpEnvironment, Throwable, Request, Response] =
    Http.collectZIO[Request] {

      case req@(Method.POST -> !! / "zioCollector") =>
        val c = req.body.asString.map(_.fromJson[Experiments])
        for {
          u <- req.body.asString.map(_.fromJson[Experiments])
          r <- u match {
            case Left(e) =>
              ZIO.debug(s"Failed to parse the input: $e").as(
                Response.text(e).setStatus(Status.BadRequest)
              )
            case Right(u) =>
              println(s"$u +       =====")
              ExpEnvironment.register(u)
                .map(id => Response.text(id))
          }
        }

        yield r
    }

}




//  val experimentsSerde: Serde[Any, Experiments] = Serde.string.inmapM { string =>
//    //desericalization
//    ZIO.fromEither(string.fromJson[Experiments].left.map(errorMessage => new RuntimeException(errorMessage)))
//  } { theMatch =>
//    ZIO.effect(theMatch.toJson)
//
//  }


object ZioHttpCollector extends ZIOAppDefault {
  def run: ZIO[Environment with ZIOAppArgs with Scope, Any, Any] = {
    Server.start(
      port = 9003,
      http = HttpService()).provide(ZLayerExp.layer)

  }



}

