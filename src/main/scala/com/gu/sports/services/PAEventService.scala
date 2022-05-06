package com.gu.sports.services

import org.json4s.JsonAST.JValue
import scala.concurrent.Future

class PAEventService {

  def handleEvent(body: JValue): Future[Unit] = {

    Future.successful()
  }
}
