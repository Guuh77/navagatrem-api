package br.com.navegatrem.repository;

import br.com.navegatrem.entity.FuncionarioEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepositoryBase<FuncionarioEntity, String> {

}