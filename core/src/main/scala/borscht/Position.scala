package borscht

import scala.annotation.targetName

trait Position:
  @targetName("merge")
  final def +(that: Position): Position = Position.Merged(this, that)
  
  override def toString: String

object Position:
  private val Separator = " | "

  private def append(sb: StringBuilder, position: Position): StringBuilder = position match
    case Merged(p1, p2) => append(append(sb, p1) ++= Separator, p2)
    case p => sb ++= p.toString

  private case class Merged(p1: Position, p2: Position) extends Position:
    override def toString: String =
      (append(append(StringBuilder() ++= getClass.getName += '(', p1) ++= Separator, p2) += ')').result
