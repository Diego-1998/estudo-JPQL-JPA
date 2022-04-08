package com.jpql.sistemausuarios;

import com.jpql.sistemausuarios.model.Usuario;

import javax.persistence.*;
import java.util.List;
import java.util.Queue;

public class ConsultasComJPQL {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Usuarios-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        primeirasConsultas(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }

        public static void  primeirasConsultas(EntityManager entityManager){

            String jpql = "select u from Usuario u";
            TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql, Usuario.class);
            List<Usuario> lista = typedQuery.getResultList();
            lista.forEach( u -> System.out.println(u.getId() + ", " + u.getNome()));

            String jpqlSing = "select u from Usuario u where u.id = 1";
            TypedQuery<Usuario> typedQuerySing = entityManager.createQuery(jpqlSing, Usuario.class);
            Usuario usuario = typedQuerySing.getSingleResult();
            System.out.println(usuario.getId() + " " + usuario.getNome());

            String jpqlCast = "select u from Usuario u where u.id = 1";
            Query query = entityManager.createQuery(jpqlCast);
            Usuario usuario2 = (Usuario) query.getResultList();
            System.out.println(usuario2.getId() + ", " + usuario2.getNome());

    }
}
