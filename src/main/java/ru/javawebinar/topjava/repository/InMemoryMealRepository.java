package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public class InMemoryMealRepository implements MealRepository {
    @Override
    public Meal save(Meal meal) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Meal get(int id) {
        return null;
    }

    @Override
    public Collection<Meal> getAll() {
        return null;
    }
}
