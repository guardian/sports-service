package com.gu.sports.api

import com.gu.sports.services.PAEventService
import org.json4s.JValue
import org.json4s.jackson.JsonMethods.parse
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._
import org.scalatra.test.scalatest._

import scala.concurrent.Future

class PAEventAPISpec extends ScalatraFunSuite {
  val jsonString = """{ "json" : "string" }"""
  val paEventService = mock(classOf[PAEventService])
  addServlet(new PAEventAPI(paEventService), "/sports-event")

  test("POST /sports-event on PAEventAPI should return status 200") {
    when(paEventService.handleEvent(any[JValue])).thenReturn(Future.successful())
    post("/sports-event", jsonString) {
      verify(paEventService, times(1)).handleEvent(parse(jsonString))
      status should equal (200)
    }
  }

}
