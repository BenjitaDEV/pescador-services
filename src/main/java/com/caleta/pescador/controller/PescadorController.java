package com.caleta.pescador.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caleta.pescador.dto.CreatePescadorRequest;
import com.caleta.pescador.dto.UpdatePescadorRequest;
import com.caleta.pescador.exception.ResourceNotFoundException;
import com.caleta.pescador.model.Pescador;
import com.caleta.pescador.service.PescadorService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/pescadores")
public class PescadorController {

    private final PescadorService pescadorService;

    public PescadorController(PescadorService pescadorService) {
        this.pescadorService = pescadorService;
    }

    @GetMapping
    public ResponseEntity<List<Pescador>> listar(){
        return ResponseEntity.ok(pescadorService.getAllPescadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pescador> obtenerPorId(@PathVariable Long id){
        Pescador pescador = pescadorService.getPescadorById(id);
        if (pescador == null){
            throw new ResourceNotFoundException("Pescador " + id +" no encontrado");
        }
        return ResponseEntity.ok(pescador);
    }

    @GetMapping("/licencia/{licencia}")
    public ResponseEntity<Pescador> obtenerPorLicencia(@PathVariable String liencia){
        Pescador pescador = pescadorService.getPescadorByLicencia(liencia);

        if (pescador == null){
            throw new ResourceNotFoundException("Pescador con licencia " + liencia + " no encontrado");
        }

        return ResponseEntity.ok(pescador);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Pescador>> activos(){
        return ResponseEntity.ok(pescadorService.getActivos());
    }

    @GetMapping("/sindicato/{sindicato}")
    public ResponseEntity<List<Pescador>> sindicato(@PathVariable String sindicato){
        return ResponseEntity.ok(pescadorService.getBySindicato(sindicato));
    }

    @PostMapping
    public ResponseEntity<Pescador> crear(@Valid @RequestBody CreatePescadorRequest request){
        Pescador nuevoPescador = pescadorService.crearPescador(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPescador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pescador> actualizar(@PathVariable Long id, @Valid @RequestBody UpdatePescadorRequest request){
        Pescador pescadorAct = pescadorService.actualizarPescador(id, request);

        if (pescadorAct == null){
            throw new ResourceNotFoundException("Pescador " + id + " no encontrado");
        }

        return ResponseEntity.ok(pescadorAct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        pescadorService.eliminarPescador(id);

        return ResponseEntity.noContent().build();
    }



}
