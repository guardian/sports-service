package com.gu.sports.api

import com.gu.sports.services.PAEventService
import org.json4s.JsonAST.JValue
import org.json4s.jackson.JsonMethods.parse
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._
import org.scalatra.test.scalatest._

import scala.concurrent.Future

class PAEventAPISpec extends ScalatraFunSuite {
  val jsonString = """{ "json" : "string" }"""
  val invalidJson = "invalidJson"
  val paEventService = mock(classOf[PAEventService])
  addServlet(new PAEventAPI(paEventService), "/sports-event")
  when(paEventService.handleEvent(any[JValue])).thenReturn(Future.successful())

  test("POST /sports-event should return status 200") {
    post("/sports-event", jsonString) {
      verify(paEventService, times(1)).handleEvent(parse(jsonString))
      status should equal (200)
    }
  }

  test("POST /sports-event should return 400 for invalid json body") {
    post("/sports-event", invalidJson) {
      status should equal (400)
      response.body should equal ("json format expected")
    }
  }
}
