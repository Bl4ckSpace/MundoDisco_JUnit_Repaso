package junit;

import mundodisco.objetos.DespensaMagica;
import mundodisco.objetos.Hechizo;
import mundodisco.objetos.excepciones.DespensaMagicaVaciaException;
import mundodisco.objetos.excepciones.HechizoEnRecargaException;
import mundodisco.objetos.excepciones.HechizoInexistenteException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DespensaMagicaTest {

    private static DespensaMagica despensaMagica;

    @BeforeEach
    public void crearDespensa() {
        despensaMagica = new DespensaMagica();
    }

    @AfterEach
    public void borrarDespensa() {
        despensaMagica = null;
    }

    @Test
    void sacaHechizoDespensaTest() {
        Hechizo hechizoEsperado = new Hechizo("Bola Fuego", 1, 3, 1000);
        despensaMagica.getHechizos().put("Bola Fuego", hechizoEsperado);
        try {
            assertSame(hechizoEsperado, despensaMagica.sacaHechizoDespensa("Bola Fuego"),
                "No son iguales los hechizos.");
        } catch (DespensaMagicaVaciaException | HechizoInexistenteException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void sacaHechizoInexistente() {
        despensaMagica.getHechizos().put("Bola Fuego", new Hechizo("Bola Fuego", 1, 3, 1000));
        assertThrows(HechizoInexistenteException.class, ()->despensaMagica.sacaHechizoDespensa(null));
    }

    @Test
    void sacaHechizoDespensaVacia() {
        assertThrows(DespensaMagicaVaciaException.class, ()->despensaMagica.sacaHechizoDespensa("Bola Fuego"));
    }

    @Test
    void almacenaHechizoTest() {
        Hechizo hechizo = new Hechizo("Bola Fuego", 1, 3, 1000);
        despensaMagica.almacenaHechizo(hechizo);
        assertEquals("Bola Fuego", hechizo.getNombre(), "No se ha podido almacenar porque no son el mismo hechizo");
    }

    @Test
    void eliminaHechizoTest() {
        despensaMagica.getHechizos().put("Bola Fuego", new Hechizo("Bola Fuego", 1, 3, 1000));
        despensaMagica.eliminaHechizo("Bola Fuego");
        assertTrue(despensaMagica.isEmpty(), "No se ha podido eliminar porque no existe el mismo hechizo");
    }

    @Test
    void usarHechizosTest() {
        ArrayList<String> usados = new ArrayList<>();
        ArrayList<String> nombresHechizos = new ArrayList<>();
        usados.add("Bola Fuego");
        nombresHechizos.add("Bola Fuego");

        despensaMagica.getHechizos().put("Bola Fuego", new Hechizo("Bola Fuego", 1, 3, 1000));
        try {
            assertEquals(usados.size(), despensaMagica.usarHechizos(nombresHechizos).size(),
                "No son los mismos hechizos usados.");
        } catch (DespensaMagicaVaciaException | HechizoEnRecargaException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void usarHechizosOrdenados() {
        ArrayList<String> nombresHechizos = new ArrayList<>();
        nombresHechizos.add("Pinchos Hielo");
        nombresHechizos.add("Bola Fuego");

        despensaMagica.getHechizos().put("Pinchos Hielo", new Hechizo("Pinchos Hielo", 2, 5, 3000));
        despensaMagica.getHechizos().put("Bola Fuego", new Hechizo("Bola Fuego", 1, 3, 1000));

        try {
            List<String> usados = despensaMagica.usarHechizos(nombresHechizos);
            List<String> copiaUsados = new ArrayList<>(usados);
            assertEquals(copiaUsados, usados, "No están ordenados.");
        } catch (DespensaMagicaVaciaException | HechizoEnRecargaException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void usarHechizosVacios() {
        assertThrows(DespensaMagicaVaciaException.class, ()->despensaMagica.usarHechizos(null));
    }
}