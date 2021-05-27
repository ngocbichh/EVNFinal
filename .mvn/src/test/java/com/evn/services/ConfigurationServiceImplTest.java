package com.evn.services;

import com.evn.model.Configuration;
import com.evn.repository.ConfigurationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.annotation.Rollback;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ConfigurationServiceImplTest {

    @Mock
    private ConfigurationRepository mockConfigurationRepository;

    private ConfigurationServiceImpl configurationServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        configurationServiceImplUnderTest = new ConfigurationServiceImpl(mockConfigurationRepository);
    }

    @Test
    void testGetAll() {
        // Setup
        final Configuration configuration = new Configuration();
        configuration.setIdConfiguration(0);
        configuration.setSubject("subject");
        configuration.setCreator("creator");
        configuration.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration.setPrice(0.0f);
        configuration.setEditor("editor");
        configuration.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Configuration> expectedResult = List.of(configuration);

        // Configure ConfigurationRepository.findAll(...).
        final Configuration configuration1 = new Configuration();
        configuration1.setIdConfiguration(0);
        configuration1.setSubject("subject");
        configuration1.setCreator("creator");
        configuration1.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration1.setPrice(0.0f);
        configuration1.setEditor("editor");
        configuration1.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Configuration> configurations = List.of(configuration1);
        when(mockConfigurationRepository.findAll()).thenReturn(configurations);

        // Run the test
        final List<Configuration> result = configurationServiceImplUnderTest.getAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAll_ConfigurationRepositoryReturnsNoItems() {
        // Setup
        final Configuration configuration = new Configuration();
        configuration.setIdConfiguration(0);
        configuration.setSubject("subject");
        configuration.setCreator("creator");
        configuration.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration.setPrice(0.0f);
        configuration.setEditor("editor");
        configuration.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<Configuration> expectedResult = List.of(configuration);
        when(mockConfigurationRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Configuration> result = configurationServiceImplUnderTest.getAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    @Rollback
    void testSave() {
        // Setup
        final Configuration configuration = new Configuration();
        configuration.setIdConfiguration(0);
        configuration.setSubject("subject");
        configuration.setCreator("creator");
        configuration.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration.setPrice(0.0f);
        configuration.setEditor("editor");
        configuration.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final Configuration expectedResult = new Configuration();
        expectedResult.setIdConfiguration(0);
        expectedResult.setSubject("subject");
        expectedResult.setCreator("creator");
        expectedResult.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setPrice(0.0f);
        expectedResult.setEditor("editor");
        expectedResult.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure ConfigurationRepository.save(...).
        final Configuration configuration1 = new Configuration();
        configuration1.setIdConfiguration(0);
        configuration1.setSubject("subject");
        configuration1.setCreator("creator");
        configuration1.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration1.setPrice(0.0f);
        configuration1.setEditor("editor");
        configuration1.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
//        when(mockConfigurationRepository.save(new Configuration())).thenReturn(configuration1);

        // Run the test
        final Configuration result = configurationServiceImplUnderTest.save(configuration);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetById() {
        // Setup
        final Configuration configuration = new Configuration();
        configuration.setIdConfiguration(0);
        configuration.setSubject("subject");
        configuration.setCreator("creator");
        configuration.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration.setPrice(0.0f);
        configuration.setEditor("editor");
        configuration.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final Optional<Configuration> expectedResult = Optional.of(configuration);

        // Configure ConfigurationRepository.findById(...).
        final Configuration configuration2 = new Configuration();
        configuration2.setIdConfiguration(0);
        configuration2.setSubject("subject");
        configuration2.setCreator("creator");
        configuration2.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration2.setPrice(0.0f);
        configuration2.setEditor("editor");
        configuration2.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final Optional<Configuration> configuration1 = Optional.of(configuration2);
        when(mockConfigurationRepository.findById(0)).thenReturn(configuration1);

        // Run the test
        final Optional<Configuration> result = configurationServiceImplUnderTest.getById("0");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetById_ConfigurationRepositoryReturnsAbsent() {
        // Setup
        final Configuration configuration = new Configuration();
        configuration.setIdConfiguration(0);
        configuration.setSubject("subject");
        configuration.setCreator("creator");
        configuration.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration.setPrice(0.0f);
        configuration.setEditor("editor");
        configuration.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final Optional<Configuration> expectedResult = Optional.of(configuration);
        when(mockConfigurationRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Configuration> result = configurationServiceImplUnderTest.getById("0");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    @Rollback
    void testDelete() {
        // Setup
        final Configuration configuration = new Configuration();
        configuration.setIdConfiguration(0);
        configuration.setSubject("subject");
        configuration.setCreator("creator");
        configuration.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration.setPrice(0.0f);
        configuration.setEditor("editor");
        configuration.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final Optional<Configuration> expectedResult = null;

        // Configure ConfigurationRepository.findById(...).
        final Configuration configuration2 = new Configuration();
        configuration2.setIdConfiguration(0);
        configuration2.setSubject("subject");
        configuration2.setCreator("creator");
        configuration2.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration2.setPrice(0.0f);
        configuration2.setEditor("editor");
        configuration2.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final Optional<Configuration> configuration1 = Optional.of(configuration2);
        when(mockConfigurationRepository.findById(0)).thenReturn(configuration1);

        // Run the test
        final Optional<Configuration> result = configurationServiceImplUnderTest.delete("0");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockConfigurationRepository).deleteById(0);
    }

    @Test
    @Rollback
    void testDelete_ConfigurationRepositoryFindByIdReturnsAbsent() {
        // Setup
        final Configuration configuration = new Configuration();
        configuration.setIdConfiguration(0);
        configuration.setSubject("subject");
        configuration.setCreator("creator");
        configuration.setDateCreate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        configuration.setPrice(0.0f);
        configuration.setEditor("editor");
        configuration.setDateEdit(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final Optional<Configuration> expectedResult = Optional.of(configuration);
        when(mockConfigurationRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Configuration> result = configurationServiceImplUnderTest.delete("0");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockConfigurationRepository).deleteById(0);
    }
}