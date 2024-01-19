package pierpaolo.colasante.u5w2d5project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pierpaolo.colasante.u5w2d5project.entities.User;
import pierpaolo.colasante.u5w2d5project.exceptions.BadRequestException;
import pierpaolo.colasante.u5w2d5project.exceptions.NotFoundException;
import pierpaolo.colasante.u5w2d5project.payloads.UserDTO;
import pierpaolo.colasante.u5w2d5project.repositories.UserDAO;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    public List<User> getUser(){return this.userDAO.findAll();}
    public User save(UserDTO body){
        userDAO.findByEmail(body.email()).ifPresent(user -> {throw new BadRequestException("email " + user.getEmail() + " già usata!!!");});
        userDAO.findByUsername(body.username()).ifPresent(user -> {throw new BadRequestException("username " + user.getUsername() + " già in uso!!!");});
        User newUser = new User();
        newUser.setName(body.name());
        newUser.setLastName(body.lastName());
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        newUser.setAvatar("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.lastName());
        return userDAO.save(newUser);
    }
    public User findById(long id){
        return userDAO.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public User findByIdAndUpdate(long id, User body){
        User found = this.findById(id);
        found.setName(body.getName());
        found.setLastName(body.getLastName());
        found.setUsername(body.getUsername());
        found.setEmail(body.getEmail());
        found.setAvatar(body.getAvatar());
        return userDAO.save(found);
    }
    public void findByIdAndDelete(int id){
        User found = this.findById(id);
        userDAO.delete(found);
    }
}
