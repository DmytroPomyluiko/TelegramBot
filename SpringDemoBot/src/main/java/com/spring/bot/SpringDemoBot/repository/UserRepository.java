package com.spring.bot.SpringDemoBot.repository;

import com.spring.bot.SpringDemoBot.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
