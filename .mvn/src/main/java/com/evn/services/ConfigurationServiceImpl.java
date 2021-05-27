package com.evn.services;

import com.evn.model.Configuration;
import com.evn.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public ConfigurationServiceImpl(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public List<Configuration> getAll() {
        List<Configuration> result = configurationRepository.findAll();
        return result;
    }

    @Override
    public Configuration save(Configuration configuration) {
        Configuration result = configurationRepository.save(configuration);
        return result;
    }

   

	@Override
	public Optional<Configuration> getById(String id) {
		Optional<Configuration> configuration = configurationRepository.findById(Integer.parseInt(id));
		return configuration;
	}

	@Override
	public Optional<Configuration> delete(String id) {
		// TODO Auto-generated method stub
//		Optional<Configuration> configuration = configurationRepository.findById(Integer.parseInt(id));
		 configurationRepository.deleteById(Integer.parseInt(id));
		 return null;
	}
}
