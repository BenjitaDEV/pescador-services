package com.caleta.pescador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePescadorRequest (
    @NotNull (message = "El usuario es obligatorio") Long usuarioId,
    @NotBlank (message = "La licencia es obligatoria") String licencia,
    @NotBlank (message = "El sindicato es obligatorio") String sindicato,
    @NotNull (message = "El ID del bote es obligatorio") Long BoteId,
    @NotNull (message = "El estado activo es obligatorio") Boolean activo
) {

}
