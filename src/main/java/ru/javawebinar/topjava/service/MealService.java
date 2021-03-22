package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository.USER_ID;

import static ru.javawebinar.topjava.util.DateTimeUtil.getEndExclusive;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartInclusive;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

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

    public List<Meal> getBetweenHalfOpen(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.getBetweenHalfOpen(getStartInclusive(startDate), getEndExclusive(endDate), userId);
    }

}