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
    public static void main (String [] args) throws IOException, SolrServerException, InterruptedException{
        String filename = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\PRACTICAMOTORES\\CISI.ALL";
        Scanner scan = new Scanner(new File(filename));
        
        
        String autor = " ";
        String titulo = " ";
        String texto = " ";
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            
            if (line.startsWith(".I"))
            {
                 autor = " ";
                 titulo = " ";
                 texto = " ";
                
            }else if(line.startsWith(".T")){
                titulo = scan.nextLine().trim();
            
            }else if(line.startsWith(".A")){
                autor = scan.nextLine().trim();
            
            }else if(line.startsWith(".W")){
                StringBuilder stb= new StringBuilder();
                
                while(scan.hasNextLine()){
                    line = scan.nextLine();
                    if(line.startsWith(".I") || line.startsWith(".A") || line.startsWith(".T")){
                        break;
                    }
                    stb.append(line).append("\n");
                }
                texto = stb.toString().trim();
                introducedoc(titulo, autor, texto);
            }
            
            
        }
        
    }
    
     private static void introducedoc(String titulo, String autor, String texto) throws SolrServerException, IOException
    {
        if(titulo != " " && autor != " " && texto != " "){
            System.out.println("El titulo es " + " "+ titulo);
            System.out.println("El autor es " + " "+ autor);
            System.out.println("El texto es " + " "+ texto);
        
        
            SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/motores4").build();
            SolrInputDocument doc = new SolrInputDocument();
        
            doc.addField("titulo", titulo);
            doc.addField("autor", autor);
            doc.addField("texto", texto);
            client.add(doc);
            client.commit();
        }
    }
     
    
    
   
    
    
    
}
