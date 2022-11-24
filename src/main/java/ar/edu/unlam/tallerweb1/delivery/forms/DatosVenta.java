package ar.edu.unlam.tallerweb1.delivery.forms;

import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;

public class DatosVenta {
    long idEmpleado;
    long idP1;
    long cantidadP1;

    long idP2;
    long cantidadP2;

    long idP3;
    long cantidadP3;

    long idP4;
    long cantidadP4;

    long idP5;
    long cantidadP5;

    long idP6;
    long cantidadP6;

    long idP7;
    long cantidadP7;

    long idP8;
    long cantidadP8;

    long idP9;
    long cantidadP9;

    long idP10;
    long cantidadP10;

    public long getIdP1() {
        return idP1;
    }

    public void setIdP1(long idP1) {
        this.idP1 = idP1;
    }

    public long getCantidadP1() {
        return cantidadP1;
    }

    public long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setCantidadP1(long cantidadP1) {
        this.cantidadP1 = cantidadP1;
    }

    public long getIdP2() {
        return idP2;
    }

    public void setIdP2(long idP2) {
        this.idP2 = idP2;
    }

    public long getCantidadP2() {
        return cantidadP2;
    }

    public void setCantidadP2(long cantidadP2) {
        this.cantidadP2 = cantidadP2;
    }

    public long getIdP3() {
        return idP3;
    }

    public void setIdP3(long idP3) {
        this.idP3 = idP3;
    }

    public long getCantidadP3() {
        return cantidadP3;
    }

    public void setCantidadP3(long cantidadP3) {
        this.cantidadP3 = cantidadP3;
    }

    public long getIdP4() {
        return idP4;
    }

    public void setIdP4(long idP4) {
        this.idP4 = idP4;
    }

    public long getCantidadP4() {
        return cantidadP4;
    }

    public void setCantidadP4(long cantidadP4) {
        this.cantidadP4 = cantidadP4;
    }

    public long getIdP5() {
        return idP5;
    }

    public void setIdP5(long idP5) {
        this.idP5 = idP5;
    }

    public long getCantidadP5() {
        return cantidadP5;
    }

    public void setCantidadP5(long cantidadP5) {
        this.cantidadP5 = cantidadP5;
    }

    public long getIdP6() {
        return idP6;
    }

    public void setIdP6(long idP6) {
        this.idP6 = idP6;
    }

    public long getCantidadP6() {
        return cantidadP6;
    }

    public void setCantidadP6(long cantidadP6) {
        this.cantidadP6 = cantidadP6;
    }

    public long getIdP7() {
        return idP7;
    }

    public void setIdP7(long idP7) {
        this.idP7 = idP7;
    }

    public long getCantidadP7() {
        return cantidadP7;
    }

    public void setCantidadP7(long cantidadP7) {
        this.cantidadP7 = cantidadP7;
    }

    public long getIdP8() {
        return idP8;
    }

    public void setIdP8(long idP8) {
        this.idP8 = idP8;
    }

    public long getCantidadP8() {
        return cantidadP8;
    }

    public void setCantidadP8(long cantidadP8) {
        this.cantidadP8 = cantidadP8;
    }

    public long getIdP9() {
        return idP9;
    }

    public void setIdP9(long idP9) {
        this.idP9 = idP9;
    }

    public long getCantidadP9() {
        return cantidadP9;
    }

    public void setCantidadP9(long cantidadP9) {
        this.cantidadP9 = cantidadP9;
    }

    public long getIdP10() {
        return idP10;
    }

    public void setIdP10(long idP10) {
        this.idP10 = idP10;
    }

    public long getCantidadP10() {
        return cantidadP10;
    }

    public void setCantidadP10(long cantidadP10) {
        this.cantidadP10 = cantidadP10;
    }

    public Venta toVenta() {
        Venta venta = new Venta();
        Producto producto;
        if(idEmpleado != 0 ){
            venta.setIdEmpleado((int)idEmpleado);
        }
        if(idP1 != 0 && cantidadP1 != 0){
            producto = new Producto();
            producto.setId(idP1);
            producto.setCantidad((int)cantidadP1);
            venta.addProducto(producto);
        }
        if(idP2 != 0 && cantidadP2 != 0){
            producto = new Producto();
            producto.setId(idP2);
            producto.setCantidad((int)cantidadP2);
            venta.addProducto(producto);
        }
        if(idP3 != 0 && cantidadP3 != 0){
            producto = new Producto();
            producto.setId(idP3);
            producto.setCantidad((int)cantidadP3);
            venta.addProducto(producto);
        }
        if(idP4 != 0 && cantidadP4 != 0){
            producto = new Producto();
            producto.setId(idP4);
            producto.setCantidad((int)cantidadP4);
            venta.addProducto(producto);
        }
        if(idP5 != 0 && cantidadP5 != 0){
            producto = new Producto();
            producto.setId(idP5);
            producto.setCantidad((int)cantidadP5);
            venta.addProducto(producto);
        }
        if(idP6 != 0 && cantidadP6 != 0){
            producto = new Producto();
            producto.setId(idP6);
            producto.setCantidad((int)cantidadP6);
            venta.addProducto(producto);
        }
        if(idP7 != 0 && cantidadP7 != 0){
            producto = new Producto();
            producto.setId(idP7);
            producto.setCantidad((int)cantidadP7);
            venta.addProducto(producto);
        }
        if(idP8 != 0 && cantidadP8 != 0){
            producto = new Producto();
            producto.setId(idP8);
            producto.setCantidad((int)cantidadP8);
            venta.addProducto(producto);
        }
        if(idP9 != 0 && cantidadP9 != 0){
            producto = new Producto();
            producto.setId(idP9);
            producto.setCantidad((int)cantidadP9);
            venta.addProducto(producto);
        }
        if(idP10 != 0 && cantidadP10 != 0){
            producto = new Producto();
            producto.setId(idP10);
            producto.setCantidad((int)cantidadP10);
            venta.addProducto(producto);
        }
        return venta;
    }
}
