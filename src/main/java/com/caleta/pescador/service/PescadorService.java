package com.caleta.pescador.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.caleta.pescador.dto.CreatePescadorRequest;
import com.caleta.pescador.dto.UpdatePescadorRequest;
import com.caleta.pescador.dto.UsuarioResponse;
import com.caleta.pescador.mapper.PescadorMapper;
import com.caleta.pescador.model.Pescador;
import com.caleta.pescador.repository.PescadorRepository;

@Service
public class PescadorService {

    private final PescadorRepository pescadorRepository;
    private final WebClient UsuarioWebClient;

    public PescadorService(PescadorRepository pescadorRepository, WebClient usuarioWebClient) {
        this.pescadorRepository = pescadorRepository;
        this.UsuarioWebClient = usuarioWebClient;
    }

    public List<Pescador> getAllPescadores(){
        return pescadorRepository.findAll();
    }

    public Pescador getPescadorById(Long id){
        return pescadorRepository.findById(id).orElse(null);
    }

    public Pescador getPescadorByLicencia(String licencia){
        return pescadorRepository.findByLicencia(licencia);
    }

    public List<Pescador> getActivos(){
        return pescadorRepository.findActivos();
    }

    public List<Pescador> getBySindicato(String sindicato){
        return pescadorRepository.findBySindicato(sindicato);
    }

    public Pescador crearPescador(CreatePescadorRequest request){

        UsuarioResponse usuario = UsuarioWebClient.get()
            .uri("/{id}", request.usuarioId())
            .retrieve()
            .bodyToMono(UsuarioResponse.class)
            .block();

        if (usuario == null){
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!usuario.isActivo()){
            throw new RuntimeException("El usuario esta inactivo");
        }

        if (!usuario.getRol().equalsIgnoreCase("PESCADOR")){
            throw new RuntimeException("El usuario no tiene el rol de pescador");
        }

        if (pescadorRepository.countByLicencia(request.licencia()) > 0){
            throw new RuntimeException("La licencia ya existe ");
        }

        Pescador pescador = PescadorMapper.toModel(request);
        return pescadorRepository.save(pescador);
    }

    public Pescador actualizarPescador (Long id, UpdatePescadorRequest request){
        Pescador pescador = pescadorRepository.findById(id).orElse(null);
        if (pescador == null){
            return null;
        }

        pescador.setUsuarioId(request.usuarioId());
        pescador.setLicencia(request.licencia());
        pescador.setBoteId(request.BoteId());
        pescador.setSindicato(request.sindicato());
        pescador.setActivo(request.activo());

        return pescadorRepository.save(pescador);
    }

    public void eliminarPescador (Long id){

        Pescador pescador = pescadorRepository.findById(id).orElse(null);

        if (pescador != null){
            throw new RuntimeException("Pescador no encontrado");
        }

        pescadorRepository.delete(pescador);
    }

}