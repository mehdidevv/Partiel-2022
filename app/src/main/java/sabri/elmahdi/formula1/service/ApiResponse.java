package sabri.elmahdi.formula1.service;

public class ApiResponse {
    private Integer position;
    private Driver driver;
    private Team team;
    private String points;

    public ApiResponse(Integer position, Driver driver, Team team, String points, Integer season) {
        this.position = position;
        this.driver = driver;
        this.team = team;
        this.points = points;
        this.season = season;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    private Integer season;
}
