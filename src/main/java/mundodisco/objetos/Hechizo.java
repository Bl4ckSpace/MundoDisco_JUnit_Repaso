package mundodisco.objetos;

import mundodisco.objetos.excepciones.HechizoEnRecargaException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Hechizo {
    private String nombre;
    private int tipo;
    private int rangoAtaque;
    private double tiempoRecarga;
    private LocalDateTime fechaHoraUsado;

    public Hechizo(String nombre, int tipo, int rangoAtaque, double tiempoRecarga) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.rangoAtaque = rangoAtaque;
        this.tiempoRecarga = tiempoRecarga;
        this.fechaHoraUsado = null;
    }

    public String usar() throws HechizoEnRecargaException {
        System.out.println("En usar");
        if (this.fechaHoraUsado==null || (this.fechaHoraUsado!=null && (this.fechaHoraUsado.plusNanos((long) (this.tiempoRecarga*1000000)).isBefore(LocalDateTime.now())))) {
            this.fechaHoraUsado = LocalDateTime.now();
            System.out.println("En usar: "+nombre);
            return "Usando el hechizo "+nombre;
        } else {
            throw new HechizoEnRecargaException("El hechizo se está recargando");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hechizo hechizo = (Hechizo) o;
        return Objects.equals(nombre, hechizo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public int getRangoAtaque() {
        return rangoAtaque;
    }

    public double getTiempoRecarga() {
        return tiempoRecarga;
    }

    public LocalDateTime getFechaHoraUsado() {
        return fechaHoraUsado;
    }

    public void setFechaHoraUsado(LocalDateTime fechaHoraUsado) {
        this.fechaHoraUsado = fechaHoraUsado;
    }
}
