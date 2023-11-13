/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbpracica1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.apache.solr.client.solrj.SolrClient;
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

       
       HttpSolrClient solr = new HttpSolrClient.Builder("http://localhost:8983/solr/motores4").build();
       SolrQuery query = new SolrQuery();
       
       String ruta = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\CISI.QRY";
       File filename = new File(ruta);
       Scanner sc = new Scanner(filename);
       int cuenta = 0;
       StringBuilder palabras = new StringBuilder();
       
       
       while(sc.hasNext() && cuenta < 5)
       {
           
           String pal = sc.next();
           if(!pal.matches("[0-9].*|\\..*"))
           {
            palabras.append(pal). append(" ");
            System.out.println(pal + " ");
            cuenta ++;
           }
       }
       
       
       
       sc.close();
       
       String consulta = palabras.toString().trim();
       query.setQuery("*");
       query.addFilterQuery("texto: " + consulta);
       QueryResponse rsp = solr.query(query);
       SolrDocumentList docs = rsp.getResults();
       for (int i = 0; i<docs.size(); i++)
       {
           System.out.println(docs.get(i));
       }

        
        
        
        
    }
}
