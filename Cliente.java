public class Cliente {
    private int codigo;
    private String nombre;
    private String telefono;
    private String zona;

    public Cliente() {
        this.codigo = 0;
        this.nombre = "";
        this.telefono = "";
        this.zona = "";
    }

    public Cliente(int codigo, String nombre, String telefono, String zona) {
        setCodigo(codigo);
        setNombre(nombre);
        setTelefono(telefono);
        setZona(zona);
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getZona() { return zona; }

    public void setCodigo(int codigo) {
        if (codigo > 0) this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        if (telefono != null) this.telefono = telefono;
    }

    public void setZona(String zona) {
        if (zona != null && !zona.trim().isEmpty()) this.zona = zona;
    }

    public String toString() {
        return "Cliente " + codigo + " - " + nombre + " - Tel: " + telefono + " - Zona: " + zona;
    }
}
