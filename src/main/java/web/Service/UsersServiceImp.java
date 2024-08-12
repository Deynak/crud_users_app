package web.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.Dao.UsersDao;
import web.entity.Users;

import java.util.List;

@Component
public class UsersServiceImp implements UsersService {

    @Autowired
    private UsersDao usersDao;


    @Override
    @Transactional
    public List<Users> readingAllUsers() {
        return usersDao.readingAllUsers();
    }

    @Override
    @Transactional
    public Users readUser(Long id) {
        return usersDao.readUser(id);
    }

    @Override
    @Transactional
    public void saveUser(Users user) {
        usersDao.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, Users user) {
        usersDao.updateUser(id, user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        usersDao.deleteUser(id);
    }
}
