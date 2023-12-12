/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbpracica1;


import static com.mycompany.mbpracica1.Version3.nConsulta;
import static com.mycompany.mbpracica1.Version3.nRanking;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
public class Buscador1 {
    public static SolrDocumentList docs;
    public static String consultaPrueba = " ";
    
        
    public static void comienzo(String consultaPrueba) throws SolrServerException, IOException{ 
        
        if(!consultaPrueba.equals(" "))
        {
            System.out.println(consultaPrueba);
            divisor(consultaPrueba);
        }
        

    
    
    }
    
    private static void lanzaConsulta(String campo, String texto)
    {
        System.out.println("El campo es " + " " + campo);
        System.out.println("El campo es " + " " + texto);
    }
    
    
    private static void lanzaConsulta1(String campo, String texto) throws SolrServerException, IOException   
    {
       HttpSolrClient solr = new HttpSolrClient.Builder("http://localhost:8983/solr/motores7").build();
       SolrQuery query = new SolrQuery();
      
       System.out.println("El campo es " + " " + campo);
       System.out.println("El texto es " + " " + texto); 
       query.setQuery("*");
       query.setFields(campo);
       query.addFilterQuery("texto: " + texto);
       QueryResponse rsp = solr.query(query);
       docs = rsp.getResults();
       for (int i = 0; i<docs.size(); i++)
       {
          System.out.println(docs.get(i));
       }
       
       //converTrec(docs,nConsulta);
       //System.out.println(nConsulta);
       nConsulta++;
       
       
       
    }
    
    private static void converTrec(SolrDocumentList docs, int nConsulta) throws IOException
    {
        
        String ruta = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\miTrec.TREC";
        File filename = new File(ruta);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(filename,true));
        for (SolrDocument doc: docs)
        {
            Object titulo = doc.getFieldValue("titulo");
            Object autor = doc.getFieldValue("autor");
            Object score  = doc.getFieldValue("score");
            Object idp = doc.getFieldValue("idp");
            
            String tituloSTR = titulo.toString();
            String autorSTR = autor.toString();
            String scoreSTR = score.toString();
            String idpSTR = idp.toString();
            String patron = "[^a-zA-Z0-9\\s]"; 
            String idpSTR1 = idpSTR.replaceAll(patron, "");
            
            escritor.write(nConsulta + " " + "Q0" + " " + idpSTR1 + " " + nRanking + " " + scoreSTR + " " + "Luismi" + " " + "\n" );
            nRanking++;
        }
        escritor.close();
        
        //adecuaCisi();
        
    }
    
    private static void adecuaCisi() throws IOException
    {
        String ruta = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\CISI.REL";
        String ruta2 = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\CISI2.TREC";
        File filename = new File(ruta);
        File filename2 = new File(ruta2);
        Scanner scan = new Scanner(filename);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(filename2,true));
       
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
            
            //System.out.println(consulta);
            //System.out.println(documento);
            //System.out.println(scoreSTR);
           
           
            escritor.write(consulta + " " + cero + " " + cero + " " + documento + "\n");
            
           
        }
        
        escritor.close();
        scan.close();
    }
    
    private static void divisor(String cadena) throws SolrServerException, IOException {
        System.out.println("Empieza divisor");
        String[] partes = divideString(cadena);
        int i = 0;
        String campo = " ";
        String texto = " ";
        String cadenavacia = " ";
        for (String cadena1 : partes) {
            if (i % 2 == 0) {
                campo = cadena1;
                i++;
            } else {
                texto = cadena1;
                i++;
            }
            if (!campo.equals(cadenavacia) && !texto.equals(cadenavacia)) {
                lanzaConsulta1(campo, texto);
            }
        }
    }
   
   private static String[] divideString(String cadena)
   {
       System.out.println("Empieza divideString");
        String[] partes = cadena.split(":");
        return partes;
   }
   
   public static SolrDocumentList devuelveList()
   {
       return docs;
       
   }
    
    
}    
