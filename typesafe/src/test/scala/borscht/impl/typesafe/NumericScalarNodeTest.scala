package borscht.impl.typesafe

import borscht.Engine
import borscht.parsers.{given _}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.language.implicitConversions

class NumericScalarNodeTest extends AnyFlatSpec with Matchers {
  private val engine = Engine(provider = TypesafeConfigProvider())
  import engine.{given _}

  "Config" should "provide a byte value" in {
    val cfg = cfg"key: 42"
    cfg.get[Byte]("key") shouldEqual 42.toByte
  }

  it should "provide a byte value from string" in {
    val cfg = cfg"""key: "42""""
    cfg.get[Byte]("key") shouldEqual 42.toByte
  }

  it should "provide a double value" in {
    val cfg = cfg"key: 2.66"
    cfg.get[Double]("key") shouldEqual 2.66
  }

  it should "provide a double value from string" in {
    val cfg = cfg"""key: "2.66""""
    cfg.get[Double]("key") shouldEqual 2.66
  }

  it should "provide a float value" in {
    val cfg = cfg"key: 2.66"
    cfg.get[Float]("key") shouldEqual 2.66f
  }

  it should "provide a float value from string" in {
    val cfg = cfg"""key: "2.66""""
    cfg.get[Float]("key") shouldEqual 2.66f
  }

  it should "provide an integer value" in {
    val cfg = cfg"key: ${Int.MaxValue}"
    cfg.get[Int]("key") shouldEqual Int.MaxValue
  }

  it should "provide an integer value from string" in {
    val cfg = cfg"""key: "1""""
    cfg.get[Int]("key") shouldEqual 1
  }

  it should "provide a long value" in {
    val cfg = cfg"key: ${Long.MaxValue}"
    cfg.get[Long]("key") shouldEqual Long.MaxValue
  }

  it should "provide a long value from string" in {
    val cfg = cfg"""key: "${Long.MinValue}""""
    cfg.get[Long]("key") shouldEqual Long.MinValue
  }

  it should "provide a short value" in {
    val cfg = cfg"key: 42"
    cfg.get[Short]("key") shouldEqual 42.toShort
  }

  it should "provide a short value from string" in {
    val cfg = cfg"""key: "42""""
    cfg.get[Short]("key") shouldEqual 42.toShort
  }
}
