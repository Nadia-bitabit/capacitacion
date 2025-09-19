package Controller;

import Models.PersonaDao;
import Models.PersonaModel;

public class PersonaController {
    private PersonaDao personaDAO;

    public PersonaController(PersonaDao personaDao) {
        personaDAO = personaDao;
    }
    public void insertPersona(PersonaModel persona){
        personaDAO.crearPersona(persona);
    }

}
