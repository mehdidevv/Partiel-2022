package sabri.elmahdi.formula1.service;

import java.util.List;

public class TeamApiResponse {
    public TeamApiResponse(List<TeamDetail> teams) {
        this.teams = teams;
    }

    private List<TeamDetail> teams = null;

    public List<TeamDetail> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDetail> teams) {
        this.teams = teams;
    }
}
