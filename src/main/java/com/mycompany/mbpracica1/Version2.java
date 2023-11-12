/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbpracica1;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.solr.client.solrj.SolrQuery;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 *
 * @author luism
 */
public class Version2 {
    
    public static void main(String[] args) throws IOException,
			SolrServerException {

       final SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/miconsulta").build();
		
        SolrQuery query = new SolrQuery();
        
        //APERTURA ARCHIVO
        String ruta = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\CISI.QRY";
        File archivo = new File(ruta);
        Scanner sc = new Scanner(archivo);
        
        
        
        //LEEMOS
        StringBuilder palabras = new StringBuilder();
        int cuenta = 0;
        while(sc.hasNext() && cuenta < 5)
        {
            String pal = sc.next();
            palabras.append(pal).append("");
            System.out.println(pal + " ");
            cuenta++;
        }
        sc.close();
        
        String consulta = palabras.toString().trim();
        query.setQuery(consulta);
        QueryResponse rsp = solr.query(query);
        SolrDocumentList docs = rsp.getResults();
	for (int i = 0; i < docs.size(); ++i) {
            System.out.println(docs.get(i));
        }
    
    }
}
