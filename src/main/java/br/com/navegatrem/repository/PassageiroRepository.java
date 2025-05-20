package br.com.navegatrem.repository;

import br.com.navegatrem.entity.PassageiroEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PassageiroRepository implements PanacheRepositoryBase<PassageiroEntity, Integer> {

}