/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAOInterface.IDAOMahasiswa;
import Helper.KoneksiDB;
import Model.Mahasiswa;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOMahasiswa implements IDAOMahasiswa {
    Connection con;
    //sqlQuery
    String strRead = "SELECT * FROM mhs;";
    String strInsert = "INSERT INTO mhs (nim,nama,kelas,prodi) values (?,?,?,?);";
    String strUpdate = "UPDATE mhs SET nama=?,kelas=?,prodi=? WHERE nim=?;";
    String strDelete = "DELETE FROM mhs WHERE nim=?;";
    
    public DAOMahasiswa () {
        con = KoneksiDB.getConection();
    }
    
    @Override
    public List<Mahasiswa> getAll() {
        List <Mahasiswa> lstMhs = null;
        try {
            lstMhs = new ArrayList<Mahasiswa>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(strRead);
            while(rs.next()){
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setKelas(rs.getString("kelas"));
                mhs.setProdi(rs.getString("prodi"));
                lstMhs.add(mhs);
            }
        } catch (Exception e) {
            System.out.println("Masalah :"+e);
        }
        return lstMhs;
    }

    @Override
    public void insert(Mahasiswa b ) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(strInsert);
            statement.setString(1, b.getNim());
            statement.setString(2, b.getNama());
            statement.setString(3, b.getKelas());
            statement.setString(4, b.getProdi());
            statement.execute();
        } catch (Exception e){
            System.out.println("INSERT GAGAL!");
        }
        finally{
            try {
                statement.close();
            } catch (Exception e) {
                System.out.print("GAGAL INSERT!");
            }
        }
    }

    @Override
    public void update(Mahasiswa b) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(strUpdate);
            statement.setString(1, b.getNim());
            statement.setString(2, b.getNama());
            statement.setString(3, b.getKelas());
            statement.setString(4, b.getProdi());
            statement.execute();
        } catch (Exception e){
            System.out.println("UPDATE GAGAL!");
        }
        finally{
            try {
                statement.close();
            } catch (Exception e) {
                System.out.print("GAGAL UPDATE!");
            }
        }
    }

    @Override
    public void delete(String nim ) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(strInsert);
            statement.setString(1, nim);
            statement.execute();
        } catch (Exception e){
            System.out.println("INSERT GAGAL!");
        }
        finally{
            try {
                statement.close();
            } catch (Exception e) {
                System.out.print("GAGAL INSERT!");
            }
        }
    }
}

