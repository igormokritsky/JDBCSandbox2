package org.example.dao;

import org.example.ConnectionHolder;
import org.example.DAOException;
import org.example.DBUtils;
import org.example.ServiceException;
import org.example.mysql.impl.CoachDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.example.entity.*;
import java.sql.*;
import static org.junit.Assert.*;
import java.util.*;



public class CoachesDaoTest {


    private DBUtils dbUtils;
    private CoachDaoImpl coachDaoImpl;
    private CoachesDao coachesDao;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private ConnectionHolder connectionHolder;


    @Before
    public void setUp() throws Exception {
        connection = ConnectionHolder.getConnection();
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testGetCoach() throws DAOException {
        coachDaoImpl = new CoachDaoImpl(connection);
        coachDaoImpl.read(1);
    }

    @Test
    public void testInsertCoach() throws DAOException {
        coachDaoImpl = new CoachDaoImpl(connection);
        Coach coach = new Coach();
        coach.setId(5);
        coach.setName("Hasso");
        coach.setAwards("Halal");
        coach.setCountry_id(1);
        coach.setUser_id(1);

        coachDaoImpl.create(coach);
        assertNotNull("we added someone", coachDaoImpl.read(5));
    }

    @Test
    public void testUpdateCoach() throws DAOException {
        coachDaoImpl = new CoachDaoImpl(connection);
        coachDaoImpl.read(1);

        Coach coach = new Coach();
        coach.setName("Ragucci");
        coach.setAwards("Best in 2009");
        coach.setId(1);

        coachDaoImpl.update(coach);
        assertEquals(coachDaoImpl.read(1).getId(), coach.getId());
        assertEquals(coachDaoImpl.read(1).getName(), coach.getName());
        assertEquals(coachDaoImpl.read(1).getAwards(), coach.getAwards());
    }

    @Test
    public void testDeleteCoach() throws DAOException {
        coachDaoImpl = new CoachDaoImpl(connection);
        coachDaoImpl.read(5);

        coachDaoImpl.delete(5);
        assertNull("we don't have this coach",coachDaoImpl.read(5));
    }
}