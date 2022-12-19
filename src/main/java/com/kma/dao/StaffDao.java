package com.kma.dao;

import com.kma.model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    private List<Staff> staffList;
    public StaffDao()
    {
        this.staffList = new ArrayList<>();
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    private PreparedStatement mapObjectToQuery(PreparedStatement stm, Boolean updateFlag, Staff staff)
            throws SQLException {
        stm.setInt(1, staff.getId());
        stm.setNString(2,staff.getName());
        stm.setString(3, staff.getPhone());
        stm.setTimestamp(4, staff.getBirthday());
        stm.setString(5, staff.getEmail());
        stm.setNString(6, staff.getGender());
        stm.setNString(7, staff.getDepartment());
        if(updateFlag)
            stm.setInt(8, staff.getId());
        return stm;
    }

    public List<Staff> findAll()
    {
        staffList = new ArrayList<>();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try
        {
            con = ConnectionDao.getConnection();
            String query = "SELECT * FROM staff";
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while(rs.next())
            {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setName(rs.getString("name"));
                staff.setPhone(rs.getString("phone"));
                staff.setBirthday(rs.getTimestamp("birthday"));
                staff.setEmail(rs.getString("email"));
                staff.setGender(rs.getString("gender"));
                staff.setDepartment(rs.getString("department"));
                staffList.add(staff);
            }
            return staffList;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try
            {
                if(rs != null)
                    rs.close();
                if(stm != null)
                    stm.close();
                if(con != null)
                    con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void save(String query, Boolean updateFlag, Staff... staff)
    {
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = ConnectionDao.getConnection();
            stm = con.prepareStatement(query);
            if(staff.length != 0)
                stm = mapObjectToQuery(stm, updateFlag, staff[0]);
            stm.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                if(stm != null)
                    stm.close();
                if(con != null)
                    con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void addStaff(Staff staff)
    {
        String query = "INSERT INTO staff VALUES(?, ?, ?, ?, ?, ?, ?)";
        save(query, false, staff);
    }

    public void updateStaff(Staff staff)
    {
        String query = "UPDATE staff SET id = ?, name = ?, phone = ?, birthday = ?, email = ?, gender = ?, department = ? " +
                "WHERE id = ?";
        save(query, true, staff);
    }
    public void deleteStaff(Integer staffId)
    {
        String query = "DELETE FROM staff WHERE id = " + staffId;
        save(query, false);
    }
}
