package sprql;


import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

public class asdda {

	public static void main(String[] args) {
		sparqlTest();

	}
public static void sparqlTest(){
	
	String str = "Twisted_Sister";
	
	String queryString= "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
			+ "PREFIX dbo:<http://dbpedia.org/ontology/>\n"
			+ "PREFIX dbr:<http://dbpedia.org/resource/>\n"
			+ "SELECT ?miembro\n"
			+ "WHERE {"
			+ "dbr:"+str+" "
			+ "dbo:bandMember ?miembro."
			+ "}";
	Query query = QueryFactory.create(queryString);
	QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql",query);
	try{
		ResultSet results = (ResultSet) qexec.execSelect();
		System.out.println("Twisted Sisters:");
		while (((org.apache.jena.query.ResultSet) results).hasNext()){
			QuerySolution soln = ((org.apache.jena.query.ResultSet) results).nextSolution();
			System.out.println(soln);
		}
	}finally{
			qexec.close();
		}		
	}	
}