package com.gu.sports.services

import com.gu.sports.model.PANotification
import org.json4s.DefaultFormats
import org.json4s.JsonAST.JValue
import org.json4s.jackson.JsonMethods._

import scala.io.Source.fromURL
import dispatch._
import Defaults._
import com.gu.sports.dataaccess.{PAClient, S3Client}
import com.gu.sports.util.Configuration

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

trait PAEventService {
  implicit val formats = DefaultFormats

  val paClient: PAClient
  val s3Client: S3Client

  // define some remote configuration to get the apikey?
//  val configuration: Configuration

  def handleEvent(event: JValue): Future[Unit] = {
     for {
       paNotification <- parseEvent(event)
       response <- paClient.get(paNotification.url, Configuration.apiKey)
       responseBody = response.getResponseBody()
       path = getPath(paNotification)
       _ <- s3Client.put(responseBody, path)
     } yield {
       // something/nothing
     }
    // parse - do we care about parsing the response in this component? part 2

    Future.successful()
  }

  private def parseEvent(event: JValue): Future[PANotification] = {
    Try {
      event.extract[PANotification]
    } match {
      case Success(paNotif) => Future.successful(paNotif)
      case Failure(e) => Future.failed(e)
    }
  }

  private def getPath(notif: PANotification): String =
    s"${notif.`type`}/${notif.event.id}-${notif.timestamp}.json"
}

object PAEventService extends PAEventService {
  val paClient = PAClient
  val s3Client = S3Client
}
