package ar.edu.unlam.tallerweb1.domain.cierreDiario.delivery.forms;

import java.util.ArrayList;
import java.util.List;

public class DataChart<T> {
    private String name;
    private List<T> data = new ArrayList<>();

    public DataChart(String name, T data) {
        this.name = name;
        this.data.add(data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
