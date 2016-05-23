package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {

    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testShouldFindLibraryByName() {
        // given
        final String libraryName = "Library1";
        // when
        LibraryEntity libraryEntity = libraryRepository.findLibraryByName(libraryName).get(0);
        // then
        assertNotNull(libraryEntity);
        assertEquals("Library1", libraryEntity.getName());
    }
    
    @Test
    public void testShouldFindLibraryByPrefix() {
        // given
        final String libraryName = "Lib";
        // when
        List<LibraryEntity> libraryEntity = libraryRepository.findLibraryByName(libraryName);
        // then
        assertNotNull(libraryEntity);
    }

    @Test
    public void testShouldRemoveLibraryWithAssignedBook() {
        // given
        final String libraryName = "Library2";
        LibraryEntity libraryEntity = libraryRepository.findLibraryByName(libraryName).get(0);
        // when
        libraryRepository.delete(libraryEntity);
        List<LibraryEntity> libraryEntity2 = libraryRepository.findLibraryByName(libraryName);
        List<BookEntity> bookEntity= bookRepository.findBookByTitle("Druga książka");
        // then
        assertTrue(libraryEntity2.isEmpty());
        assertTrue(bookEntity.isEmpty());
    }
}
