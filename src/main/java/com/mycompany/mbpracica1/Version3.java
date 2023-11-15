/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbpracica1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import java.lang.StringBuilder;
import org.apache.solr.common.SolrDocument;

/**
 *
 * @author luism
 */
public class Version3 {
    
    public static int nConsultaParaCisi = 1;
   
    public static void main(String[] args) throws IOException,
			SolrServerException {
       
       int nConsulta = 1;

       
       
       
       HttpSolrClient solr = new HttpSolrClient.Builder("http://localhost:8983/solr/motores5").build();
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
       query.setFields("autor", "titulo", "score", "idp");
       query.addFilterQuery("texto: " + consulta);
       QueryResponse rsp = solr.query(query);
       SolrDocumentList docs = rsp.getResults();
       
       
       //for (int i = 0; i<docs.size(); i++)
       //{
           //System.out.println(docs.get(i));
       //}
       converTrec(docs,nConsulta);
       nConsulta++;
    }
    
    private static void converTrec(SolrDocumentList docs, int nConsulta) throws IOException
    {
        int nranking = 0;
        String ruta = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\miTrec.TREC";
        File filename = new File(ruta);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(filename));
        for (SolrDocument doc: docs)
        {
            Object titulo = doc.getFieldValue("titulo");
            Object autor = doc.getFieldValue("autor");
            Object score  = doc.getFieldValue("score");
            Object idp = doc.getFieldValue("idp");
            
            System.out.println("Título: " + titulo);
            System.out.println("Autor: " + autor);
            System.out.println("Score: " + score);
            System.out.println("IDP: " + idp);
            
            String tituloSTR = titulo.toString();
            String autorSTR = autor.toString();
            String scoreSTR = score.toString();
            String idpSTR = idp.toString();
            String patron = "[^a-zA-Z0-9\\s]"; 
            String idpSTR1 = idpSTR.replaceAll(patron, "");
            
            escritor.write(nConsulta + " " + "Q0" + " " + idpSTR1 + " " + nranking + " " + scoreSTR + " " + "Luismi" + " " + "\n" );
            nranking++;
        }
        escritor.close();
        
        adecuaCisi();
        
    }
    
    private static void adecuaCisi() throws IOException
    {
        String ruta = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\CISI.REL";
        String ruta2 = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\CISI2.TREC";
        File filename = new File(ruta);
        File filename2 = new File(ruta2);
        Scanner scan = new Scanner(filename);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(filename2));
       
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
           
            //Para declarar que los valores se dividen en tabs
            String [] valores = line.split("\\s+");
           
            String cero = "0";
            String consulta = valores[1];
            String documento = valores[2];
            String score = valores[3];
            float scoreFl = Float.parseFloat(score);
            int scoreInt = (int) scoreFl;
            String scoreSTR = String.valueOf(scoreInt);
            
            System.out.println(consulta);
            System.out.println(documento);
            System.out.println(scoreSTR);
           
           
            escritor.write(consulta + " " + cero + " " + documento + " " + scoreSTR + "\n");
            
           
        }
        
        escritor.close();
        scan.close();
    }
    
    

}


