package com.gu.sports.api

import com.gu.sports.services.PAEventService
import org.scalatra.{BadRequest, Ok}

import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global

trait PAEventAPI extends BaseAPI {

  val paEventService: PAEventService

  // I'm guessing PA's webhook is expecting a POST endpoint
  post("/") {
    println(request.body)
    parseOpt(request.body) match {
      case Some(event) =>
        log(s"Handling event: $event")
        paEventService.handleEvent(event).map(
          result => Ok(result)
        )

      case badThing =>
        println(badThing)
        BadRequest("json format expected")
    }
  }
}

object PAEventAPI extends PAEventAPI {
  override val paEventService: PAEventService = PAEventService
}
