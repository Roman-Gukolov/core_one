package com.epam.jdbc;

import com.epam.jdbc.Entity.Library;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LibraryFunctionTest {

    private Library libraryFirst;
    private Library libraryLast;
    private List<Library> libraries;

    @Mock
    private LibraryFunction function;

    @Mock
    private ConnectionPool pool;

    @Before
    public void init() {
        libraryFirst = new Library();
        libraryLast = new Library();

        libraries = new ArrayList<>();
        libraries.add(libraryFirst);
        libraries.add(libraryLast);
    }

    @Test
    public void testGetAll() {
        when(function.getAll(pool)).thenReturn(libraries);

        assertThat(libraries, equalTo(function.getAll(pool)));
    }

    @Test
    public void testRowCount() {
        int rows = 10;
        when(function.getRowCount(pool)).thenReturn(rows);

        assertThat(rows, equalTo(function.getRowCount(pool)));
    }

    @Test
    public void testSearchById() {
        int id = 10;
        when(function.searchById(id, pool)).thenReturn(libraryFirst);

        assertThat(libraryFirst, equalTo(function.searchById(id, pool)));
    }

    @Test
    public void testSearchByBookName() {
        String name = "bookNameTest";
        when(function.searchByBookName(name, pool)).thenReturn(libraries);

        assertThat(libraries, equalTo(function.searchByBookName(name, pool)));
    }

    @Test
    public void testSearchByAuthor() {
        String author = "bookNameTest";
        when(function.searchByAuthor(author, pool)).thenReturn(libraries);

        assertThat(libraries, equalTo(function.searchByAuthor(author, pool)));
    }

    @Test
    public void testSearchByYear() {
        int year = 2000;
        when(function.searchByYear(year, pool)).thenReturn(libraries);

        assertThat(libraries, equalTo(function.searchByYear(year, pool)));
    }

    @Test
    public void testSearchByShelfNumber() {
        int shelf = 2;
        when(function.searchByShelfNumber(shelf, pool)).thenReturn(libraries);

        assertThat(libraries, equalTo(function.searchByShelfNumber(shelf, pool)));
    }

    @Test
    public void testSearchByBookNameAndAuthor() {
        String book = "book";
        String author = "author";
        when(function.searchByBookNameAndAuthor(book, author, pool)).thenReturn(libraries);

        assertThat(libraries, equalTo(function.searchByBookNameAndAuthor(book, author, pool)));
    }

    @Test
    public void testAdd() {
        Mockito.doNothing().when(function).add(isA(Library.class), isA(ConnectionPool.class));
        function.add(libraryFirst, pool);

        verify(function, times(1)).add(libraryFirst, pool);
    }

    @Test
    public void testUpdate() {
        Mockito.doNothing().when(function).update(isA(Library.class), isA(ConnectionPool.class));
        function.update(libraryFirst, pool);

        verify(function, times(1)).update(libraryFirst, pool);
    }

    @Test
    public void testDeleteById() {
        long id = 15;
        Mockito.doNothing().when(function).deleteById(isA(Long.class), isA(ConnectionPool.class));
        function.deleteById(id, pool);

        verify(function, times(1)).deleteById(id, pool);
    }

    @Test
    public void testByBookNameAndAuthor() {
        String book = "book";
        String author = "author";
        Mockito.doNothing().when(function)
                .deleteByBookNameAndAuthor(isA(String.class), isA(String.class), isA(ConnectionPool.class));
        function.deleteByBookNameAndAuthor(book, author, pool);

        verify(function, times(1)).deleteByBookNameAndAuthor(book, author, pool);
    }
}
