package br.com.navegatrem.repository;

import br.com.navegatrem.entity.EstacaoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstacaoRepository implements PanacheRepositoryBase<EstacaoEntity, String> {
}