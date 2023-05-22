package com.sophia.droid.service;

import com.sophia.droid.model.Droid;
import com.sophia.droid.repository.DroidRepository;

import java.util.List;

public class DroidService {
    private DroidRepository droidRepository;

    public DroidService(DroidRepository droidRepository) {
        this.droidRepository = droidRepository;
    }

    public List<Droid> findAll() {
        return droidRepository.findAll();
    }
}
