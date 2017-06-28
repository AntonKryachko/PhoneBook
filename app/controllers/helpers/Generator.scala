package controllers.helpers

/**
  * Created by anton on 26.06.17.
  */
trait Generator
{
  def generatorId = java.util.UUID.randomUUID.toString.replace("-","")
}
