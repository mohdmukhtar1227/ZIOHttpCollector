//package com.zioHttp
//
//import java.util.UUID
//import zio.json._
//
//case class Experiments(experimentId: String,
//                       variantId: String,
//                       accountId: String,
//                       deviceId: String,
//                       date: Int)
//
//object Experiments{
//  implicit val encoder: JsonEncoder[Experiments] = DeriveJsonEncoder.gen[Experiments]
//  implicit val decoder: JsonDecoder[Experiments] = DeriveJsonDecoder.gen[Experiments]
//}
//
