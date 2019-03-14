package services

import javax.inject._
import domain._

trait Resolve {
  def apply(operation: String, value1: Int, value2: Int): OutData
}