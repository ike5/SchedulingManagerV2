package data;

import model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

// Completed CRUD functionality
public class DBCountries {

    /**
     * Returns the ResultSet object of the SELECT * query from the countries database table.
     *
     * @return ResultSet object
     */
    public static ResultSet getAllCountriesResultSet() {
        try {
            String sql = "SELECT * FROM countries";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     * Returns an ObservableList<Country> object of all countries listed in the countries database table.
     * listed in the countries database table.
     *
     * @return ObservableList<Country> object
     */
    public static ObservableList<Country> getAllCountries() {
        ObservableList<Country> countryObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = getAllCountriesResultSet();

            while (resultSet.next()) {
                Country country = new Country(resultSet.getInt("Country_ID"), resultSet.getString("Country"));
                countryObservableList.add(country);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return countryObservableList;
    }


    /**
     * Returns a Country object provided a country id.
     *
     * @param countryId
     * @return Country object
     */
    public Country getCountryFromCountryId(int countryId) {
        String sql = "SELECT Country_ID, Country FROM countries WHERE Country_ID = " + countryId;
        Country country = null;
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                country = new Country(resultSet.getInt(1), resultSet.getString(2));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return country;
    }

    /**
     * Should not use this method since SQL database is READ ONLY for Country
     *
     * @param country
     * @return
     */
    @Deprecated
    public Country updateCountry(Country country) {
        return null;
    }

    /**
     * Should not use this method since SQL database is READ ONLY for Country
     *
     * @param country
     * @return
     */
    @Deprecated
    public boolean deleteCountry(Country country) {
        return false;
    }

    /**
     * Should not use this method since SQL database is READ ONLY for Country
     *
     * @param country
     * @return
     */
    @Deprecated
    public boolean createCountry(Country country) {
        return false;
    }

    /**
     * Could implement some sort of factory to reduce the repetitive code?
     *
     * @param divisionId
     * @return
     */
    public static Country getCountryFromDivisionId(int divisionId) {
        Country country = null;
        try {
            String sql = "SELECT * FROM (SELECT countries.Country, client_schedule.first_level_divisions.Division_ID " +
                    "FROM countries INNER JOIN first_level_divisions ON countries.Country_ID = client_schedule.first_level_divisions.COUNTRY_ID) " +
                    "as CDI WHERE CDI.Division_ID = " + divisionId;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                country = new Country(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return country;
    }

    /**
     * Not sure if this method is necessary. Flag for removal
     */
    public static void checkDateConversion() { //FIXME - Do I need this method?
        System.out.println("CREATE DATE TEST");
        String sql = "SELECT Create_Date from countries";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("Create_Date");
                System.out.println("CD: " + ts.toLocalDateTime().toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Country getCountry(int divisionId){
        String sql = "SELECT client_schedule.countries.Country_ID, Country, Division_ID " +
                "FROM countries " +
                "LEFT JOIN first_level_divisions fld ON countries.Country_ID = fld.COUNTRY_ID " +
                "WHERE Division_ID = " + divisionId;
        Country country = null;
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                country = new Country(resultSet.getInt("Country_ID"), resultSet.getString("Country"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return country;
    }
}