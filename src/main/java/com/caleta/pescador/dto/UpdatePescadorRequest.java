package com.caleta.pescador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePescadorRequest (
    @NotBlank (message = "El usuario es obligatorio") Long usuarioId,
    @NotBlank (message = "La licencia es obligatoria") String licencia,
    @NotBlank (message = "El sindicato es obligatorio") String sindicato,
    @NotNull (message = "El ID del bote es obligatorio") Long BoteId,
    boolean activo
) {

}
