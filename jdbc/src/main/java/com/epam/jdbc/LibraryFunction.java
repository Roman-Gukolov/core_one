package com.epam.jdbc;

import com.epam.jdbc.Entity.Library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryFunction implements Dao {

    @Override
    public List<Library> getAll(ConnectionPool conPool) {
        List<Library> libraryList = new ArrayList<>();
        String sql = "SELECT * FROM LIBRARY ORDER BY BOOKID";
        Connection con = conPool.useCon();
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Library library = new Library();
                library.setId(rs.getLong("BOOKID"));
                library.setShelfNumber(rs.getLong("SHELF_NUMBER"));
                library.setBookName(rs.getString("BOOKNAME"));
                library.setAuthor(rs.getString("AUTHOR"));
                library.setDateRelease(rs.getDate("DATE_RELEASE"));
                libraryList.add(library);
            }
        } catch (SQLException e) {
            System.out.println("Error read table: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
        return libraryList;
    }

    @Override
    public int getRowCount(ConnectionPool conPool) {
        int result=0;
        String sql = "select COUNT(*) from library";
        Connection con = conPool.useCon();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("unexpected error: " + e);
        }
        return result;
    }

    @Override
    public void add(Library library, ConnectionPool conPool) {
        String sql = "INSERT INTO LIBRARY (BOOKID,SHELF_NUMBER,BOOKNAME,AUTHOR,DATE_RELEASE) VALUES (?,?,?,?,?)";
        Connection con = conPool.useCon();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, library.getId());
            ps.setLong(2, library.getShelfNumber());
            ps.setString(3, library.getBookName());
            ps.setString(4, library.getAuthor());
            ps.setDate(5, library.getDateRelease());
            ps.executeUpdate();
            System.out.println("Book is added");

        } catch (SQLException e) {
            System.out.println("Insert error: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
    }

    @Override
    public void update(Library library, ConnectionPool conPool) {
        String sql = "UPDATE LIBRARY SET SHELF_NUMBER = ?, BOOKNAME = ?, AUTHOR = ?, DATE_RELEASE = ? WHERE BOOKID = ?";
        Connection con = conPool.useCon();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(5, library.getId());
            ps.setLong(1, library.getShelfNumber());
            ps.setString(2, library.getBookName());
            ps.setString(3, library.getAuthor());
            ps.setDate(4, library.getDateRelease());
            ps.executeUpdate();
            System.out.println("Update complited");
        } catch (SQLException e) {
            System.out.println("Error searching: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
    }

    @Override
    public Library searchById(long id, ConnectionPool conPool) {
        System.out.println("Search by Id");
        String sql = "SELECT * FROM LIBRARY WHERE BOOKID = ?";
        Library library = new Library();
        Connection con = conPool.useCon();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    library.setId(rs.getLong("BOOKID"));
                    library.setShelfNumber(rs.getLong("SHELF_NUMBER"));
                    library.setBookName(rs.getString("BOOKNAME"));
                    library.setAuthor(rs.getString("AUTHOR"));
                    library.setDateRelease(rs.getDate("DATE_RELEASE"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
        return library;
    }

    @Override
    public List<Library> searchByBookName(String bookName, ConnectionPool conPool) {
        System.out.println("Search by book-name");
        List<Library> libraryList = new ArrayList<>();
        String sql = "SELECT * FROM LIBRARY WHERE BOOKNAME = ?";
        Connection con = conPool.useCon();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bookName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Library library = new Library();
                    library.setId(rs.getLong("BOOKID"));
                    library.setShelfNumber(rs.getLong("SHELF_NUMBER"));
                    library.setBookName(rs.getString("BOOKNAME"));
                    library.setAuthor(rs.getString("AUTHOR"));
                    library.setDateRelease(rs.getDate("DATE_RELEASE"));
                    libraryList.add(library);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
        return libraryList;
    }

    @Override
    public List<Library> searchByAuthor(String author, ConnectionPool conPool) {
        System.out.println("Search by author");
        List<Library> libraryList = new ArrayList<>();
        String sql = "SELECT * FROM LIBRARY WHERE AUTHOR = ?";
        Connection con = conPool.useCon();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, author);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Library library = new Library();
                    library.setId(rs.getLong("BOOKID"));
                    library.setShelfNumber(rs.getLong("SHELF_NUMBER"));
                    library.setBookName(rs.getString("BOOKNAME"));
                    library.setAuthor(rs.getString("AUTHOR"));
                    library.setDateRelease(rs.getDate("DATE_RELEASE"));
                    libraryList.add(library);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
        return libraryList;
    }

    @Override
    public List<Library> searchByYear(int year, ConnectionPool conPool) {
        System.out.println("Search by year release");
        List<Library> libraryList = new ArrayList<>();
        String sql = "SELECT * FROM LIBRARY WHERE EXTRACT(YEAR FROM date_release) = ?";
        Connection con = conPool.useCon();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Library library = new Library();
                    library.setId(rs.getLong("BOOKID"));
                    library.setShelfNumber(rs.getLong("SHELF_NUMBER"));
                    library.setBookName(rs.getString("BOOKNAME"));
                    library.setAuthor(rs.getString("AUTHOR"));
                    library.setDateRelease(rs.getDate("DATE_RELEASE"));
                    libraryList.add(library);
                }
            }
        } catch (Exception e) {
            System.out.println("Error searching: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
        return libraryList;
    }

    @Override
    public List<Library> searchByShelfNumber(long number, ConnectionPool conPool) {
        System.out.println("Search book on shelf number");
        List<Library> libraryList = new ArrayList<>();
        String sql = "SELECT * FROM LIBRARY WHERE SHELF_NUMBER = ?";
        Connection con = conPool.useCon();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, number);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Library library = new Library();
                    library.setId(rs.getLong("BOOKID"));
                    library.setShelfNumber(rs.getLong("SHELF_NUMBER"));
                    library.setBookName(rs.getString("BOOKNAME"));
                    library.setAuthor(rs.getString("AUTHOR"));
                    library.setDateRelease(rs.getDate("DATE_RELEASE"));
                    libraryList.add(library);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
        return libraryList;
    }

    @Override
    public List<Library> searchByBookNameAndAuthor(String bookName, String author, ConnectionPool conPool) {
        System.out.println("Search by book-name and author");
        List<Library> libraryList = new ArrayList<>();
        String sql = "SELECT * FROM LIBRARY WHERE BOOKNAME = ? AND AUTHOR = ?";
        Connection con = conPool.useCon();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bookName);
            ps.setString(2, author);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Library library = new Library();
                    library.setId(rs.getLong("BOOKID"));
                    library.setShelfNumber(rs.getLong("SHELF_NUMBER"));
                    library.setBookName(rs.getString("BOOKNAME"));
                    library.setAuthor(rs.getString("AUTHOR"));
                    library.setDateRelease(rs.getDate("DATE_RELEASE"));
                    libraryList.add(library);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
        return libraryList;
    }

    @Override
    public void deleteById(long id, ConnectionPool conPool) {
        String sql = "DELETE FROM LIBRARY WHERE BOOKID = ?";
        Connection con = conPool.useCon();
        try (
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("Delete complited");
        } catch (SQLException e) {
            System.out.println("Error searching: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
    }

    @Override
    public void deleteByBookNameAndAuthor(String bookName, String author, ConnectionPool conPool) {
        String sql = "DELETE FROM LIBRARY WHERE BOOKNAME = ? AND AUTHOR = ?";
        Connection con = conPool.useCon();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bookName);
            ps.setString(2, author);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error searching: " + e);
        } finally {
            conPool.putUsedCon(con);
        }
    }
}
