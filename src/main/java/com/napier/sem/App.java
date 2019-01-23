package com.napier.sem;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import  org.bson.Document;

import javax.print.Doc;


public class App
{
    public  static void main(String[] args)
    {
        //Connecting to MongoDB on Local System
        MongoClient mongoClient = new MongoClient("localhost", 27000);

        //Gets Database - Will Create when used
        MongoDatabase database = mongoClient.getDatabase("mydb");

        //Gets Collection From Database
        MongoCollection<Document> collection = database.getCollection("test");

        //Creates a Document to store information
        Document doc  = new Document("name","Iain McLachlan")
                .append("Class","Software Engineering Methods")
                .append("Year","2018/19")
                .append("Result",new Document("CW",95).append("EX",85));

        //Add Document to Collection
        collection.insertOne(doc);

        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
    }
}

