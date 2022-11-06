package ar.edu.unlam.tallerweb1.delivery;

import java.util.ArrayList;
import java.util.List;

public class DataEmpleado {
    private String name;
    private List<Double> data = new ArrayList<>();
    public DataEmpleado(String name, Double total) {
        this.name = name;
        this.data.add(total);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
