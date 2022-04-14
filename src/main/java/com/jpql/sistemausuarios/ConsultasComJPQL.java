package com.jpql.sistemausuarios;

import com.jpql.sistemausuarios.dto.UsuarioDTO;
import com.jpql.sistemausuarios.model.Dominio;
import com.jpql.sistemausuarios.model.Usuario;

import javax.persistence.*;
import java.util.List;

public class ConsultasComJPQL {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Usuarios-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        //primeirasConsultas(entityManager);
        //escolhendoORetorno(entityManager);
        //fazendoProjecoes(entityManager);
          passandoParametros(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }

        public static void passandoParametros(EntityManager entityManager){
            String jpql = "select u from Usuario u where u.id = :idUsuario";
            TypedQuery<Usuario> typedQuery = entityManager
                    .createQuery(jpql, Usuario.class)
                    .setParameter("idUsuario", 1);
            Usuario usuario = typedQuery.getSingleResult();
            System.out.println(usuario.getId() + " ," + usuario.getNome());


            String jpqlLog = "select u from Usuario u where u.login = :loginUsuario";
            TypedQuery<Usuario> typedQueryLog = entityManager.createQuery(jpqlLog, Usuario.class);
            typedQueryLog.setParameter("loginUsuario", "ria");
            Usuario usuarioLog = typedQueryLog.getSingleResult();
            System.out.println(usuarioLog.getId() + ", " + usuarioLog.getNome());


        }

        public static void fazendoProjecoes(EntityManager entityManager){
            String jpqlArr = "select id, login, nome from Usuario";
            TypedQuery<Object[]> typedQueryArr = entityManager.createQuery(jpqlArr, Object[].class);
            List<Object[]> listArr = typedQueryArr.getResultList();
            listArr.forEach(arr -> System.out.println(String.format("%s, %s, %s", arr)));

            String jpqlDto = "select new com.jpql.sistemausuarios.dto.UsuarioDTO(id, login, nome)" +
                 "from Usuario";
            TypedQuery<UsuarioDTO> typedQueryDto = entityManager.createQuery(jpqlDto, UsuarioDTO.class);
            List<UsuarioDTO> listDto = typedQueryDto.getResultList();
            listDto.forEach(d -> System.out.println("DTO: " + d.getId() + ", " + d.getNome()));
        }

        public static void escolhendoORetorno(EntityManager entityManager){
            String jpql = "select u.dominio from Usuario u where u.id = 1";
            TypedQuery<Dominio> typedQuery = entityManager.createQuery(jpql, Dominio.class);
            Dominio dominio = typedQuery.getSingleResult();
            System.out.println(dominio.getId() + ", " + dominio.getNome());

            String jpqlNom = "select u.nome from Usuario u";
            TypedQuery<String> typedQueryNom = entityManager.createQuery(jpqlNom, String.class);
            List<String> listaNom = typedQueryNom.getResultList();
            listaNom.forEach( nome -> System.out.println(nome));
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
