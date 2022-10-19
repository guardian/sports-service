package com.gu.sports.api

import com.gu.sports.services.PAEventService
import org.scalatra.{AsyncResult, BadRequest, Created, FutureSupport}

import java.util.logging.Logger
import scala.concurrent.{ExecutionContext, Future}

trait PAEventAPI extends BaseAPI with FutureSupport {

  protected implicit def executor: ExecutionContext = ExecutionContext.global

  val paEventService: PAEventService

  // I'm guessing PA's webhook is expecting a POST endpoint
  post("/") {
    new AsyncResult {
      override val is =
        parseOpt(request.body) match {
          case Some(event) =>
            println(s"Handling event: $event")
            paEventService.handleEvent(event).map(
              _ => Created(())
            )
          case badThing =>
            println(badThing, "bad request")
            Future.successful(BadRequest("""{"error":"json format expected"}"""))
        }
    }
  }
}

object PAEventAPI extends PAEventAPI {
  override val paEventService: PAEventService = PAEventService
}
