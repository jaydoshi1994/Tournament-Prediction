public class Match {

        Team homeTeam;
        Team awayTeam;

        Match(Team homeTeam, Team awayTeam) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
        }

        public Team getresult1() {
            return homeTeam;
        }

        public Team getresult2() {
            return awayTeam;
        }
}
