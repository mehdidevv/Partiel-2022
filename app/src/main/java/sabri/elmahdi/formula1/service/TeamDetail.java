package sabri.elmahdi.formula1.service;

public class TeamDetail {
    private Integer position;
    private Team team;
    private String points;
    private Integer season;
    private String website;

    public TeamDetail(Integer position, Team team, String points, Integer season, String website) {
        this.position = position;
        this.team = team;
        this.points = points;
        this.season = season;
        this.website = website;
    }

    public Integer getPosition() {
        return position;
    }

    public Team getTeam() {
        return team;
    }

    public String getPoints() {
        return points;
    }

    public Integer getSeason() {
        return season;
    }

    public String getWebsite() {
        return website;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
