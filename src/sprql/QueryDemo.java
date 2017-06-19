package sprql;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryException;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

public class QueryDemo {
	public static void main(String[] args){
		String queryStr = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> \n"
				+"PREFIX dbo: <http://dbpedia.org/ontology/> \n"
				+"SELECT DISTINCT ?persona ?nom ?naix \n"
				+"WHERE { \n"
				+"?persona dbo:birthPlace <http://dbpedia.org/resource/London> . \n"
				+"?persona dbo:occupation <http://dbpedia.org/resource/Actor> . \n"
				+"?persona foaf:name ?nom . \n"
				+"?persona dbo:birthDate ?naix \n"
				+"FILTER NOT EXISTS { ?persona dbo:deathDate ?death. } \n"
				+"}";
		
		Query query = QueryFactory.create(queryStr);
		QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		try{
			ResultSet results = exec.execSelect();
			while(results.hasNext()){
				QuerySolution sol = results.nextSolution();
				System.out.println(sol);
			}
		}finally{
			exec.close();
		}				 
	}
}
