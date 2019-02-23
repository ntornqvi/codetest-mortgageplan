/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mortgagecalculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author nicke
 */
public class Parser {
    private File textFile;
    
    public Parser() {
        
    }
    
    public ArrayList<String> calculate(ArrayList<String> forCalc) {
        for(String s : forCalc) {
            if(0 > Double.parseDouble(s)) {
                throw new IllegalArgumentException();
            }
        }
        if(Double.parseDouble(forCalc.get(1)) > 100) {
            throw new IllegalArgumentException();
        }
        double E;
        double b;
        double p;
        double U = Double.parseDouble(forCalc.get(0));
        double interest = Double.parseDouble(forCalc.get(1));
        int years = Integer.parseInt(forCalc.get(2));
        
        p = years * 12;
        
        b = interest / 100 / 12;
        
        double temp1 = 1;
        for(int x = 0; x < p; ++x) {
            temp1 *= (1+b);
        }
                
        E = U * (b * temp1) / (temp1 - 1);
        
        ArrayList<String> calculated = new ArrayList();
        calculated.add(Double.toString(U));             //total loan
        calculated.add(forCalc.get(2));                 //years
        DecimalFormat df = new DecimalFormat("#.##");
        calculated.add(df.format(E));                   //monthly payment
        
        return calculated;
    }
    
    public String parse(String line, int x) {
        ArrayList<String> matches = new ArrayList();
        ArrayList<String> forCalc = new ArrayList();
        String name = null;
        Pattern regex = Pattern.compile("([^\"][^,]*|\".+?\")\\s*");
        Matcher regexMatcher = regex.matcher(line);
        while(regexMatcher.find()) {
            matches.add(regexMatcher.group());
        }
        int y = 0;
        for(String match : matches) {
            match = match.replace(",", " ");
            match = match.replace("\"", " ");
            match = match.trim();
            if(y == 0) {
                name = match;
            } else {
                forCalc.add(match);
            }
            ++y;
        }
        
        ArrayList<String> calculated = new ArrayList();
        
        calculated = calculate(forCalc);
        
        String s = "Prospect" + " " + x + ": " + name + 
                " wants to borrow " + calculated.get(0) + " for a period of " + 
                calculated.get(1) + " years and pay " + calculated.get(2) +
                " each month.";
        
        return s;
    }
    
    public ArrayList<String> getStrings() throws FileNotFoundException, IOException {
        ArrayList<String> strings = new ArrayList();
        FileInputStream is = new FileInputStream(this.textFile);
        InputStreamReader sr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(sr);
        String line;
        int x = 0;
        while((line = br.readLine()) != null) {
            if(x != 0 && line.trim().length() > 4) {
                strings.add(parse(line, x));
            }
            ++x;
        }
        return strings;
    }
    
    public void selectFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        this.textFile = fileChooser.showOpenDialog(primaryStage);
    }
}
