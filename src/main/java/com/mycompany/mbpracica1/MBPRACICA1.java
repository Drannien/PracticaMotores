/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mbpracica1;
import java.io.IOException;
import java.util.UUID;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;


/**
 *
 * @author luism
 */
public class MBPRACICA1 {

    public static void main(String[] args) throws SolrServerException, IOException {
        System.out.println("Hello World!");
        final SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr").build();
        final SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", UUID.randomUUID().toString());
        doc.addField("name", "Amazon Kindle Paperwhite");
        doc.addField("price", 99.0);
        final UpdateResponse updateResponse = client.add("Luismi", doc);
        // Indexed documents must be committed
        client.commit("Luismi");
    
    }
}
