/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbpracica1;

import java.io.File;
import java.util.Scanner;
import java.io.*;
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
    public static void main (String [] args) throws IOException, SolrServerException{
        String filename = "C:\\Users\\luism\\OneDrive\\Documentos\\NetBeansProjects\\MBPRACICA1\\CISI.ALL";
        Scanner scan = new Scanner(new File(filename));
        while(scan.hasNextLine())
        {
            int i = 0; 
            final SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr").build();
            final SolrInputDocument doc = new SolrInputDocument();
            String line = scan.nextLine();
            String [] fragments = line.split("(?<=I)");
            for (String fragment : fragments)
            {   
               
               System.out.println(fragment);
               doc.addField(Integer.toString(i),fragments);
               i++;
            }
            final UpdateResponse updateResponse = client.add("Luismi", doc);
            client.commit("Luismi");
            
            
        }
    }
    
}
