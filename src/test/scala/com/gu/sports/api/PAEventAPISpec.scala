package com.gu.sports.api

import com.gu.sports.services.PAEventService
import org.json4s.JsonAST.JValue
import org.json4s.jackson.JsonMethods.parse
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}
import org.scalatra.test.scalatest._

import scala.concurrent.Future

class PAEventAPISpec extends ScalatraFunSuite with BeforeAndAfterEach {
  val jsonString = """{ "json" : "string" }"""
  val invalidJson = "invalidJson"
  val paEventServiceMock = mock(classOf[PAEventService])

  object PAEventAPITest extends PAEventAPI {
    override val paEventService: PAEventService = paEventServiceMock
  }

  addServlet(PAEventAPITest, "/sports-event")

  override def beforeEach() { reset(paEventServiceMock) }

  test("POST /sports-event should return status 201") {
    when(paEventServiceMock.handleEvent(any[JValue])).thenReturn(Future.successful())

    post("/sports-event", jsonString) {
      verify(paEventServiceMock, times(1)).handleEvent(parse(jsonString))
      status should equal (201)
    }
  }

  test("POST /sports-event should return status 201 even if handleEvent fails") {
    when(paEventServiceMock.handleEvent(any[JValue])).thenReturn(Future.failed(new Exception("Bad")))

    post("/sports-event", jsonString) {
      verify(paEventServiceMock, times(1)).handleEvent(parse(jsonString))
      status should equal (201)
    }
  }

  test("POST /sports-event should return 400 for invalid json body") {
    post("/sports-event", invalidJson) {
      status should equal (400)
      verifyNoInteractions(paEventServiceMock)
      response.body should equal ("json format expected")
    }
  }
}
