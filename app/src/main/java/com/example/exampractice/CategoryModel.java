package com.example.exampractice;

public class CategoryModel {
    private String docID;
    private String name;
    private int nooFTests;

    public CategoryModel(String docID, String name, int nooFTests) {
        this.docID = docID;
        this.name = name;
        this.nooFTests = nooFTests;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNooFTests() {
        return nooFTests;
    }

    public void setNooFTests(int nooFTests) {
        this.nooFTests = nooFTests;
    }
}
