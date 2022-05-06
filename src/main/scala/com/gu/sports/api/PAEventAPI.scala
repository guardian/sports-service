package com.gu.sports.api

import com.gu.sports.services.PAEventService
import org.scalatra.{Ok, BadRequest}

class PAEventAPI(
  val paEventService: PAEventService
) extends BaseAPI {

  // I'm guessing PA's webhook is expecting a POST endpoint
  post("/") {
    parseOpt(request.body) match {
      case Some(body) =>
        paEventService.handleEvent(body)
        Ok()
      case _ => BadRequest("json format expected")
    }
  }
}
