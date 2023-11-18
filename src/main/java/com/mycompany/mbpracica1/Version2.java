/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbpracica1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        
       
        
        
        
        
        
        
        //StringBuilder palabras = new StringBuilder();
        String ruta = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\CISI.QRY";
        File filename = new File(ruta);
        Scanner sc = new Scanner(filename);
        StringBuilder palabras = new StringBuilder();
        int cuenta = 0;
        
        while(sc.hasNext())
        {
           
           
           String linea = sc.nextLine();
           if(linea.startsWith(".I"))
           {
               palabras.setLength(0);
               cuenta = 0;
               
                while(cuenta <5)
                {
                String pal = sc.next();
                palabras.append(pal). append(" ");
                cuenta++;
                
                if(cuenta  ==5)
                {
                String consulta = palabras.toString().trim();
                String consulta1 = " ";
                consulta1 = eliminarPrimeraPalabra(consulta);
                System.out.println(consulta1);
                lanzaConsulta(consulta1);
                }
               }
            }
        }
    }

    public static void lanzaConsulta(String palabras) throws SolrServerException, IOException
    {
        
       HttpSolrClient solr = new HttpSolrClient.Builder("http://localhost:8983/solr/motores5").build();
       SolrQuery query = new SolrQuery();
       String palabras1 = palabras.replaceAll("[^a-zA-Z0-9 ]", "");
       System.out.println(palabras1);
       query.setQuery("*");
       query.setFields("autor", "titulo", "score", "idp");
       query.addFilterQuery("texto: " + palabras1);
       QueryResponse rsp = solr.query(query);
       SolrDocumentList docs = rsp.getResults();
       for (int i = 0; i<docs.size(); i++)
       {
           System.out.println(docs.get(i));
       }
    
    }
    
    private static  String eliminarPrimeraPalabra(String input) {
    if (input == null || input.isEmpty()) {
        return ""; // Devuelve una cadena vacía si el input es nulo o está vacío
    }

    int indiceEspacio = input.indexOf(' ');
    if (indiceEspacio != -1) { // Si se encuentra al menos un espacio en el string
        return input.substring(indiceEspacio + 1).trim(); // Devuelve el string después del primer espacio
    } else {
        return ""; // Si no se encuentra ningún espacio, devuelve una cadena vacía
    }
}



}
