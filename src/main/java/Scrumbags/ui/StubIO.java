/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scrumbags.ui;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author toniramo
 */
public class StubIO implements IO {
    private List<String> input;
    private List<String> output;
    private int index;
    
    public StubIO(List<String> input) {
        this.input = input;
        this.output = new ArrayList<>();
    }

    @Override
    public int nextInt() {
        //TODO
        return -1;
    }

    @Override
    public String nextLine() {
        if (index < input.size()) {
            return input.get(index++);
        }
        return "";
    }

    @Override
    public void print(String m) {
        output.add(m);
    }
    
    public List<String> getOutput() {
        return output;
    }
}
