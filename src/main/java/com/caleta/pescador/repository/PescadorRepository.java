package com.caleta.pescador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caleta.pescador.model.Pescador;

@Repository
public interface PescadorRepository extends JpaRepository<Pescador, Long> {
    
    //Validacion de licencia
    @Query(value = "SELECT * FROM pescadores WHERE licencia = :licencia", nativeQuery = true)
    Pescador findByLicencia(@Param("licencia") String licencia);

    //Verificar existencia de licencia
    @Query(value = "SELECT COUNT(*) FROM pescadores WHERE licencia = :licencia", nativeQuery = true)
    int countByLicencia(@Param("licencia") String licencia);

    @Query(value = "SELECT * FROM pescadores WHERE activo = true", nativeQuery = true)
    List<Pescador> findActivos();

    @Query(value = "SELECT * FROM pescadores WHERE sindicato = :sindicato", nativeQuery = true)
    List<Pescador> findBySindicato(@Param("sindicato") String sindicato);

    @Query(value = "SELECT * FROM pescadores WHERE usuario_id = :usuarioId", nativeQuery = true)
    Pescador findByUsuarioId(@Param("usuarioId") String usuarioId);

}
