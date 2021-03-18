package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository.USER_ID;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal) {
        return repository.save(meal, USER_ID);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id, USER_ID), id);
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id, USER_ID), id);
    }

    public Collection<Meal> getAll() {
        return repository.getAll(USER_ID);
    }

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal, USER_ID), meal.getId());
    }

}