package org.example.dao;

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


    @Before
    public void setUp() throws Exception {
        connection = DBUtils.getConnection();
    }

    @After
    public void tearDown() throws Exception {
        DBUtils.closeStatement(statement);
    }


    @Test
    public void testGetCoach() {
        coachDaoImpl = new CoachDaoImpl(connection);
        coachDaoImpl.getCoach(1);
    }

    @Test
    public void testInsertCoach() {
        coachDaoImpl = new CoachDaoImpl(connection);
        Coach coach = new Coach();
        coach.setId(5);
        coach.setName("Hasso");
        coach.setAwards("Halal");
        coach.setCountry_id(1);
        coach.setUser_id(1);

        coachDaoImpl.insertCoach(coach);
        assertNotNull("we added someone", coachDaoImpl.getCoach(5));
    }

    @Test
    public void testUpdateCoach() {
        coachDaoImpl = new CoachDaoImpl(connection);
        coachDaoImpl.getCoach(1);

        Coach coach = new Coach();
        coach.setName("Ragucci");
        coach.setAwards("Best in 2009");
        coach.setId(1);

        coachDaoImpl.updateCoach(coach);
        assertEquals(coachDaoImpl.getCoach(1).getId(), coach.getId());
        assertEquals(coachDaoImpl.getCoach(1).getName(), coach.getName());
        assertEquals(coachDaoImpl.getCoach(1).getAwards(), coach.getAwards());
    }

    @Test
    public void testDeleteCoach() {
        coachDaoImpl = new CoachDaoImpl(connection);
        coachDaoImpl.getCoach(5);

        coachDaoImpl.deleteCoach(5);
        assertNull("we don't have this coach",coachDaoImpl.getCoach(5));
    }
}