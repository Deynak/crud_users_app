package web.dao;


import org.springframework.stereotype.Repository;
import web.entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsersDaoImp implements UsersDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Users> readingAllUsers() {
        return em.createQuery("from Users").getResultList();
    }

    @Override
    public Users readUser(Long id) {
        return em.find(Users.class, id);
    }

    @Override
    public void saveUser(Users user) {
        em.persist(user);
    }

    @Override
    public void updateUser(Long id, Users user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        em.remove(em.find(Users.class, id));
    }
}
