package co.uniquindio.unicine.servicios;

import co.uniquindio.unicine.dto.HorarioSalaDTO;
import co.uniquindio.unicine.dto.HorarioSalaPeliculaDTO;
import co.uniquindio.unicine.entidades.*;
import co.uniquindio.unicine.repositorio.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorTeatroServicioImpl implements AdministradorTeatroServicio {


    private final AdministradorTeatroRepositorio administradorTeatroRepo;
    private final TeatroRepositorio teatroRepo;
    private final SalaRepositorio salaRepo;
    private final FuncionRepositorio funcionRepo;

    private final HorarioRepositorio horarioRepo;


    public AdministradorTeatroServicioImpl(AdministradorTeatroRepositorio administradorTeatroRepo, TeatroRepositorio teatroRepo, SalaRepositorio salaRepo, FuncionRepositorio funcionRepo, HorarioRepositorio horarioRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.teatroRepo = teatroRepo;
        this.salaRepo = salaRepo;
        this.funcionRepo = funcionRepo;
        this.horarioRepo = horarioRepo;
    }

    //Administrador Teatro
    @Override
    public AdministradorTeatro obtener(Integer id) throws Exception {

        Optional<AdministradorTeatro> guardado = administradorTeatroRepo.findById(id);

        if (guardado == null){
            try {
                throw new Exception("El Administrador teatro no existe");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return guardado.get();

    }

    @Override
    public boolean iniciarSesion(String email, String password) throws Exception {

        if (administradorTeatroRepo.comprobarAuntenticacion(email,password)==null){
            return false;
        }
        return true;

    }

    //Gestión Teatros
    @Override
    public Teatro obtenerTeatro(Integer id) throws Exception {

        Optional<Teatro> teatro = teatroRepo.findById(id);
        if (teatro.get().getId()==null){
            throw new Exception("El teatro con id " + id + "no existe");
        }
        return teatro.get();

    }


    @Override
    public Teatro crearTeatro(Teatro teatro) throws Exception {

            Teatro registro = teatroRepo.save(teatro);

            return registro;

    }




    @Override
    public void eliminarTeatro(Integer idTeatro) throws Exception {

        if (obtenerTeatro(idTeatro)==null){
            throw new Exception("El teatro con id :" + idTeatro + "No se puede eliminar porque no existe");
        }
        teatroRepo.delete(obtenerTeatro(idTeatro));

    }

    @Override
    public Teatro actualizarTeatro(Teatro teatro) throws Exception {

        Optional<Teatro> guardado = teatroRepo.findById(teatro.getId());
        if (guardado.isEmpty()){
            throw new Exception("El teatro con id :" + teatro.getId() + "No existe");
        }
        return teatroRepo.save(teatro);

    }

    @Override
    public List<Teatro> listarTeatros() throws Exception {
        List<Teatro> teatros = teatroRepo.findAll();

        return teatros;
    }

    //Gestión funciones
    @Override
    public Funcion obtenerFuncion(Integer id) throws Exception {
        Funcion funcion = funcionRepo.findById(id).orElse(null);
        if (funcion.getId() == null){
            throw new Exception("La funcion con id " + id + "no existe");
        }
        return funcion;
    }

    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception {
        return funcionRepo.save(funcion);
    }

    @Override
    public void eliminarFuncion(Integer idFuncion) throws Exception {

        if (obtenerFuncion(idFuncion)==null){
            throw new Exception("La funcion con id :" + idFuncion + "No se puede eliminar porque no existe");
        }
        funcionRepo.delete(obtenerFuncion(idFuncion));
    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {
        Optional<Funcion> guardado = funcionRepo.findById(funcion.getId());
        if (guardado.isEmpty()){
            throw new Exception("La funcion con id :" + funcion.getId() + "No existe");
        }
        return funcionRepo.save(funcion);
    }

    @Override
    public List<Funcion> listarFunciones() throws Exception {
        List<Funcion> funciones = funcionRepo.findAll();
        return funciones;
    }

    //Gestion sala
    @Override
    public Sala obtenerSala(Integer id) throws Exception {
        Sala sala = salaRepo.findById(id).orElse(null);
        if (sala.getId() == null){
            throw new Exception("La sala con id " + id + "no existe");
        }
        return sala;
    }

    @Override
    public Sala crearSala(Sala sala) throws Exception {
        return salaRepo.save(sala);
    }

    @Override
    public void eliminarSala(Integer idSala) throws Exception {
        if (obtenerSala(idSala)==null){
            throw new Exception("La sala con id :" + idSala + "No se puede eliminar porque no existe");
        }
        salaRepo.delete(obtenerSala(idSala));
    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {
        Optional<Sala> guardado = salaRepo.findById(sala.getId());
        if (guardado.isEmpty()){
            throw new Exception("La sala con id :" + sala.getId() + "No existe");
        }
        return salaRepo.save(sala);    }

    @Override
    public List<Sala> listarSalas() throws Exception {
        List<Sala> salas = salaRepo.findAll();

        return salas;
    }

    @Override
    public Pelicula obtenerPelicula(Integer idFuncion) throws Exception {

        Pelicula p = funcionRepo.obtenerPelicula(idFuncion);
        if (p == null){
            throw  new Exception("La pelicula no existe");
        }
        return p;
    }

    @Override
    public Horario obtenerHorario(Integer id) throws Exception {
        Horario h = horarioRepo.obtenerHorario(id);
        if (h == null){
            throw new Exception("El horario con id " + id + "no existe");
        }

        return h;
    }


    @Override
    public List<Horario> listarHorarios() {
        return horarioRepo.findAll();
    }


}
