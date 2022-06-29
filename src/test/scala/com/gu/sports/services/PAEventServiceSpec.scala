package com.gu.sports.services

import com.gu.sports.dataaccess.{PAClient, S3Client}
import dispatch.Res
import org.json4s.jackson.JsonMethods._
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{mock, verify, when}
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.{Await, Future}
import scala.io.Source
import scala.concurrent.duration._

class PAEventServiceSpec extends AnyFlatSpec {

  val mockPAClient = mock(classOf[PAClient])
  val mockS3Client = mock(classOf[S3Client])

  object TestPAEventService extends PAEventService {
    val paClient: PAClient = mockPAClient
    val s3Client: S3Client = mockS3Client
  }
  "handleEvent" should "process and persist the sport data for a valid event" in {
    val testEvent = parse(Source.fromResource("validNotification.json").getLines.mkString)
    val mockResponse = mock(classOf[Res])

    // stub/mock the pa event api?
    when(mockPAClient.get(any[String], any[String])).thenReturn(Future.successful(mockResponse))
    when(mockResponse.getResponseBody).thenReturn("test body")
    when(mockResponse.getStatusCode).thenReturn(200)

    Await.result(TestPAEventService.handleEvent(testEvent), 5.seconds)

    verify(mockPAClient).get("https://e5ddbf81-9bb2-4a8c-ac98-8d47bf007acd.mock.pstmn.io/v1/event/12345", "1234")

    // also verify that we call s3 client
  }
}

