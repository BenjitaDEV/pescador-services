package com.caleta.pescador.mapper;

import com.caleta.pescador.dto.CreatePescadorRequest;
import com.caleta.pescador.dto.UpdatePescadorRequest;
import com.caleta.pescador.model.Pescador;

public class PescadorMapper {

    //CREATE
    public static Pescador toModel(CreatePescadorRequest request){
        return new Pescador(
            0L,
            request.usuarioId(),
            request.licencia(),
            request.BoteId(),
            request.sindicato(),
            request.activo()

        );
    }

    //UPDATE
    public static Pescador toModel(Long id, UpdatePescadorRequest request){
        return new Pescador(
            id,
            request.usuarioId(),
            request.licencia(),
            request.BoteId(),
            request.sindicato(),
            request.activo()
        );
    }

}
