package mundodisco.objetos;

import mundodisco.objetos.excepciones.DespensaMagicaVaciaException;
import mundodisco.objetos.excepciones.HechizoEnRecargaException;
import mundodisco.objetos.excepciones.HechizoInexistenteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DespensaMagica {
    private HashMap<String,Hechizo> hechizos;

    public DespensaMagica() {
        hechizos = new HashMap<>();
    }
    public Hechizo sacaHechizoDespensa(String nombreHechizo) throws DespensaMagicaVaciaException, HechizoInexistenteException {
        if (hechizos.isEmpty()){
            throw new DespensaMagicaVaciaException("La despensa mágica está vacía.");
        }

        if (!hechizos.containsKey(nombreHechizo)) {
            throw new HechizoInexistenteException("Hechizo "+nombreHechizo+" no existe en la despensa.");
        } else {
            return hechizos.get(nombreHechizo);
        }
    }

    public void almacenaHechizo(Hechizo hechizo) {
        if (!hechizos.containsKey(hechizo.getNombre())) {
            hechizos.put(hechizo.getNombre(),hechizo);
        }
    }
    public void vaciaDespensaMagica() {
        hechizos.clear();
    }

    public boolean isEmpty() {
        return hechizos.isEmpty();
    }

    public int size() {
        return hechizos.size();
    }

    public void eliminaHechizo(String nombreHechizo) {
        hechizos.remove(nombreHechizo);
    }
    public HashMap<String,Hechizo> getHechizos() {
        return hechizos;
    }
    public ArrayList<String> usarHechizos(ArrayList<String> nombresHechizos)
            throws DespensaMagicaVaciaException, HechizoEnRecargaException {
        ArrayList<String> usados = new ArrayList<>();
        if (this.hechizos.isEmpty()) {
            throw new DespensaMagicaVaciaException("La despensa mágica está vacía.");
        } else {
            for (String nombreHechizo: nombresHechizos) {
                usados.add(this.hechizos.get(nombreHechizo).usar());
            }
            Collections.sort(usados);
            return usados;
        }
    }
}
