package model

class Document (id:String, docCont:String){
  var content = docCont
  var identifier = id
  
  def getContent():String = {
    return content
  }
  
  def getIdentifier():String = {
    return identifier
  }
}