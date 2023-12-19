/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbpracica1;

import java.io.File;
import java.util.Scanner;
import java.io.*;
import static java.lang.Thread.sleep;
import java.util.*;
import javax.sound.midi.Soundbank;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

/**
 *
 * @author luism
 */
public class Version1 {

    public static void main(String[] args) throws IOException, SolrServerException, InterruptedException {
        String filename = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\CISI.XML.xml";
        Scanner scan = new Scanner(new File(filename));

        int id = 1;
        String autor = " ";
        String titulo = " ";
        String texto = " ";
        

        String [] personas = new String[10000];
        String [] organizaciones = new String[10000];
        String [] lugares = new String[10000];
        
        int personCount = 0;
        int organizationCount = 0;
        int locationCount = 0;

        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if (line.startsWith(".I")) {
                autor = " ";
                titulo = " ";
                texto = " ";
                
                
                
            } else if (line.startsWith(".T")) {
                titulo = scan.nextLine().trim();
                if (line.contains("<Person>")) {
                        String[] parts = line.split("<Person>");
                        if (parts.length > 1) {
                            String word = parts[1].split("\\s+")[0];
                            String word1 = extractNextWord(word);
                            personas[personCount] = word1;
                            personCount++;
                        }
                    }
                    if (line.contains("<Organization>")) {
                        String[] parts = line.split("<Organization>");
                        if (parts.length > 1) {
                            String word = parts[1].split("\\s+")[0];;
                            String word1 = extractNextWord(word);
                            organizaciones[organizationCount] = word1;
                            organizationCount++;
                        }

                    }
                    if (line.contains("<Location>")) {
                        String[] parts = line.split("<Location>");
                        if (parts.length > 1) {
                            String word = parts[1].split("\\s+")[0];
                            String word1 = extractNextWord(word);
                            lugares[locationCount] = word1;
                            locationCount++;
                        }

                    }
            } else if (line.startsWith(".A")) {
                autor = scan.nextLine().trim();
                if (line.contains("<Person>")) {
                        String[] parts = line.split("<Person>");
                        if (parts.length > 1) {
                            String word = parts[1].split("\\s+")[0];
                            String word1 = extractNextWord(word);
                            personas[personCount] = word1;
                            personCount++;
                        }
                    }
                    if (line.contains("<Organization>")) {
                        String[] parts = line.split("<Organization>");
                        if (parts.length > 1) {
                            String word = parts[1].split("\\s+")[0];;
                            String word1 = extractNextWord(word);
                            organizaciones[organizationCount] = word1;
                            organizationCount++;
                        }

                    }
                    if (line.contains("<Location>")) {
                        String[] parts = line.split("<Location>");
                        if (parts.length > 1) {
                            String word = parts[1].split("\\s+")[0];
                            String word1 = extractNextWord(word);
                            lugares[locationCount] = word1;
                            locationCount++;
                        }

                    }
            } else if (line.startsWith(".W")) {
                StringBuilder stb = new StringBuilder();

                while (scan.hasNextLine()) {
                    line = scan.nextLine();
                    if (line.startsWith(".I") || line.startsWith(".A") || line.startsWith(".T")) {
                        break;
                    }

                    // Buscar etiquetas y guardar la palabra siguiente
                    if (line.contains("<Person>")) {
                        String[] parts = line.split("<Person>");
                        if (parts.length > 1) {
                            String word = parts[1].split("\\s+")[0];
                            String word1 = extractNextWord(word);
                            personas[personCount] = word1;
                            personCount++;
                        }
                    }
                    if (line.contains("<Organization>")) {
                        String[] parts = line.split("<Organization>");
                        if (parts.length > 1) {
                            String word = parts[1].split("\\s+")[0];;
                            String word1 = extractNextWord(word);
                            organizaciones[organizationCount] = word1;
                            organizationCount++;
                        }

                    }
                    if (line.contains("<Location>")) {
                        String[] parts = line.split("<Location>");
                        if (parts.length > 1) {
                            String word = parts[1].split("\\s+")[0];
                            String word1 = extractNextWord(word);
                            lugares[locationCount] = word1;
                            locationCount++;
                        }

                    }

                    stb.append(line).append("\n");
                }
                texto = stb.toString().trim();
                //System.out.println("El autor es " + " " + autor );
                //System.out.println("El titulo es " + " " + titulo );
                //System.out.println("El texto es " + " " + texto );
                //System.out.println("La persona es " + " " + nextPerson);
                //System.out.println("La organizacion es " + " " + nextOrganization );
                //System.out.println("El lugar es " + " " + nextLocation );
                //for (int i = 0; i < personCount; i++) {

                    //System.out.println("Documento" + id + " La persona" + " " + i + " es"  + " " + personas[i]);
                    
                //}
                //System.out.println("Cantidad de personas en documento" + id + " : " + personCount);

                //for (int i = 0; i < organizationCount; i++) {
                    //System.out.println("Documento" + id + " El organizador" + " " + i + " es"  + " " + organizaciones[i]);
                //}
                //System.out.println("Cantidad de organizaciones en documento" + id + " : " + organizationCount);
                
                //for (int i = 0; i < locationCount; i++) {
                    //System.out.println("Documento" + id + " El lugar" + " " + i + " es"  + " " + lugares[i]);
                //}
                //System.out.println("Cantidad de lugares en documento" + id + " : " + locationCount);
                introducedoc(titulo, autor, texto, Integer.toString(id), personas, organizaciones, lugares, personCount, organizationCount, locationCount);
                vaciaString(personas);
                vaciaString(organizaciones);
                vaciaString(lugares);
                personCount = 0;
                organizationCount = 0;
                locationCount = 0;
                
               
                
                id++;
            }
        }

    }
    
    
    private static void introducedoc(String titulo, String autor, String texto, String id, String [] personas, 
            String [] organizaciones, String [] lugares, int personCount, int OrganizationCount, int LocationCount) throws SolrServerException, IOException
    {
        
        
        if(titulo != " " && autor != " " && texto != " "){
            //System.out.println("El titulo es " + " "+ titulo);
            //System.out.println("El autor es " + " "+ autor);
            //System.out.println("El texto es " + " "+ texto);
            //System.out.println("El id" + " " + id);
            
            
            
            SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/gate").build();
            SolrInputDocument doc = new SolrInputDocument();
        
            doc.addField("titulo", titulo);
            doc.addField("autor", autor);
            doc.addField("texto", texto);
            doc.addField("idp", id);
            
            //Tratamiento Strings
            String [] personas1 = creaString(personas, personCount);
            String [] organizaciones1 = creaString(organizaciones, OrganizationCount);
            String [] lugares1 = creaString(lugares, LocationCount);
            
            if(id.equals("3")){
                visualizaString(personas1);
            }
            
            
            
            if(personas1.length > 0){
                doc.addField("Persona", personas1);
                //System.out.println(persona);
            }
            if(organizaciones1.length > 0){
                doc.addField("Organizacion", organizaciones1);
                //System.out.println(organizacion);
            }
            if(lugares1.length > 0){
                doc.addField("Lugar", lugares1);
                //System.out.println(lugar);
            }
            
            visualizaDoc(doc);
            
            
            
            client.add(doc);
            client.commit();
        }
    }
    
    private static void vaciaString(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = null; // Llenamos el arreglo con datos para demostración
        }
    }
    
    private static String extractNextWord(String line) {
        String[] words = line.split("</");
        String nueva = words[0];
        return nueva;
        
    }
    
    private static void visualizaString(String [] lugares)
    {
        for(int i = 0; i<lugares.length; i++)
        {
            System.out.println(lugares[i]);
        }
    }
    
    private static void visualizaDoc(SolrInputDocument solrDoc)
    {
        int i = 1;
        for (String fieldName : solrDoc.getFieldNames()) {
            
            System.out.println(fieldName + ": " + solrDoc.getFieldValues(fieldName));
            
        }
    }
    
    private static String [] creaString(String [] cadenas, int tamaño)
            
    {
        
        String [] nuevoString = new String[tamaño];
        for(int i = 0; i<tamaño; i++){
            nuevoString[i] = cadenas[i];
        }
        return nuevoString;
    }
    
    
     
    
    
   
    
    
    
}
