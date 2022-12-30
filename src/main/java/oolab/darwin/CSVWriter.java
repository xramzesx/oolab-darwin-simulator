package oolab.darwin;

import oolab.darwin.stats.EngineStats;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class CSVWriter {
    String fileName = "";
    public CSVWriter(String fileName) {
        this.fileName = fileName;
    }

    public String arrayToWritableString(ArrayList array) {
        String str = "[ ";
        for(int i = 0 ; i < array.size(); i++) {
            str += array.get(i) + " ";
        }
        str += "]";
        return str;
    }

    public void clearFile() {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print("");
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveRecord(String data) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(data);
            pw.flush();
            pw.close();
        } catch (Exception error) {
            System.out.println(error);
        }
    }


}
