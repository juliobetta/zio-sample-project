package com.bettasoftware.domain

import zio.json._


object HelloResponse {
  implicit val encoder: JsonEncoder[HelloResponse] = DeriveJsonEncoder.gen[HelloResponse]
}

case class HelloResponse (message: String)
