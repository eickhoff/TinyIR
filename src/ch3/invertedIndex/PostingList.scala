package ch3.invertedIndex

import model.Document
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

class PostingList(documents:Array[Document]) {

	var postings = new HashMap[String,ListBuffer[String]]
	initialize(documents)

	/**
	 * Converts a text file into a posting list.
	 */
	def initialize(documents:Array[Document]) {
		//TODO: use tokenizer from earlier chapter
		for (document <- documents){
			var tokens = document.getContent.split(' ')
			for (token <- tokens){
				if (!postings.contains(token.toLowerCase())){
					postings.put(token.toLowerCase(), ListBuffer (document.getIdentifier))
				}else{
					if (!postings.get(token.toLowerCase()).contains(document.getIdentifier)){
						var ids = postings.get(token.toLowerCase()).get
						ids += document.getIdentifier
					    postings.put(token.toLowerCase(),ids)
					}
				}
			}
		}
	}
	
	/**
	 * Outputs this posting list in a human-readable way.
	 */
	def printPostings() {
		for (term <- postings.keySet){
			var line = term+":\t"
			for (doc <- postings.get(term).get){
				line += doc+", "
			}
			println(line)
		}
	}

}

object Test {
	def main(args: Array[String]){
	    var doc1 = new Document("d1","This is a document")
	    var doc2 = new Document("d2","This is a flower pot")
	    var docs = Array(doc1,doc2)
		var pl = new PostingList(docs)
	    pl.printPostings()
	}
}