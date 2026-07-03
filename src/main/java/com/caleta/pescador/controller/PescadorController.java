package com.caleta.pescador.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caleta.pescador.dto.CreatePescadorRequest;
import com.caleta.pescador.dto.UpdatePescadorRequest;
import com.caleta.pescador.exception.ResourceNotFoundException;
import com.caleta.pescador.model.Pescador;
import com.caleta.pescador.service.PescadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pescadores")
@Tag(name = "Pescadores", description = "API para gestionar pescadores")
public class PescadorController {

    private final PescadorService pescadorService;

    public PescadorController(PescadorService pescadorService) {
        this.pescadorService = pescadorService;
    }

    @Operation(
            summary = "Listar pescadores",
            description = "Obtiene todos los pescadores registrados"
    )
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Pescador>> listar() {
        return ResponseEntity.ok(pescadorService.getAllPescadores());
    }

    @Operation(
            summary = "Buscar pescador por ID",
            description = "Obtiene un pescador mediante su ID"
    )
    @ApiResponse(responseCode = "200", description = "Pescador encontrado")
    @ApiResponse(responseCode = "404", description = "Pescador no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Pescador> obtenerPorId(@PathVariable Long id) {

        Pescador pescador = pescadorService.getPescadorById(id);

        if (pescador == null) {
            throw new ResourceNotFoundException("Pescador " + id + " no encontrado");
        }

        return ResponseEntity.ok(pescador);
    }

    @Operation(
            summary = "Buscar pescador por licencia",
            description = "Obtiene un pescador mediante su licencia"
    )
    @ApiResponse(responseCode = "200", description = "Pescador encontrado")
    @ApiResponse(responseCode = "404", description = "Pescador no encontrado")
    @GetMapping("/licencia/{licencia}")
    public ResponseEntity<Pescador> obtenerPorLicencia(@PathVariable String licencia) {

        Pescador pescador = pescadorService.getPescadorByLicencia(licencia);

        if (pescador == null) {
            throw new ResourceNotFoundException("Pescador con licencia " + licencia + " no encontrado");
        }

        return ResponseEntity.ok(pescador);
    }

    @Operation(
            summary = "Listar pescadores activos",
            description = "Obtiene todos los pescadores activos"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping("/activos")
    public ResponseEntity<List<Pescador>> activos() {
        return ResponseEntity.ok(pescadorService.getActivos());
    }

    @Operation(
            summary = "Buscar pescadores por sindicato",
            description = "Obtiene todos los pescadores pertenecientes a un sindicato"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping("/sindicato/{sindicato}")
    public ResponseEntity<List<Pescador>> sindicato(@PathVariable String sindicato) {
        return ResponseEntity.ok(pescadorService.getBySindicato(sindicato));
    }

    @Operation(
            summary = "Crear pescador",
            description = "Registra un nuevo pescador"
    )
    @ApiResponse(responseCode = "201", description = "Pescador creado correctamente")
    @PostMapping
    public ResponseEntity<Pescador> crear(

            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del pescador",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                    {
                                      "nombre":"Juan Perez",
                                      "edad":30,
                                      "licencia":"LIC001",
                                      "activo":true,
                                      "sindicato":"Caleta Norte"
                                    }
                                    """
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody CreatePescadorRequest request) {

        Pescador nuevoPescador = pescadorService.crearPescador(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPescador);
    }

    @Operation(
            summary = "Actualizar pescador",
            description = "Actualiza la información de un pescador"
    )
    @ApiResponse(responseCode = "200", description = "Pescador actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Pescador no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Pescador> actualizar(
            @PathVariable Long id,
            @Valid
            @org.springframework.web.bind.annotation.RequestBody UpdatePescadorRequest request) {

        Pescador pescadorAct = pescadorService.actualizarPescador(id, request);

        if (pescadorAct == null) {
            throw new ResourceNotFoundException("Pescador " + id + " no encontrado");
        }

        return ResponseEntity.ok(pescadorAct);
    }

    @Operation(
            summary = "Eliminar pescador",
            description = "Elimina un pescador por su ID"
    )
    @ApiResponse(responseCode = "204", description = "Pescador eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Pescador no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        pescadorService.eliminarPescador(id);

        return ResponseEntity.noContent().build();
    }

}