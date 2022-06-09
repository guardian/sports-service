package com.gu.sports.services

import org.json4s.jackson.JsonMethods._
import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source

class PAEventServiceSpec extends AnyFlatSpec {
  "handleEvent" should "process and persist the sport data for a valid event" in {
    val testEvent = parse(Source.fromFile("validEvent.json").getLines.mkString)
    TestPAEventService.handleEvent(testEvent)
  }
}

object TestPAEventService extends PAEventService {

}