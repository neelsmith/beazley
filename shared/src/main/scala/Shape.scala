package edu.holycross.shot.beazley

sealed abstract class Shape (
  label: String
)

case object Krater extends Shape("krater")
