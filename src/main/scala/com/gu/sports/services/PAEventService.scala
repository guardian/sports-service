package com.gu.sports.services

import com.gu.sports.model.PANotification
import org.json4s.DefaultFormats
import org.json4s.JsonAST.JValue
import org.json4s.jackson.JsonMethods._
import scala.io.Source.fromURL

import scala.concurrent.Future

trait PAEventService {
  implicit val formats = DefaultFormats

  def handleEvent(event: JValue): Future[Unit] = {
    // parse
    val paNotification = parseEvent(event)

    // call url
    // what happens if timeout - maybe wrap in a future
    val fullEvent = fromURL(paNotification.url).mkString

    // receive full event data - what do we get?
    // parse/translate event data (make event storable)
    // pass to database layer/s3
    Future.successful()
  }

  private def parseEvent(event: JValue): PANotification = {
    event.extract[PANotification]
  }
}

object PAEventService extends PAEventService {

}
