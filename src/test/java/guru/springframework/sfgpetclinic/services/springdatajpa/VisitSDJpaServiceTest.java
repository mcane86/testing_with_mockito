package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    Visit visit;
    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSDJpaService service;

    @BeforeEach
    void setUp() {
        visit = new Visit();
    }

    @Test
    @DisplayName("Final All")
    void findAllTest() {
        // setup
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        when(visitRepository.findAll()).thenReturn(visits);
        // execute
        Set<Visit> foundVisits = service.findAll();
        // verify
        verify(visitRepository).findAll();
        assertEquals(1, foundVisits.size());
    }

    @Test
    @DisplayName("Find By ID")
    void findByIdTest() {
        // setup
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));
        // execute
        Visit foundVisit = service.findById(1L);
        //verify
        verify(visitRepository).findById(anyLong());
        assertNotNull(foundVisit);
    }

    @Test
    @DisplayName("Save")
    void saveTest() {
        // setup
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        // execute
        Visit savedVisit = service.save(visit);
        // verify
        verify(visitRepository).save(any(Visit.class));
        assertNotNull(savedVisit);
    }

    @Test
    @DisplayName("Delete")
    void deleteTest() {
        //execution
        service.delete(visit);
        // verify
        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    @DisplayName("Delete By ID")
    void deleteByIdTest() {
        // execution
        service.deleteById(2L);
        // verify
        verify(visitRepository).deleteById(anyLong());
    }
}