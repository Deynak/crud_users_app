package web.service;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UsersDao;
import web.entity.Users;

import java.util.List;

@Component
@Transactional
public class UsersServiceImp implements UsersService {

    private final UsersDao usersDao;

    public UsersServiceImp(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    @Override
    public List<Users> readingAllUsers() {
        return usersDao.readingAllUsers();
    }

    @Override
    public Users readUser(Long id) {
        return usersDao.readUser(id);
    }

    @Override
    public void saveUser(Users user) {
        usersDao.saveUser(user);
    }

    @Override
    public void updateUser(Long id, Users user) {
        usersDao.updateUser(id, user);
    }

    @Override
    public void deleteUser(Long id) {
        usersDao.deleteUser(id);
    }
}
