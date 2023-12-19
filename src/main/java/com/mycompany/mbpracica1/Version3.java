/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbpracica1;

import static com.mycompany.mbpracica1.Version2.lanzaConsulta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Source;
import org.apache.solr.common.SolrDocument;

/**
 *
 * @author luism
 */
public class Version3 {
    static int nRanking = 1;
    
    public static int nConsultaParaCisi = 1;
    static int nConsulta = 1;
   
    public static void main(String[] args) throws IOException,
			SolrServerException {
       
       

       String ruta = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\CISI.QRY";
       File filename = new File(ruta);
       List<String> contentList = new ArrayList<>(); // Lista para almacenar el contenido de .W
       StringBuilder currentContent = new StringBuilder(); // StringBuilder para almacenar el contenido actual

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Elimina espacios en blanco al principio y final de la línea
                if (line.startsWith(".W")) {
                    if (currentContent.length() > 0) { // Si hay contenido almacenado, añádelo a la lista
                        contentList.add(currentContent.toString());
                        currentContent.setLength(0); // Reinicia el StringBuilder
                    }
                } else {
                    currentContent.append(line); // Agrega el contenido al StringBuilder
                }
            }
            // Añade el último contenido después del último ".W" a la lista si hay contenido
            if (currentContent.length() > 0) {
                contentList.add(currentContent.toString());
            }

            // Imprime el contenido extraído
            for (String content: contentList) {
                //System.out.println(content);
                lanzaConsulta1(content);
            }
            
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            
            
       
       
       
       
       
       
    }   
    
    private static void lanzaConsulta(String consulta) throws SolrServerException, IOException   
    {
        System.out.println(consulta + "\n");
    }
    
    
    private static void lanzaConsulta1(String consulta) throws SolrServerException, IOException   
    {
       HttpSolrClient solr = new HttpSolrClient.Builder("http://localhost:8983/solr/gate").build();
       SolrQuery query = new SolrQuery();
       String palabras = consulta.replaceAll("[^a-zA-Z0-9 ]", "");
       
       System.out.println(palabras); 
       query.setQuery("*");
       query.setFields("*");
       query.addFilterQuery("texto: " + palabras);
       QueryResponse rsp = solr.query(query);
       SolrDocumentList docs = rsp.getResults();
       //converTrec(docs,nConsulta);
       //System.out.println(nConsulta);
       nConsulta++;
       
       
       
    }
    
    private static String eliminarPrimeraPalabra(String input) {
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
        
        adecuaCisi();
        
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
    
}

