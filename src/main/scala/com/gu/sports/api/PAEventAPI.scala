package com.gu.sports.api

import com.gu.sports.services.PAEventService
import org.scalatra.Ok

class PAEventAPI(
  val paEventService: PAEventService
) extends BaseAPI {

  // I'm guessing PA's webhook is expecting a POST endpoint
  post("/") {
    paEventService.handleEvent(request.body)
    println("POST", request.body)
    Ok()
  }
}
