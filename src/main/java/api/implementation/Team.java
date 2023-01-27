package api.implementation;
import collections.implementation.ArrayUnorderedList;
import collections.exceptions.EmptyCollectionException;
public class Team {

        private TeamName name;
        private ArrayUnorderedList<Player> teamPlayers;

        public Team(TeamName name) {
            this.name = name;
            this.teamPlayers = new ArrayUnorderedList<>();
        }

        public TeamName getName() {
            return name;
        }

        public boolean validateUserExistence(String playerName) {
            for (Player player : teamPlayers) {
                if (player.getName().equals(playerName)) {
                    return true;
                }
            }
            return false;
        }

        public int getUserIDbyName(String playerName) {
            for (Player player : teamPlayers) {
                if (player.getName().equals(playerName)) {
                    return player.getId();
                }
            }
            return -1;
        }

        public void setName(TeamName name) {
            this.name = name;
        }

        public ArrayUnorderedList<Player> getTeamPlayers() {
            return teamPlayers;
        }

        public void setTeamPlayers(ArrayUnorderedList<Player> teamPlayers) {
            this.teamPlayers = teamPlayers;
        }

        public void addPlayer(Player player) {
            teamPlayers.addToRear(player);
        }

        public void removePlayer(Player player) throws EmptyCollectionException {
            teamPlayers.remove(player);
        }

        public boolean findPlayer(Player player) {
            return teamPlayers.contains(player);
        }

        public Player findPlayerByID(int ID) {
            return teamPlayers.get(ID);
        }

        public boolean isEmpty() {
            return teamPlayers.isEmpty();
        }

        public int size() {
            return teamPlayers.size();
        }

        public enum TeamName {
            Sparks, Giants
        }

        @Override
        public String toString() {
            return "Team [name=" + name + ", teamPlayers=" + teamPlayers.toString() + "]";
        }
}


