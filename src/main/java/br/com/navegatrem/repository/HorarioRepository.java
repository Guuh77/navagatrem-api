package br.com.navegatrem.repository;

import br.com.navegatrem.entity.HorarioEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HorarioRepository implements PanacheRepositoryBase<HorarioEntity, String> {
}