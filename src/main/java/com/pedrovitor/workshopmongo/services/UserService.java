package com.pedrovitor.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrovitor.workshopmongo.domain.User;
import com.pedrovitor.workshopmongo.dto.UserDTO;
import com.pedrovitor.workshopmongo.repository.UserRepository;
import com.pedrovitor.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public User update(User obj) {
		User newObj = this.findById(obj.getId());
		this.updateData(newObj, obj);
		return repo.save(newObj);
	}	

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User fromDTO (UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
