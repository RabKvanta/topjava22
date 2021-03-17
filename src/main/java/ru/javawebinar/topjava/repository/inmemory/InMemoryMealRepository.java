package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.meals) {
            save(meal);
        }
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(SecurityUtil.authUserId());
        }
        // handle case: update, but not present in storage
        return meal.getUserId() == SecurityUtil.authUserId() ?
                repository.put(meal.getId(), meal) : null;
    }

    @Override
    public boolean delete(int id) {
        return repository.get(id).getUserId() == SecurityUtil.authUserId() ? repository.remove(id) != null : false;
    }

    @Override
    public Meal get(int id) {
        Meal meal = repository.get(id);
        return repository.get(id).getUserId() == SecurityUtil.authUserId() ? meal : null;
    }

    @Override
    public Collection<Meal> getAll() {

        List<Meal> meals = repository.values()
                .stream()
                .filter(m -> m.getUserId() == SecurityUtil.authUserId())
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        return meals.get(0).getUserId() == SecurityUtil.authUserId() ? meals : new ArrayList<Meal>();
    }
}

